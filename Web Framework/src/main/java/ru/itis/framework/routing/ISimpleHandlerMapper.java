package ru.itis.framework.routing;

import org.springframework.context.ApplicationContext;
import ru.itis.framework.entities.Controller;

public interface ISimpleHandlerMapper{
    Controller getHandler(String name);
    boolean hasRoute(String path);
    void init(ApplicationContext context);
}
