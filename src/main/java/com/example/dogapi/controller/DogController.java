package com.example.dogapi.controller;


import com.example.dogapi.model.DogImage;
import com.example.dogapi.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
// Indica que esta clase maneja peticiones HTTP y devuelve vistas HTML con Thymeleaf
public class DogController {

// @Autowired le dice a Spring: "Inyectame una instacia del servicio DogService automaticamente"
    @Autowired
    private DogService dogService;

// Este metodo se activa cuando vistas http://localhost:8080/
    @GetMapping("/")
    public String index(Model model) {
// Llama al servicio para obtener la lista de perros (dogService.getDogImagen)
        List<DogImage> dogs = dogService.getDogImages();
// Agrega esa lista al modelo con el nombre "dogs", que luego usaremos en Thymeleaf
        model.addAttribute("dogs", dogs);
// Devuelve la vista llamada "index" (debe haber un archivo index.html en templates/)
        return "index";
    }
}


