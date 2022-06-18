package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ProductDto;
import ru.itis.services.StorehouseService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/storehouse")
public class StorehouseController {

    private final StorehouseService storehouseService;

    @GetMapping
    public String getAddProductPage(){
        return "storehouse";
    }

    @PostMapping("/{storehouse-id}/supply")
    public String addSupply(@PathVariable("storehouse-id") Long id, ProductDto productDto){
        storehouseService.saveSupply(id, productDto);

        return "redirect:/storehouse";
    }
}
