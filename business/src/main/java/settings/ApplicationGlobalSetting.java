package settings;

/**
 * Created by Виктор on 05.09.2017.
 */
public class ApplicationGlobalSetting extends Setting {

    private boolean isUseStub;

    public ApplicationGlobalSetting(String fileName) {
        super(fileName);
    }

    @Override
    public void refreshPropertiesByCustomConfig() {
        isUseStub = Boolean.parseBoolean(getProperty("settings.application.use.stub"));
    }

    public boolean isUseStub() {
        return isUseStub;
    }
}
