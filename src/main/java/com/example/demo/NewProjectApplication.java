package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class NewProjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(NewProjectApplication.class, args);
        RestTemplateApp restTemplateApp = new RestTemplateApp();

       ResponseEntity<Partners> partnerObject= restTemplateApp.getDataFromAPI();

      // System.out.print(partnerObject.toString())
        restTemplateApp.postData(partnerObject);
    }
}
