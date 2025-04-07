package com.example.dogapi.service;

import com.example.dogapi.model.DogImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DogService {

    private final String API_URL = "https://api.thedogapi.com/v1/images/search?limit=3";

    @Value("${thedogapi.key}")
    private String apikey;

    public List<DogImage> getDogImages() {
        RestTemplate restTemplate = new RestTemplate();

        // Agregamos los headers con la API KEY
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apikey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DogImage[]> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                DogImage[].class
        );

        DogImage[] dogs = response.getBody();

        System.out.println("Imágenes recibidas: " + dogs.length);
        return Arrays.asList(dogs);
    }
}
