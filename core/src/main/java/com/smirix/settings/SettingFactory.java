package com.smirix.settings;


import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика настроек
 * Created by Виктор on 20.05.2017.
 */
public abstract class SettingFactory {

    private static volatile Map<Class<? extends Setting>, ? super Setting> settings = new HashMap<>();

    public static <T extends Setting> T getSetting(Class<T> configKey) {
        return (T) settings.get(configKey);
    }

    public static <T extends Setting> void addSetting(Class<T> type, T setting) {
        settings.put(type, setting);
    }

}
