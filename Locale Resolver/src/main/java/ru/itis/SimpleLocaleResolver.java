package ru.itis;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;

@Data
@Component
public class SimpleLocaleResolver implements LocaleResolver {

    private final String LOCALE_ATTRIBUTE_NAME = "lang";

    private Locale defaultLocale;

    public SimpleLocaleResolver(){
        defaultLocale = Locale.US;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        String language = requestURL.substring(requestURL.indexOf("://") + 3, requestURL.indexOf("."));

        Locale locale = new Locale(language);

        if (!Arrays.asList(
                Locale.getAvailableLocales())
                .contains(locale)){

            locale = defaultLocale;
        }

        request.setAttribute(LOCALE_ATTRIBUTE_NAME, locale);

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.setAttribute(LOCALE_ATTRIBUTE_NAME, locale);
    }
}
