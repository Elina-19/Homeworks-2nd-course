package ru.itis.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validator.CollectionFieldsValidator;

@Data
@NoArgsConstructor
@CollectionFieldsValidator(fields = {"test1", "test2"})
public class Test2 {
    private Integer test;
}
