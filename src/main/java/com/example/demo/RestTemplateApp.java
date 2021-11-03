package com.example.demo;

// this class is used to get and post data to API

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateApp {


    ResponseEntity<Partners> responseEntity = null;

    public ResponseEntity<Partners> getDataFromAPI() {
        // request url
        String url = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=c2c65fed95129ae44e4b603601e1";


        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                Partners.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(responseEntity.getBody().getPartners());
        }


        return responseEntity;
    }

    void postData(ResponseEntity<Partners> responseEntity) {

        PostDataService postDataService = new PostDataService();

       Country countryObj=  postDataService.processData(responseEntity);

      // postDataService.sendData(countriesList);




    }

}