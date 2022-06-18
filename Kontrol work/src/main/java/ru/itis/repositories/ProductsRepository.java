package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
