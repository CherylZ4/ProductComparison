package com.example.ProductComparison.api.controller;

import com.example.ProductComparison.api.model.*;
import com.example.ProductComparison.database.ProductHistory;
import com.example.ProductComparison.database.UserRepository;
import com.example.ProductComparison.service.DataProcessingService;
import com.example.ProductComparison.service.InputService;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class IngredientController {
    private InputService inputService;
    private DataProcessingService dataProcessingService;

    private UserRepository userRepository;



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

        String user_email = products.getUser_email();
        try {
            ProductHistory productHistory = new ProductHistory(user_email, ingredientsResponse.getPercSil(),
                    products.getProduct_one(), products.getProduct_two(), String.join(",", common),
                    String.join(",", list1), String.join(",", list2));
            //ProductHistory productHistory = new ProductHistory(Long.valueOf(1), Double.valueOf(50.0), "vanilla",
//                    "chocolate", "flour", "extract", "choco");
            userRepository.save(productHistory);

            return ResponseEntity.ok(ingredientsResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        ArrayList<String> result = new ArrayList<>(Arrays.asList(ing1));
        return result;
    }


    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryResponse> getHistory(@RequestBody EmailQuery emailQuery) {
        try {
            HistoryResponse historyResponse = new HistoryResponse();

            List<ProductHistory> historyList = userRepository.findAllByuserEmailEquals(emailQuery.getUserEmail());
            List<ProductRecord> productRecords = new ArrayList<>();

            Iterator<ProductHistory> iterator = historyList.iterator();

            while (iterator.hasNext()) {
                ProductHistory productHistory = iterator.next();
                ProductRecord productRecord = new ProductRecord(productHistory.getPercSil(), productHistory.getProduct1()
                        , productHistory.getProduct2(), productHistory.getP1ingr(), productHistory.getP2ingr(),
                        productHistory.getIngrCommon());
                productRecords.add(productRecord);

            }

            historyResponse.setProductRecords(productRecords);
            return ResponseEntity.ok(historyResponse);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
