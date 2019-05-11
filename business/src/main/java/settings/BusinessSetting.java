package settings;

import com.smirix.settings.Setting;

/**
 * Фабрика настроек
 * Created by Виктор on 20.05.2017.
 */
public class BusinessSetting extends Setting {

    private static final String PREFIX = "settings.BusinessSetting.";
    private static final String FILE_PATH_KEY = PREFIX + "file.path";

    public BusinessSetting(String fileName) {
        super(fileName);
    }

    public String getFilePath() {
        return getProperty(FILE_PATH_KEY);
    }
}
