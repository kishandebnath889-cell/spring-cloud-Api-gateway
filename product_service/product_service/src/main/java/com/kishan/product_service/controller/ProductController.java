package com.kishan.product_service.controller;

import com.kishan.product_service.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product(1, "Laptop", 55000.0),
                new Product(2, "Headphones", 2500.0),
                new Product(3, "Keyboard", 1500.0)
        );
    }
}