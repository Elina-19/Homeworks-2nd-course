package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.ProductDto;
import ru.itis.services.ProductsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(
                productsService.findAll());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("product-id") Long id){
        return ResponseEntity.ok(
                productsService.getById(id));
    }

}
