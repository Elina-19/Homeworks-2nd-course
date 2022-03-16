package ru.itis.framework.main;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.entities.SimpleController;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;
import ru.itis.framework.exceptions.BeanNotFound;
import ru.itis.framework.modelAndView.ModelAndView;
import ru.itis.framework.modelAndView.SimpleViewResolver;
import ru.itis.framework.modelAndView.ISimpleViewResolver;
import ru.itis.framework.routing.ISimpleHandlerMapper;
import ru.itis.framework.util.HandlerControllerMethods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SimpleDispatcherServlet extends HttpServlet {

    private ISimpleHandlerMapper handlerMapper;
    private ApplicationContext context;
    private ISimpleViewResolver viewResolver;
    private HandlerControllerMethods requestHandler;

    public SimpleDispatcherServlet(ApplicationContext context){
        this.context = context;
        myInit();
    }

    public void myInit() {
        requestHandler = new HandlerControllerMethods();
        requestHandler.init();

        try {
            handlerMapper = context.getBean(ISimpleHandlerMapper.class);
            handlerMapper.init(context);
        }
        catch(NoSuchBeanDefinitionException e){
            throw new BeanNotFound("Bean HandlerMapper not found", e);
        }

        try{
            viewResolver = context.getBean(ISimpleViewResolver.class);
        }catch (NoSuchBeanDefinitionException e){
            viewResolver = new SimpleViewResolver();
        }
    }

    @Override
    public void service(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        SimpleRequest request = new SimpleRequest(httpRequest);
        SimpleResponse response = new SimpleResponse(httpResponse);

        String requestPath = request.getRequestPath();

        if (handlerMapper.hasRoute(requestPath)){
            SimpleController controller = handlerMapper.getHandler(requestPath);
            ModelAndView modelAndView = new ModelAndView();
            //String viewName;

            requestHandler.handleRequest(controller, request, modelAndView);

//            Class<Annotation> annotationName = methodAnnotations.get(request.getMethod());
//            Method[] methods = controller.getClass().getMethods();
//
//            try {
//                System.out.println(methods.length);
//                boolean flag = true;
//                for (Method m : methods) {
//                    Annotation[] annotations = m.getDeclaredAnnotations();
//                    //оздать карту с респонсом, реквестом, модель вью, у метода брать типы принимаемых аргументов,
//                    //если такой тип есть в этой карте, тон он, если нет, то энтитименеджер и бин из класса пользввателя
//                    System.out.println("annot: " + annotations.length);
//                    System.out.println(annotations[0].annotationType().getName());
//                    if (Arrays.asList(annotations).stream().map(e -> e.annotationType()).collect(Collectors.toList()).contains(annotationName)) {
//                        if (!flag){
//                            throw new IllegalArgumentException();
//                        }
//                        viewName = (String) m.invoke(controller, modelAndView);
//                        System.out.println(viewName);
//                        System.out.println(annotationName.getName());
//                        modelAndView.setName(viewName);
//                        flag = false;
//                    }
//                    System.out.println("here");
//                }
//            }catch (IllegalAccessException | InvocationTargetException e){
//                throw new IllegalArgumentException("Can't find method");
//            }
//            switch (request.getMethod()){
//                case HttpMethod.GET:
//
//                    viewName = controller.doGet(modelAndView);
//                    break;
//                case HttpMethod.POST:
//                    viewName = controller.doPost(modelAndView);
//                    break;
//                default:
//                    throw new IllegalArgumentException("That method doesn't exist");
//            }
//            modelAndView.setName(viewName);

            viewResolver.resolveViewName(modelAndView, request, response);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpResponse.getWriter().write("Page not found");
        }
    }
}
