package ru.itis.viewResolver;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

public class SimpleViewResolver extends InternalResourceViewResolver{
    private static final String MY_STATUS = "status:";

//    @Override
//    protected View createView(String viewName, Locale locale) throws Exception {
//        if (!this.canHandle(viewName, locale)) {
//            return null;
//        } else {
//            String forwardUrl;
//            if (viewName.startsWith("redirect:")) {
//                forwardUrl = viewName.substring("redirect:".length());
//                RedirectView view = new RedirectView(forwardUrl, this.isRedirectContextRelative(), this.isRedirectHttp10Compatible());
//                String[] hosts = this.getRedirectHosts();
//                if (hosts != null) {
//                    view.setHosts(hosts);
//                }
//
//                return this.applyLifecycleMethods("redirect:", view);
//            } else if (viewName.startsWith("forward:")) {
//                forwardUrl = viewName.substring("forward:".length());
//                InternalResourceView view = new InternalResourceView(forwardUrl);
//                return this.applyLifecycleMethods("forward:", view);
//            } else {
//                return super.createView(viewName, locale);
//            }
//        }
//    }

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        if (!this.canHandle(viewName, locale)) {
            return null;
        } else {
            String forwardUrl;
            if (viewName.startsWith(MY_STATUS)) {
                forwardUrl = viewName.substring(MY_STATUS.length());
                Integer statusCode = Integer.valueOf(forwardUrl);

                if (statusCode < 100 || statusCode > 500){
                    throw new IllegalArgumentException("Incorrect code");
                }

                if (statusCode == 301 || statusCode == 302) {
                    RedirectView view = new RedirectView(forwardUrl, this.isRedirectContextRelative(), this.isRedirectHttp10Compatible());
                    view.setStatusCode(HttpStatus.valueOf(statusCode));
                    String[] hosts = this.getRedirectHosts();
                    if (hosts != null) {
                        view.setHosts(hosts);
                    }

                    System.out.println("redirect");
                    return this.applyLifecycleMethods("status:", view);
                }else {
                    System.out.println(forwardUrl);
                    InternalResourceView view = new InternalResourceView(forwardUrl);
                    return this.applyLifecycleMethods("forward:", view);
                }
            } else {
                return super.createView(viewName, locale);
            }
        }
    }
}
