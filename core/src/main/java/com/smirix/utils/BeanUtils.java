package com.smirix.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Виктор on 06.01.2019.
 */
public class BeanUtils {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("com/smirix/applicationContext.xml");

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return context.getBean(name, type);
    }

    public static <T> T createBean(Class<T> tClass) {
        return context.getBean(tClass);
    }
}
