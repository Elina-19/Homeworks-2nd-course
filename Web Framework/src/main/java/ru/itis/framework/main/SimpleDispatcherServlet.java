package ru.itis.framework.main;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
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
import java.io.IOException;

@NoArgsConstructor
public class SimpleDispatcherServlet extends HttpServlet {

    private ISimpleHandlerMapper handlerMapper;
    private ApplicationContext context;
    private ISimpleViewResolver viewResolver;

    private HandlerControllerMethods handlerControllerMethods;

    public SimpleDispatcherServlet(ApplicationContext context){
        this.context = context;
        myInit();
    }

    public void myInit() {
        handlerControllerMethods = new HandlerControllerMethods();
        handlerControllerMethods.init();

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

            handlerControllerMethods.handleRequest(controller, request, response, modelAndView);
            viewResolver.resolveViewName(modelAndView, request, response);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpResponse.getWriter().write("Page not found");
        }
    }
}

