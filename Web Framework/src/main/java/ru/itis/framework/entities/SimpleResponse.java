package ru.itis.framework.entities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}

