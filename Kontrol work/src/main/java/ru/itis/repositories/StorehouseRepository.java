package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Storehouse;

public interface StorehouseRepository extends JpaRepository<Storehouse, Long> {
}
