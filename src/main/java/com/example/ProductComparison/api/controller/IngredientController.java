package com.example.ProductComparison.api.controller;

import com.example.ProductComparison.api.model.IngredientResponse;
import com.example.ProductComparison.api.model.IngredientsResponse;
import com.example.ProductComparison.api.model.Products;
import com.example.ProductComparison.service.DataProcessingService;
import com.example.ProductComparison.service.InputService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class IngredientController {
    private InputService inputService;
    private DataProcessingService dataProcessingService;

    public IngredientController(InputService inputService, DataProcessingService dataProcessingService) {
        this.inputService = inputService;
        this.dataProcessingService = dataProcessingService;
    }

    @PostMapping(value = "/ingredients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientsResponse> generateIngredients(@RequestBody Products products) {
        IngredientsResponse ingredientsResponse = new IngredientsResponse();

        if (products.getProduct_one().isEmpty() && products.getProduct_two().isEmpty()){
            RuntimeException e  = new RuntimeException("both products are empty");
            throw e;
        }

        if (products.getProduct_one().isEmpty()){
            RuntimeException e  = new RuntimeException("product 1 is empty");
            throw e;
        }
        if (products.getProduct_two().isEmpty()){
            RuntimeException e  = new RuntimeException("product 2 is empty");
            throw e;
        }

        List<String> list1 = retrieveIngr(products.getProduct_one());
        List<String> list2 = retrieveIngr(products.getProduct_two());
        ingredientsResponse.setPercSil(dataProcessingService.calculateJaccardSimilarity(list1,list2));
        List<String> common = dataProcessingService.getIngredientsInCommon(list1, list2);


        ingredientsResponse.setIngr_common(common);
        list1.removeAll(common);
        ingredientsResponse.setProduct_one_ingr(list1);
        list2.removeAll(common);
        ingredientsResponse.setProduct_two_ingr(list2);


        return ResponseEntity.ok(ingredientsResponse);

    }

    /**
     * retrieves the ingredients string array of the product by calling chatgpt using inputService
     * it returns a string list of the ingredients sorted
     *
     * @param productName
     */
    private List<String> retrieveIngr(String productName) {
        IngredientResponse rs1 = inputService.getIngredients(productName);
        String[] ing1 = rs1.getChoices().get(0).getText().replaceAll("\n", "").split(",");
        Arrays.sort(ing1);
        List<String> list1 = new ArrayList<String>(Arrays.asList(ing1));
        return list1;
    }

}
