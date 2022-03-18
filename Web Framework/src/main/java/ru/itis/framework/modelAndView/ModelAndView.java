package ru.itis.framework.modelAndView;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private String name;
    private Map<String, Object> data;

    public ModelAndView(){
        data = new HashMap<>();
    }

    public ModelAndView(String name){
        this(name, new HashMap<>());
    }

    public ModelAndView(String name, Map<String, Object> data){
        this.name = name;
        this.data = data;
    }

    public void addAttribute(String name, Object attr){
        data.put(name, attr);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

