package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.framework.modelAndView.ISimpleViewResolver;
import ru.itis.framework.modelAndView.SimpleViewResolver;
import ru.itis.framework.routing.ISimpleHandlerMapper;
import ru.itis.framework.routing.SimpleHandlerMapper;
import ru.itis.framework.routing.Test;

@Configuration
@ComponentScan("test.controllers")
public class ApplicationConfig {
    @Bean
    public ISimpleViewResolver iSimpleViewResolver(){
        ISimpleViewResolver viewResolver = new SimpleViewResolver("/WEB-INF/jsp/", ".jsp");
        return viewResolver;
    }

    @Bean
    public ISimpleHandlerMapper iSimpleHandlerMapper(){
        ISimpleHandlerMapper handlerMapper = new SimpleHandlerMapper("");
        return handlerMapper;
    }

    @Bean
    public Test test(){
        return new Test();
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
