package com.smirix.initializer;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Виктор on 06.10.2018.
 */
public class RestInitializer /*implements WebApplicationInitializer */ {

//    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new ServletContainer());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/rest/*");
        dispatcher.setInitParameter("jersey.config.server.provider.packages", "com.smirix.rest.endpoints;com.smirix.rest.filters");
        dispatcher.setInitParameter("contextInitializerClasses", "ApplicationContextInitializerImpl");

        servletContext.setInitParameter("contextInitializerClasses", "ApplicationContextInitializerImpl");
    }


}
