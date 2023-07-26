package com.example.ProductComparisonTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ProductComparison.api.controller.IngredientController;
import com.example.ProductComparison.api.model.IngredientResponse;
import com.example.ProductComparison.api.model.Products;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
@SpringBootTest(classes = {IngredientControllerTests.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IngredientControllerTests {
    @InjectMocks
    IngredientController ingredientController;
    @Mock
    InputService inputService;
    DataProcessingService dataProcessingService;


    @Test
    @Order(1)
    public void test_retrieveIngr(){
        String productName = "vanilla cake";
        when(inputService.getIngredients(productName)).thenReturn(Array.asList("ingr1", "ingr2", "ingr3")); //not array I dont know what format

        assertEquals(Array.asList("ingr1", "ingr2", "ingr3"),retrieveIngr("vanilla cake"));

    }

    @Test
    @Order(2)
    public void test_generateIngredients(){
        Products products;
        products.setProduct_two("chocolate cake");
        products.setProduct_one ("vanilla cake");

        IngredientsResponse myIngredientsResponse = new IngredientsResponse();


        when(ingredientsResponse.setPercSil(dataProcessingService.calculateJaccardSimilarity(list1,list2))).thenReturn(50);
        when(ingredientsResponse.setIngr_common(common)).thenReturn(Array.asList("ingr1"));
        when(ingredientsResponse.setProduct_one_ingr(list1)).thenReturn(Array.asList("ingr1", "ingr2", "ingr3"));
        when(ingredientsResponse.setProduct_two_ingr(list2)).thenReturn(Array.asList("ingr1", "ingr4", "ingr5"));

        assertEquals(Array.asList("ingr1"),myIngredientsResponse.getIngr_common());
        assertEquals(Array.asList("ingr1", "ingr2", "ingr3"),myIngredientsResponse.getProduct_one_ingr());
        assertEquals(Array.asList("ingr1", "ingr4", "ingr5"),myIngredientsResponse.getProduct_two_ingr());

        ResponseEntity<IngredientsResponse> res = IngredientController.generateIngredients(products);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(res.getBody()); // dont know what to compare it with

    }





}