package ru.itis.framework.util;

import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.entities.SimpleController;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;
import ru.itis.framework.exceptions.MethodNotSupported;
import ru.itis.framework.modelAndView.ModelAndView;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class HandlerControllerMethods {

    private Map<String, Class> methodAnnotations;
    private EntityManager entityManager;

    public void init(){
        methodAnnotations = new HashMap<>();
        entityManager = new EntityManager();
        methodAnnotations.put(HttpMethod.GET, Get.class);
        methodAnnotations.put(HttpMethod.POST, Post.class);
    }

    //оздать карту с респонсом, реквестом, модель вью, у метода брать типы принимаемых аргументов,
    //если такой тип есть в этой карте, тон он, если нет, то энтитименеджер и бин из класса пользввателя

    public String handleRequest(SimpleController controller, SimpleRequest request, SimpleResponse response, ModelAndView modelAndView){
        Class<Annotation> requestAnnotation = methodAnnotations.get(request.getMethod());
        Method[] methods = controller.getClass().getMethods();

        String viewName = null;
        try {
            boolean flag = true;
            for (Method m : methods) {
                Annotation[] annotations = m.getDeclaredAnnotations();
                List<Class> annotationClasses = Arrays.asList(annotations).stream().
                        map(e -> e.annotationType()).
                        collect(Collectors.toList());

                if (annotationClasses.contains(requestAnnotation)) {
                    if (!flag) {
                        throw new IllegalArgumentException("Controller shouldn't have two same methods");
                    }
                    List<Class> parametersTypes = Arrays.asList(m.getParameterTypes());
                    List<Object> params = new ArrayList<>();

                    for (Class ob: parametersTypes) {
                        boolean fl = true;

                        if (fl && ob.equals(SimpleRequest.class)) {
                            params.add(request);
                            fl = false;
                        }

                        if (fl && ob.equals(SimpleResponse.class)) {
                            params.add(response);
                            fl = false;
                        }

                        if (fl && ob.equals(ModelAndView.class)) {
                            params.add(modelAndView);
                            fl = false;
                        }

                        if (fl){
                            Object param = entityManager.getEntity(request, ob);
                            params.add(param);
                        }
                    }

                    viewName = (String) m.invoke(controller, params.toArray());
                    modelAndView.setName(viewName);
                    flag = false;
                }
            }

            if (flag){
                throw new MethodNotSupported("That method is not supported with Controller");
            }

            return viewName;
        }catch (IllegalAccessException | InvocationTargetException e){
            throw new IllegalArgumentException("problems with reflection");
        }
    }
}
