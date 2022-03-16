package ru.itis.framework.util;

import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.entities.SimpleController;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.modelAndView.ModelAndView;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandlerControllerMethods {

    private Map<String, Class> methodAnnotations;

    public void init(){
        methodAnnotations = new HashMap<>();
        methodAnnotations.put(HttpMethod.GET, Get.class);
        methodAnnotations.put(HttpMethod.POST, Post.class);
    }

    //оздать карту с респонсом, реквестом, модель вью, у метода брать типы принимаемых аргументов,
    //если такой тип есть в этой карте, тон он, если нет, то энтитименеджер и бин из класса пользввателя

    public String handleRequest(SimpleController controller, SimpleRequest request, ModelAndView modelAndView){
        Class<Annotation> requestAnnotation = methodAnnotations.get(request.getMethod());
        Method[] methods = controller.getClass().getMethods();

        String viewName = null;
        try {
            boolean flag = true;
            for (Method m : methods) {
                System.out.println(m.getName());
                Annotation[] annotations = m.getDeclaredAnnotations();
                List<Class> annotationClasses = Arrays.asList(annotations).stream().
                        map(e -> e.annotationType()).
                        collect(Collectors.toList());

                if (annotationClasses.contains(requestAnnotation)) {
                    System.out.println("me");
                    if (!flag) {
                        throw new IllegalArgumentException("2 same methods");
                    }
                    viewName = (String) m.invoke(controller, modelAndView);
                    modelAndView.setName(viewName);
                    flag = false;
                }
            }

            return viewName;
        }catch (IllegalAccessException | InvocationTargetException e){
            throw new IllegalArgumentException("problems with reflection");
        }
    }
}
