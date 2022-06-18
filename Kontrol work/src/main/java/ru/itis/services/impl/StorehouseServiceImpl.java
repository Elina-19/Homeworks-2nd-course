package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductDto;
import ru.itis.exceptions.StorehouseHotExistException;
import ru.itis.models.Storehouse;
import ru.itis.repositories.StorehouseRepository;
import ru.itis.services.ProductsService;
import ru.itis.services.StorehouseService;

@RequiredArgsConstructor
@Service
public class StorehouseServiceImpl implements StorehouseService {

    private final StorehouseRepository storehouseRepository;

    @Lazy
    private ProductsService productsService;

    @Autowired
    private void setProductsService(ProductsService productsService){
        this.productsService = productsService;
    }

    @Override
    public Storehouse getStorehouseById(Long id) {
        return storehouseRepository
                .findById(id)
                .orElseThrow(StorehouseHotExistException::new);
    }

    @Override
    public void saveSupply(Long id, ProductDto productDto) {
        productsService.save(id, productDto);
    }
}
