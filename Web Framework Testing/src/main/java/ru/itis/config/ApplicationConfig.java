package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.framework.modelAndView.ISimpleViewResolver;
import ru.itis.framework.modelAndView.SimpleViewResolver;
import ru.itis.framework.routing.ISimpleHandlerMapper;
import ru.itis.framework.routing.SimpleHandlerMapper;

@Configuration
@ComponentScan("ru.itis.controllers")
public class ApplicationConfig {
    @Bean
    public ISimpleViewResolver iSimpleViewResolver(){
        ISimpleViewResolver viewResolver = new SimpleViewResolver("/WEB-INF/jsp/", ".jsp");
        return viewResolver;
    }

    @Bean
    public ISimpleHandlerMapper iSimpleHandlerMapper(){
        ISimpleHandlerMapper handlerMapper = new SimpleHandlerMapper("C:\\Users\\Репозитории\\Homeworks-2nd-course\\Web Framework Testing\\src\\main\\java\\ru\\itis\\controllers");
        return handlerMapper;
    }
//    @Bean
//    public ViewResolver viewResolver() {
//        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
//        viewResolver.setPrefix("");
//        viewResolver.setSuffix(".ftlh");
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        return viewResolver;
//    }
}
