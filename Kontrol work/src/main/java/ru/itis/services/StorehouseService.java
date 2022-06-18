package ru.itis.services;

import ru.itis.dto.ProductDto;
import ru.itis.models.Storehouse;

public interface StorehouseService {
    Storehouse getStorehouseById(Long id);
    void saveSupply(Long id, ProductDto productDto);
}
