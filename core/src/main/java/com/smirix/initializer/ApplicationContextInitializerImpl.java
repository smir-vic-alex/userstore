package com.smirix.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Виктор on 09.10.2018.
 */
public class ApplicationContextInitializerImpl implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
//        applicationContext.getBeanFactory();
        System.out.println("ApplicationContextInitializerImpl!!!!");
    }
}
