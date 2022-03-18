package ru.itis.framework.util;

import lombok.NoArgsConstructor;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.exceptions.EntityIsUnavailable;

import java.lang.reflect.Field;

@NoArgsConstructor
public class EntityManager {
    public Object getEntity(SimpleRequest request, Class cl){
        try {
            Object ob = cl.newInstance();
            Field[] fields = cl.getDeclaredFields();

            for (Field field: fields){
                field.setAccessible(true);

                String fieldName = field.getName();
                if (request.getParameter(fieldName) != null){
                    field.set(ob, request.getParameter(fieldName));
                }
            }

            return ob;
        }catch (IllegalAccessException | InstantiationException e){
            throw new EntityIsUnavailable("The entity can not be created", e);
        }
    }
}

