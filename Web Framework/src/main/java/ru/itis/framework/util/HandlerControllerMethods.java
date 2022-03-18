package ru.itis.framework.util;

import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.main.SimpleController;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;
import ru.itis.framework.exceptions.InaccessibleException;
import ru.itis.framework.exceptions.MethodNotSupported;
import ru.itis.framework.exceptions.RequestCouldNotBeProcessed;
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

    public void handleRequest(SimpleController controller, SimpleRequest request,
                                SimpleResponse response, ModelAndView modelAndView){
        Class<Annotation> requestMethodAnnotation = methodAnnotations.get(request.getMethod());
        Method[] methods = controller.getClass().getMethods();

        String viewName;
        try {
            //If a method with such an annotation has already been found,
            //then the flag is false. 2 methods with the same request method annotation cannot be
            boolean flag = true;

            for (Method m : methods) {
                if (containsRequestMethodAnnotation(m, requestMethodAnnotation)) {
                    if (!flag) {
                        throw new RequestCouldNotBeProcessed("Controller has more than 1 method handling the same request");
                    }

                    Object[] parameters = getParameters(Arrays.asList(m.getParameterTypes()),
                            request, response, modelAndView);

                    viewName = (String) m.invoke(controller, parameters);
                    modelAndView.setName(viewName);

                    flag = false;
                }
            }

            if (flag) {
                throw new MethodNotSupported("That method is not supported with Controller");
            }
        }catch (IllegalAccessException | InvocationTargetException e){
            throw new InaccessibleException("Method in controller should be public", e);
        }
    }

    private boolean containsRequestMethodAnnotation(Method m, Class<Annotation> requestMethodAnnotation){
        Annotation[] annotations = m.getDeclaredAnnotations();
        List<Class> annotationClasses = Arrays.asList(annotations).stream().
                map(e -> e.annotationType()).
                collect(Collectors.toList());

        return annotationClasses.contains(requestMethodAnnotation);
    }

    private Object[] getParameters(List<Class> parametersTypes, SimpleRequest request,
                                   SimpleResponse response, ModelAndView modelAndView){
        List<Object> parameters = new ArrayList<>();

        for (Class parameterType: parametersTypes) {
            //The flag false indicates that a new entity needs to be created, bc
            //it's not base parameter
            boolean fl = true;

            //TODO сделать карту параметров
            if (parameterType.equals(SimpleRequest.class)) {
                parameters.add(request);
                fl = false;
            }

            if (fl && parameterType.equals(SimpleResponse.class)) {
                parameters.add(response);
                fl = false;
            }

            if (fl && parameterType.equals(ModelAndView.class)) {
                parameters.add(modelAndView);
                fl = false;
            }

            if (fl){
                Object param = entityManager.getEntity(request, parameterType);
                parameters.add(param);
            }
        }

        return parameters.toArray();
    }
}

