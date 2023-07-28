package com.example.ProductComparison.service;

import com.example.ProductComparison.api.model.IngredientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;



@ExtendWith(MockitoExtension.class)
class InputServiceTest {
    //@InjectMocks
    InputService inputService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        inputService = new InputService(restTemplate);
    }

    @Test
    void getIngredients() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFilePath = "src/test/resources/IngredientResponse.json";
        IngredientResponse result = objectMapper.readValue(new File(jsonFilePath), IngredientResponse.class);
        ResponseEntity<IngredientResponse> resultEntity = new ResponseEntity<>(result,HttpStatus.OK);

        // Using lenient stubbing
        Mockito.lenient().when(restTemplate.postForEntity(
                anyString(), // You can use anyString() to allow any URL
                any(), // You can use any() to allow any request body
                eq(IngredientResponse.class)
        )).thenReturn(resultEntity);

        IngredientResponse rs = inputService.getIngredients("vanilla cake");
        assertEquals("cmpl-7gxN6Yci6oxkgSBMW1mbsQgBmfMDd",rs.getId());

    }


    @Test
    void test_getIngredients_whenRestCallFails(){
        Mockito.when(restTemplate.postForEntity(anyString(), any(), eq(IngredientResponse.class)))
                .thenThrow(new RestClientException("API call failed"));

        // Act and Assert: Test the service method that calls RestTemplate
        assertThrows(RestClientException.class, () -> inputService.getIngredients("vanilla cake"));
    }

}