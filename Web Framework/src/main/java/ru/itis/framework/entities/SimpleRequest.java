package ru.itis.framework.entities;

import javax.servlet.http.HttpServletRequest;

public class SimpleRequest {
    private HttpServletRequest servletRequest;

    public SimpleRequest(HttpServletRequest request){
        this.servletRequest = request;
    }

    public void setAttribute(String name, Object attr){
        servletRequest.setAttribute(name, attr);
    }

    public String getPathWithoutContext(){
        String requestPath = servletRequest.getRequestURI();
        return requestPath.substring(requestPath.length());
    }

    public String getParameter(String name){
        return servletRequest.getParameter(name);
    }

    public String getMethod(){
        return servletRequest.getMethod();
    }
}
