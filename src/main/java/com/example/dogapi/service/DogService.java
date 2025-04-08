package com.example.dogapi.service;

import com.example.dogapi.model.DogImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

// Esta anotacion de servicio
@Service
public class DogService {

    private final String API_URL = "https://api.thedogapi.com/v1/images/search?limit=3";

//    Esta linea carga el valor de thdogapi.key desde el archivo application.properties
    @Value("${thedogapi.key}")
    private String apikey;

    public List<DogImage> getDogImages() {
// Se crea una instancia de RestTemplate, que es la herramienta para hacer la peticion HTTP a la api
        RestTemplate restTemplate = new RestTemplate();

        // Se crea un objeto headers donde se pone la apikey
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apikey);

// Crea el objeto HttpEntity
// Este objeto entity = representa la peticion Http completa, con las cabeceras necesarias
// No enviamos cuerpo(body) porque es una peticion GET, asi que solo va la cabecera
        HttpEntity<String> entity = new HttpEntity<>(headers);

// Peticion get
/**
 * exchange = permite hacer peticiones con headers personalizados
 * API_URL = url de la api
 * HttpMethod.GET = tipo de peticion
 * entity = la entidad que contiene las cabeceras.
 * DogImage[].class = el tipo de respuesta que esperamos
 */
        ResponseEntity<DogImage[]> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                DogImage[].class
        );

// Extrae el contenido del ResponseEntity, es decir el array de imagenes de perros que te devolvio la api
        DogImage[] dogs = response.getBody();

        System.out.println("Imágenes recibidas: " + dogs.length);

// Convertir el array en lista y devolverlo
        return Arrays.asList(dogs);
    }
}


/**
 * [DogService]
 *    └─ llama a la API con clave 🔑
 *        └─ recibe JSON con imágenes 🐶🐶🐶
 *            └─ convierte JSON a objetos Java
 *                └─ devuelve lista de DogImage al controlador
 *                    └─ el controlador la envía a la vista con Thymeleaf
 */