package ru.itis.framework.entities;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

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

    public Map<String, String[]> getParameterMap(){
        return servletRequest.getParameterMap();
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

    public HttpSession getSession() {
        return servletRequest.getSession();
    }

    public Cookie[] getCookies(){
        return servletRequest.getCookies();
    }

    public String getHeader(String var){
        return servletRequest.getHeader(var);
    }

    public Enumeration<String> getHeaders(String var){
        return servletRequest.getHeaders(var);
    }

    public Enumeration<String> getHeaderNames(){
        return servletRequest.getHeaderNames();
    }

    public void setCharacterEncoding(String var) throws UnsupportedEncodingException {
        servletRequest.setCharacterEncoding(var);
    }
}

