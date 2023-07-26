package com.example.ProductComparisonTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ProductComparison.api.service.InputService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = {InputServiceTests.class})
public class InputServiceTests {
    @InjectMocks
    InputService inputService;


    @Test
    public void test_getIngredients(){
        String productName = "vanilla cake";
    }


}