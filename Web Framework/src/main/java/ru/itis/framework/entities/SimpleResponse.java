package ru.itis.framework.entities;

import javax.servlet.http.HttpServletResponse;

public class SimpleResponse {
    private HttpServletResponse servletResponse;

    public SimpleResponse(HttpServletResponse response){
        this.servletResponse = response;
    }

    public void setStatus(int statusCode){
        servletResponse.setStatus(statusCode);
    }
}
