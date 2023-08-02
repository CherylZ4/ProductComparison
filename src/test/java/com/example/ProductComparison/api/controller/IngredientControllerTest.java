package com.example.ProductComparison.api.controller;

import com.example.ProductComparison.api.model.IngredientResponse;
import com.example.ProductComparison.api.model.IngredientsResponse;
import com.example.ProductComparison.api.model.Products;
import com.example.ProductComparison.service.InputService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
    @Mock
    InputService inputService;
    @InjectMocks
    IngredientController ingredientController;

    @Mock
    DataProcessingService dataProcessingService;

    IngredientResponse result1;
    IngredientResponse result2;
    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFilePath = "src/test/resources/IngredientResponse.json";
        ObjectMapper objectMapper2 = new ObjectMapper();
        String jsonFilePath2 = "src/test/resources/IngredientResponse2.json";
        result1 = objectMapper.readValue(new File(jsonFilePath), IngredientResponse.class);
        result2 = objectMapper2.readValue(new File(jsonFilePath2), IngredientResponse.class);
     }

    @Test
    @Order(1)
    void test_retrieveIngr() {
        Mockito.lenient().when(inputService.getIngredients("vanilla cake")).thenReturn(result1);
        String[] output = {"all-purpose flour","hot water"};
        assertEquals(Arrays.asList(output),ingredientController.retrieveIngr("vanilla cake"));

    }
    @Test
    @Order(2)
    void test_generateIngredients_SameIngr() {
        Products products = new Products();
        products.setProduct_two("chocolate cake");
        products.setProduct_one ("vanilla cake");

        Mockito.lenient().when(dataProcessingService.calculateJaccardSimilarity(anyList(),anyList())).thenReturn(100.0);
        Mockito.lenient().when(dataProcessingService.getIngredientsInCommon(anyList(),anyList())).thenReturn(Arrays.asList("all-purpose flour","hot water"));

        Mockito.lenient().when(inputService.getIngredients("vanilla cake")).thenReturn(result1);
        Mockito.lenient().when(inputService.getIngredients("chocolate cake")).thenReturn(result1);

        ResponseEntity<IngredientsResponse> res = ingredientController.generateIngredients(products);
        IngredientsResponse ingredientsResponse = res.getBody();

        assertEquals(Arrays.asList("all-purpose flour","hot water"),ingredientsResponse.getIngr_common());
        assertEquals(Collections.emptyList(),ingredientsResponse.getProduct_one_ingr());
        assertEquals(Collections.emptyList(),ingredientsResponse.getProduct_two_ingr());

        assertEquals(HttpStatus.OK,res.getStatusCode());
    }

    @Test
    @Order(2)
    void test_generateIngredients_someDiffIngr() {
        Products products = new Products();
        products.setProduct_two("chocolate cake");
        products.setProduct_one ("vanilla cake");

        Mockito.lenient().when(dataProcessingService.calculateJaccardSimilarity(anyList(),anyList())).thenReturn(50.0);
        Mockito.lenient().when(dataProcessingService.getIngredientsInCommon(anyList(),anyList())).thenReturn(Arrays.asList("all-purpose flour"));

        Mockito.lenient().when(inputService.getIngredients("vanilla cake")).thenReturn(result1);
        Mockito.lenient().when(inputService.getIngredients("chocolate cake")).thenReturn(result2);

        ResponseEntity<IngredientsResponse> res = ingredientController.generateIngredients(products);
        IngredientsResponse ingredientsResponse = res.getBody();

        assertEquals(Arrays.asList("all-purpose flour"),ingredientsResponse.getIngr_common());
        assertEquals(Arrays.asList("hot water"),ingredientsResponse.getProduct_one_ingr());
        assertEquals(Arrays.asList("chocolate"),ingredientsResponse.getProduct_two_ingr());

        assertEquals(HttpStatus.OK,res.getStatusCode());

    }

}

