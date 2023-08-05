package com.example.ProductComparison.api.controller;

import com.example.ProductComparison.api.model.IngredientResponse;
import com.example.ProductComparison.api.model.IngredientsResponse;
import com.example.ProductComparison.api.model.Products;
import com.example.ProductComparison.database.ProductHistory;
import com.example.ProductComparison.database.UserRepository;
import com.example.ProductComparison.service.DataProcessingService;
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
import java.util.ArrayList;
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

    @Mock
    UserRepository userRepository;

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
        products.setUser_email("cherylzhang8@gmail.com");

        Mockito.lenient().when(dataProcessingService.calculateJaccardSimilarity(anyList(),anyList())).thenReturn(100.0);
        Mockito.lenient().when(dataProcessingService.getIngredientsInCommon(anyList(),anyList())).thenReturn(Arrays.asList("all-purpose flour","hot water"));

        Mockito.lenient().when(inputService.getIngredients("vanilla cake")).thenReturn(result1);
        Mockito.lenient().when(inputService.getIngredients("chocolate cake")).thenReturn(result1);

        Mockito.lenient().when(userRepository.save(any())).thenReturn(null);

        ResponseEntity<IngredientsResponse> res = ingredientController.generateIngredients(products);
        IngredientsResponse ingredientsResponse = res.getBody();

        String[] ingredientsArray = {"all-purpose flour", "hot water"};
        ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(ingredientsArray));
        assertEquals(ingredientsList,ingredientsResponse.getIngr_common());
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
        products.setUser_email("cherylzhang8@gmail.com");

        Mockito.lenient().when(dataProcessingService.calculateJaccardSimilarity(anyList(),anyList())).thenReturn(50.0);
        Mockito.lenient().when(dataProcessingService.getIngredientsInCommon(anyList(),anyList())).thenReturn(Arrays.asList("all-purpose flour"));

        Mockito.lenient().when(inputService.getIngredients("vanilla cake")).thenReturn(result1);
        Mockito.lenient().when(inputService.getIngredients("chocolate cake")).thenReturn(result2);

        ResponseEntity<IngredientsResponse> res = ingredientController.generateIngredients(products);
        IngredientsResponse ingredientsResponse = res.getBody();

        String[] ingredientsArray = {"all-purpose flour"};
        String[] ingredientsArray1 = {"hot water"};
        String[] ingredientsArray2 = {"chocolate"};
        ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(ingredientsArray));
        ArrayList<String> ingredientsList1 = new ArrayList<>(Arrays.asList(ingredientsArray1));
        ArrayList<String> ingredientsList2 = new ArrayList<>(Arrays.asList(ingredientsArray2));
        assertEquals(ingredientsList,ingredientsResponse.getIngr_common());
        assertEquals(ingredientsList1,ingredientsResponse.getProduct_one_ingr());
        assertEquals(ingredientsList2,ingredientsResponse.getProduct_two_ingr());

        assertEquals(HttpStatus.OK,res.getStatusCode());

    }

}

