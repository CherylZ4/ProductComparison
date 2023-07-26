package com.example.ProductComparisonTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ProductComparison.api.service.DataProcessingService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = DataProcessingTests)
public class DataProcessingTests {
    @InjectMocks
    DataProcessingService dataProcessingService;

    @Test
    public void test_getIngredientsInCommon_whenIngrInCommonExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4"));

        assertEquals(Arrays.asList("ingr1", "ingr2"), dataProcessingService.getIngredientsInCommon(mockList1, mocklList2));


    }

    @Test
    public void test_getIngredientsInCommon_whenIngrInCommonDontExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr4", "ingr5", "ingr6"));

        assertEquals(Collections.emptyList(), dataProcessingService.getIngredientsInCommon(mockList1, mocklList2));

    }
}

    @Test
    public void test_calculateJaccardSimilarity_whenIngrInCommonExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr3", "ingr5"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));

        assertEquals(50, dataProcessingService.calculateJaccardSimilarity(mockList1, mocklList2));

    }

    @Test
    public void test_calculateJaccardSimilarity_whenIngrInDontExist(){
        List<String> mockList1 = new ArrayList<>(Arrays.asList("ingr7", "ingr8", "ingr3", "ingr5"));
        List<String> mocklList2 = new ArrayList<>(Arrays.asList("ingr1", "ingr2", "ingr4", "ingr6"));

        assertEquals(0, dataProcessingService.calculateJaccardSimilarity(mockList1, mocklList2));

}
