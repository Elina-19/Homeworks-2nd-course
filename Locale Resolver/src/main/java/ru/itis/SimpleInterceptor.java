package ru.itis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SimpleInterceptor implements HandlerInterceptor {

    private final ISimpleLocaleResolver localeResolver;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        localeResolver.setLocale(request, response);

        return true;
    }
}
