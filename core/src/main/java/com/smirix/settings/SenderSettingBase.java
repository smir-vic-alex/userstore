package com.smirix.settings;

import com.smirix.application.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Виктор on 06.01.2019.
 */
public abstract class SenderSettingBase extends Setting {

    @Autowired
    private ApplicationConfig applicationConfig;

    public SenderSettingBase(String fileName) {
        super(fileName);
    }

    public abstract String getScUrl(Class type);
    public abstract String getScName(Class type);
    public abstract String getScHost(Class type);

    public String getSpName() {
        return applicationConfig.getApplicationName();
    }

}
