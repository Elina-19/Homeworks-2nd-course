package ru.itis.framework.modelAndView;

import lombok.Data;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

@Data
public class SimpleViewResolver implements ISimpleViewResolver {
    private static final String DEFAULT_PREFIX = "/WEB-INF/templates/";
    private static final String DEFAULT_SUFFIX = "";

    private static final String REDIRECT = "redirect:";

    private String prefix;
    private String suffix;

    public SimpleViewResolver(){
        this.prefix = DEFAULT_PREFIX;
        this.suffix = DEFAULT_SUFFIX;
    }

    public SimpleViewResolver(String prefix, String suffix){
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public void resolveViewName(ModelAndView modelAndView, SimpleRequest request, SimpleResponse response) throws ServletException, IOException {
        String viewName = modelAndView.getName();

        for (Map.Entry<String, Object> entry: modelAndView.getData().entrySet()){
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        if (isRedirect(viewName)){
            response.redirect(request.getContextPath() + viewName.substring(REDIRECT.length()));
        }else {
            String fullViewName = getFullViewName(viewName);
            request.forward(response, fullViewName);
        }
    }

    private String getFullViewName(String viewName){
        return prefix + viewName + suffix;
    }

    private boolean isRedirect(String viewName) {
        if (viewName.length() < REDIRECT.length()){
            return false;
        }
        return viewName.substring(0, 9).equals(REDIRECT);
    }
}

