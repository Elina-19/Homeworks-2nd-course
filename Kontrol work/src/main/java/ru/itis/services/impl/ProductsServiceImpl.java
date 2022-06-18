package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductDto;
import ru.itis.exceptions.ProductNotExistException;
import ru.itis.models.Product;
import ru.itis.models.Storehouse;
import ru.itis.repositories.ProductsRepository;
import ru.itis.services.ProductsService;
import ru.itis.services.StorehouseService;

import java.util.List;

import static ru.itis.dto.ProductDto.from;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    private StorehouseService storehouseService;

    @Autowired
    private void setStorehouseService(StorehouseService storehouseService){
        this.storehouseService = storehouseService;
    }

    @Override
    public ProductDto getById(Long id) {
        return from(productsRepository
                .findById(id)
                .orElseThrow(ProductNotExistException::new));
    }

    @Override
    public List<ProductDto> findAll() {
        return from(productsRepository
                .findAll());
    }

    @Override
    public void save(Long id, ProductDto productDto) {
        Storehouse storehouse = storehouseService.getStorehouseById(id);

        productsRepository.save(Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .storehouse(storehouse)
                .build());
    }
}
