package com.smirix.settings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виктор on 04.01.2019.
 */
public abstract class SettingFactory {
    private static volatile Map<Class<? extends Setting>, ? super Setting> settings = new HashMap<>();

    public SettingFactory () {
        this.settings = load();
    }

    public static <T extends Setting> T getSetting(Class<T> configKey) {
        return (T) settings.get(configKey);
    }

    protected abstract Map<Class<? extends Setting>, ? super Setting> load();
}
