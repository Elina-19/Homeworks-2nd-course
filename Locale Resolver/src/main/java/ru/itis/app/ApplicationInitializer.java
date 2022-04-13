package ru.itis.app;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import ru.itis.config.ApplicationConfig;

public class ApplicationInitializer extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
        cxt.register(ApplicationConfig.class);
        return cxt;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
