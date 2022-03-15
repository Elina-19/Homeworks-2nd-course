package ru.itis.framework.routing;

import org.springframework.context.ApplicationContext;
import ru.itis.framework.entities.SimpleController;

public interface ISimpleHandlerMapper{
    SimpleController getHandler(String name);
    boolean hasRoute(String path);
    void init(ApplicationContext context);
}
