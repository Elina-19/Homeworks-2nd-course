package ru.itis.framework.routing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import ru.itis.framework.entities.Controller;
import ru.itis.framework.entities.SimpleRequestMapping;
import ru.itis.framework.exceptions.IllegalControllerName;
import ru.itis.framework.exceptions.IllegalPathToDirectory;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleHandlerMapper implements ISimpleHandlerMapper{

    private ApplicationContext context;

    private Map<String, Controller> controllers;
    private String pathToControllers;

    public SimpleHandlerMapper(String pathToControllers){
        this.pathToControllers = pathToControllers;
    }

    @Override
    public Controller getHandler(String name) {
        return controllers.get(name);
    }

    @Override
    public void init(ApplicationContext applicationContext){
        this.context = applicationContext;
        controllers = new HashMap<>();
//        File controllersDirectory = new File(pathToControllers).getAbsoluteFile();
//        System.out.println(controllersDirectory.getPath());
        File controllersDirectory = new File ("C:\\Users\\Репозитории\\Homeworks-2nd-course\\Framework Testing\\src\\main\\java\\ru\\itis\\controllers");

        if (controllersDirectory.exists()){
            List<File> controllerNames = Arrays.asList(controllersDirectory.listFiles());

            for (File file : controllerNames) {
                String beanName = file.getName().substring(0, file.getName().length() - 5);
                Controller controller = (Controller) context.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));

                System.out.println(controller.getClass().getName());
                SimpleRequestMapping annotation = controller.getClass().getAnnotation(SimpleRequestMapping.class);
                System.out.println(annotation);
                String[] requestPaths = annotation.path();

                for (String path : requestPaths) {
                    controllers.put(path, controller);
                }
            }
        }else {
            throw new IllegalPathToDirectory("Path to controller's directory is incorrect");
        }
    }

    @Override
    public boolean hasRoute(String path) {
        return controllers.containsKey(path);
    }
}
