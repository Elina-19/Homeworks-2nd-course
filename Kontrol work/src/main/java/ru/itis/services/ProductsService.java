package ru.itis.services;

import ru.itis.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto getById(Long id);
    List<ProductDto> findAll();
    void save(Long id, ProductDto productDto);
}
