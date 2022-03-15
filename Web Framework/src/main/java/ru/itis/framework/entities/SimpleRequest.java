package ru.itis.framework.entities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    public String getContextPath(){
        return servletRequest.getContextPath();
    }

    public String getRequestPath(){
        String requestUri = servletRequest.getRequestURI();
        return requestUri.substring(servletRequest.getContextPath().length());
    }

    public void forward(SimpleResponse response, String path) throws ServletException, IOException {
        servletRequest.getRequestDispatcher(path).forward(servletRequest, response.getServletResponse());
    }
}
