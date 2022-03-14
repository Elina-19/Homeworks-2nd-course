package ru.itis.framework.main;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import ru.itis.framework.entities.Controller;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;
import ru.itis.framework.exceptions.BeanNotFound;
import ru.itis.framework.modelAndView.ModelAndView;
import ru.itis.framework.modelAndView.SimpleViewResolver;
import ru.itis.framework.modelAndView.ISimpleViewResolver;
import ru.itis.framework.routing.ISimpleHandlerMapper;
import ru.itis.framework.routing.SimpleHandlerMapper;
import ru.itis.framework.routing.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@NoArgsConstructor
public class SimpleDispatcherServlet extends HttpServlet {

    private ISimpleHandlerMapper handlerMapper;
    private ApplicationContext context;
    private ISimpleViewResolver viewResolver;

    public SimpleDispatcherServlet(ApplicationContext context){
        this.context = context;
        myInit();
    }

    public void myInit() {
        context.getBean(Test.class).test();
        try {
            handlerMapper = context.getBean(ISimpleHandlerMapper.class);
            handlerMapper.init(context);
        }
        catch(NoSuchBeanDefinitionException e){
            throw new BeanNotFound("Bean HandlerMapper did not find", e);
        }

        try{
            viewResolver = context.getBean(ISimpleViewResolver.class);
        }catch (NoSuchBeanDefinitionException e){
            viewResolver = new SimpleViewResolver();
        }
    }

    @Override
    public void service(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        String requestUri = httpRequest.getRequestURI();
        String requestPath = requestUri.substring(httpRequest.getContextPath().length());

        SimpleRequest request = new SimpleRequest(httpRequest);
        SimpleResponse response = new SimpleResponse(httpResponse);

        if (handlerMapper.hasRoute(requestPath)){
            Controller controller = handlerMapper.getHandler(requestPath);
            ModelAndView modelAndView = new ModelAndView();
            String viewName = null;

            //TODO найти класс с константами
            switch (request.getMethod()){
                case "GET":
                    viewName = controller.doGet(modelAndView);
                    break;
                case "POST":
                    viewName = controller.doPost(modelAndView);
                    break;
                default:
                    throw new IllegalArgumentException("That method doesn't exist");
            }
            modelAndView.setName(viewName);

            for (Map.Entry<String, Object> entry: modelAndView.getData().entrySet()){
                request.setAttribute(entry.getKey(), entry.getValue());
            }

            this.getServletContext().getRequestDispatcher(viewResolver.resolveViewName(viewName)).forward(httpRequest, httpResponse);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpResponse.getWriter().write("Page not found");
        }
    }
}
