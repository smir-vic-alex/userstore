package settings;

import com.smirix.settings.Setting;

/**
 * Created by Виктор on 05.09.2017.
 */
public class ApplicationGlobalSetting extends Setting {

    private boolean isUseStub;

    public ApplicationGlobalSetting(String fileName) {
        super(fileName);
    }

    public boolean isUseStub() {
        return Boolean.parseBoolean(getProperty("settings.application.use.stub"));
    }
}
