package ru.itis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validator.CollectionFieldsValidator;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@CollectionFieldsValidator(fields = {"test1", "test2"})
public class CollectionsFieldsTest {
    private List<String> test1;
    private Set<Integer> test2;
}
