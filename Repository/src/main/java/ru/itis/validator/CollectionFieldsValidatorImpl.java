package ru.itis.validator;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class CollectionFieldsValidatorImpl implements ConstraintValidator<CollectionFieldsValidator, Object> {
    private String[] fields;

    @Override
    public void initialize(CollectionFieldsValidator constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(o);

        try {
            for (String field : fields) {
                Collection<?> collection = (Collection<?>) wrapper.getPropertyValue(field);

                if (collection == null || collection.isEmpty()) {
                    return false;
                }
            }
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Field is not collection");
        }catch (BeansException e){
            throw new IllegalArgumentException("Field does not exist");
        }

        return true;
    }
}
