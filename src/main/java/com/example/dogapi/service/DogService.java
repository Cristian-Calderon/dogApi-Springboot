package com.example.dogapi.service;

import com.example.dogapi.model.DogImage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
// La anotacion @Service indica a Spring que esta clase es un "servicio" y debe ser gestionada
// (inyectada con @Autowired)
public class DogService {

    private final String API_URL = "https://api.thedogapi.com/v1/images/search?limit=10";

    public List<DogImage> getDogImages() {
// RestTemplate = se usa para hacer una peticion HTTP GET a la api
        RestTemplate restTemplate = new RestTemplate();
// getForObject () Hace la llamada y espera que la respuesta sea un array de DogImage
        DogImage[] response = restTemplate.getForObject(API_URL, DogImage[].class);
        return Arrays.asList(response);
    }

}
