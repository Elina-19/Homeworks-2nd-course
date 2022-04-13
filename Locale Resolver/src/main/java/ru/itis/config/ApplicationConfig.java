package ru.itis.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.itis.ISimpleLocaleResolver;
import ru.itis.SimpleLocaleResolver;

import java.util.Locale;

@EnableWebMvc
@ComponentScan("ru.itis")
@Configuration
public class ApplicationConfig {
    @Bean
    public ViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRedirectContextRelative(false);
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("classpath:i18n/messages", "classpath:i18n/validation");
        res.setCacheSeconds(0);
        res.setDefaultEncoding("UTF-8");
        res.setUseCodeAsDefaultMessage(false);
        return res;
    }
}
