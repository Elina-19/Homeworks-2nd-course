package ru.itis.util;

import javax.servlet.http.HttpServletRequest;

public class LanguageChangeUtil {

    public static String getNewPath(HttpServletRequest request){

        String requestURL = request.getRequestURL().toString();
        String lang = request.getParameter("lang");

        String newPath = requestURL.substring(0, requestURL.lastIndexOf("/"));

        if (lang == null){
            return newPath;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(newPath.substring(0, requestURL.lastIndexOf("://") + 3));
        sb.append(lang);
        sb.append(newPath.substring(requestURL.indexOf(".")));

        return sb.toString();
    }
}
