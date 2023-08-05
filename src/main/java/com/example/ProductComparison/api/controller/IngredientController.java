package com.example.ProductComparison.api.controller;

import com.example.ProductComparison.api.model.IngredientResponse;
import com.example.ProductComparison.api.model.IngredientsResponse;
import com.example.ProductComparison.api.model.Products;
import com.example.ProductComparison.database.ProductHistory;
import com.example.ProductComparison.database.UserRepository;
import com.example.ProductComparison.service.DataProcessingService;
import com.example.ProductComparison.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class IngredientController {
    private InputService inputService;
    private DataProcessingService dataProcessingService;

    private UserRepository userRepository;

    private Long idCounter = 1L;

    public IngredientController(InputService inputService, DataProcessingService dataProcessingService, UserRepository userRepository) {
        this.inputService = inputService;
        this.dataProcessingService = dataProcessingService;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/ingredients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientsResponse> generateIngredients(@RequestBody Products products) {
        IngredientsResponse ingredientsResponse = new IngredientsResponse();

        if (products.getProduct_one().isEmpty() && products.getProduct_two().isEmpty()) {
            throw new RuntimeException("both products are empty");
        }

        if (products.getProduct_one().isEmpty()) {
            throw new RuntimeException("product 1 is empty");
        }
        if (products.getProduct_two().isEmpty()) {
            throw new RuntimeException("product 2 is empty");
        }

        List<String> list1 = retrieveIngr(products.getProduct_one());
        List<String> list2 = retrieveIngr(products.getProduct_two());
        ingredientsResponse.setPercSil(dataProcessingService.calculateJaccardSimilarity(list1, list2));
        List<String> common = dataProcessingService.getIngredientsInCommon(list1, list2);


        ingredientsResponse.setIngr_common(common);
        list1.removeAll(common);
        ingredientsResponse.setProduct_one_ingr(list1);
        list2.removeAll(common);
        ingredientsResponse.setProduct_two_ingr(list2);

        try {
            ProductHistory productHistory = new ProductHistory(idCounter++, ingredientsResponse.getPercSil(),
                    products.getProduct_one(), products.getProduct_two(), String.join(",", common),
                    String.join(",", list1), String.join(",", list2));
            //ProductHistory productHistory = new ProductHistory(Long.valueOf(1), Double.valueOf(50.0), "vanilla",
//                    "chocolate", "flour", "extract", "choco");
            userRepository.save(productHistory);

            return ResponseEntity.ok(ingredientsResponse);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    /**
     * retrieves the ingredients string array of the product by calling chatgpt using inputService
     * it returns a string list of the ingredients sorted
     */
    public List<String> retrieveIngr(String productName) {
        IngredientResponse rs1 = inputService.getIngredients(productName);
        String[] ing1 = rs1.getChoices().get(0).getText().replace("\n", "").split(",");
        Arrays.sort(ing1);
        return Arrays.asList(ing1);
    }

}
