package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.itis.viewResolver.SimpleViewResolver;

@ComponentScan("ru.itis.controllers")
@EnableWebMvc
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        //InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        SimpleViewResolver resolver = new SimpleViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRedirectContextRelative(false);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("tests");
    }
}
