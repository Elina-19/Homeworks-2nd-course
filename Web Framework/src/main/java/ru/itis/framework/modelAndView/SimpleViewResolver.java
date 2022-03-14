package ru.itis.framework.modelAndView;

import lombok.Data;

@Data
public class SimpleViewResolver implements ISimpleViewResolver {
    private static final String DEFAULT_PREFIX = "/WEB-INF/templates/";
    private static final String DEFAULT_SUFFIX = "";

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
    public String resolveViewName(String viewName) {
        return prefix + viewName + suffix;
    }
}
