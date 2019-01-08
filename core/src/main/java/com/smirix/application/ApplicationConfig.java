package com.smirix.application;

/**
 * Created by Виктор on 06.01.2019.
 */
public class ApplicationConfig {

    private String applicationName;

    public ApplicationConfig(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
