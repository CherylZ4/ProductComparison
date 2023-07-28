package com.example.ProductComparison.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DataProcessingServiceTest {

    DataProcessingService dataProcessingService;

    @BeforeEach
    void setUp() {
        dataProcessingService = new DataProcessingService();
    }

    @Test
    public void test_getIngredientsInCommon_whenIngrInCommonExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));
        List<String> mockList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4"));

        assertEquals(Arrays.asList("ingr1", "ingr2"), dataProcessingService.getIngredientsInCommon(mockList1, mockList2));


    }


    @Test
    public void test_getIngredientsInCommon_whenIngrInCommonDontExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr4", "ingr5", "ingr6"));

        assertEquals(Collections.emptyList(), dataProcessingService.getIngredientsInCommon(mockList1, mocklList2));

    }

    @Test
    public void test_getIngredientsInCommon_whenIngrSame(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));
        List<String> mockList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));

        assertEquals(Arrays.asList("ingr1", "ingr2", "ingr3"), dataProcessingService.getIngredientsInCommon(mockList1, mockList2));


    }


    @Test
    public void test_calculateJaccardSimilarity_whenIngrInCommonExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3", "ingr5"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));

        assertEquals(33, dataProcessingService.calculateJaccardSimilarity(mockList1, mocklList2));

    }

    @Test
    public void test_calculateJaccardSimilarity_whenIngrInDontExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr7", "ingr8", "ingr3", "ingr5"));
        List<String> mockList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));

        assertEquals(0, dataProcessingService.calculateJaccardSimilarity(mockList1, mockList2));

}

    @Test
    public void test_calculateJaccardSimilarity_whenIngrSame(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));
        List<String> mockList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));

        assertEquals(100, dataProcessingService.calculateJaccardSimilarity(mockList1, mockList2));

    }
}
