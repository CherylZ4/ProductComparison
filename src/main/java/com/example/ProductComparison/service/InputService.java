package com.example.ProductComparison.service;


import com.example.ProductComparison.api.model.IngredientRequest;
import com.example.ProductComparison.api.model.IngredientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;


@Service
public class InputService {
    private String endpointUrl = "https://api.openai.com/v1/completions";

//      private RestTemplate restTemplate;
    @Value("${openai.api.key}")
    private String apiKey;

    //
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//
//    }


//    @Autowired
//    private SecretClient secretClient;

//    public String getPasswordFromKeyVault() {
//        KeyVaultSecret secret = secretClient.getSecret("open-api-key");
//        return secret.getValue();
//    }
    public IngredientResponse getIngredients(String productName) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            IngredientRequest request = new IngredientRequest();
            request.setPrompt("list ingredients of " + productName);
            request.setModel("text-davinci-edit-001");
            request.setMax_tokens(7);
            request.setTemperature(0);


            RestTemplate restTemplate = new RestTemplate();
            String prompt = "list ingredients of " + productName;
            String requestBody = "{\"prompt\": \"" + prompt + "\"," +
                    " \"max_tokens\": 100, " +
                    "\"n\": 1, " +
                    "\"temperature\": 0.7, " +
                    "\"model\": \"text-davinci-001\"}";

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<IngredientResponse> responseEntity = restTemplate.postForEntity(endpointUrl, requestEntity, IngredientResponse.class);
//        ResponseEntity<IngredientResponse> responseEntity = restTemplate.postForEntity(endpointUrl,
//                new HttpEntity<>(request, headers), IngredientResponse.class);
//

            String[] words = responseEntity.getBody().getChoices().get(0).getText().split(",");

            for (String word : words) {
                System.out.println(word);
            }
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
          throw e;
        }

    }
}