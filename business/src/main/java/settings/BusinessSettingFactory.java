package settings;

import com.smirix.settings.Setting;
import com.smirix.settings.SettingFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика настроек
 * Created by Виктор on 20.05.2017.
 */
public class BusinessSettingFactory extends SettingFactory {

    @Override
    protected Map<Class<? extends Setting>, ? super Setting> load() {
        Map<Class<? extends Setting>, ? super Setting> settings = new HashMap<>();

        settings.put(ApplicationGlobalSetting.class, new ApplicationGlobalSetting("/application.properties"));

        return settings;
    }
}
