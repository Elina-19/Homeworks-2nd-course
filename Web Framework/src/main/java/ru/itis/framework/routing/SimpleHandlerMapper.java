package ru.itis.framework.routing;

import org.springframework.context.ApplicationContext;
import ru.itis.framework.main.SimpleController;
import ru.itis.framework.annotations.SimpleRequestMapping;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleHandlerMapper implements ISimpleHandlerMapper{
    //.java
    private static final int FILE_SUFFIX_LENGTH = 5;

    private ApplicationContext context;

    private Map<String, SimpleController> controllers;
    private String pathToControllers;

    public SimpleHandlerMapper(String pathToControllers){
        this.pathToControllers = pathToControllers;
    }

    @Override
    public SimpleController getHandler(String name) {
        return controllers.get(name);
    }

    @Override
    public void init(ApplicationContext applicationContext){
        this.context = applicationContext;
        controllers = new HashMap<>();

        File controllersDirectory = new File (pathToControllers);
        if (controllersDirectory.exists()){
            List<File> controllerNames = Arrays.asList(controllersDirectory.listFiles());

            for (File file : controllerNames) {
                SimpleController controller = (SimpleController) context.getBean(getBeanName(file.getName()));

                SimpleRequestMapping annotation = controller.getClass().getAnnotation(SimpleRequestMapping.class);
                String[] requestPaths = annotation.path();

                for (String path : requestPaths) {
                    controllers.put(path, controller);
                }
            }
        }else {
            throw new IllegalArgumentException("Path to controller's directory is incorrect");
        }
    }

    @Override
    public boolean hasRoute(String path) {
        return controllers.containsKey(path);
    }

    //Turns a filename into a bean name
    //Example: SimpleController.java -> simpleController
    private String getBeanName(String file){
        String beanName = file.substring(0, file.length() - FILE_SUFFIX_LENGTH);
        beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);

        return beanName;
    }
}

