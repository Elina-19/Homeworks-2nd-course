package ru.itis.framework.entities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SimpleResponse {

    private HttpServletResponse servletResponse;

    public SimpleResponse(HttpServletResponse response){
        this.servletResponse = response;
    }

    public void setStatus(int statusCode){
        servletResponse.setStatus(statusCode);
    }

    public void redirect(String path) throws IOException{
        try {
            servletResponse.sendRedirect(path);
        }catch (IOException e){
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            servletResponse.getWriter().write("Page not found");
        }
    }

    public void addCookie(Cookie cookie){
        servletResponse.addCookie(cookie);
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    public void setCharacterEncoding(String var){
        servletResponse.setCharacterEncoding(var);
    }

    public void setContentType(String var){
        servletResponse.setContentType(var);
    }

    public void setHeader(String var, String var1){
        servletResponse.setHeader(var, var1);
    }

    public String getHeader(String var){
        return servletResponse.getHeader(var);
    }

    public Collection<String> getHeaders(String var){
        return servletResponse.getHeaders(var);
    }

    public Collection<String> getHeaderNames(){
        return servletResponse.getHeaderNames();
    }

    public void addHeader(String var, String var1){
        servletResponse.addHeader(var, var1);
    }
}

