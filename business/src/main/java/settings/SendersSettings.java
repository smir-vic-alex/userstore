package settings;

import com.smirix.settings.SenderSettingBase;

/**
 * Created by Виктор on 06.01.2019.
 */
public class SendersSettings extends SenderSettingBase {

    public SendersSettings(String fileName) {
        super(fileName);
    }

    @Override
    public String getScUrl(Class type) {
        return getProperty(type.getName() + ".service.consumer.url");
    }

    @Override
    public String getScName(Class type) {
        return getProperty(type.getName() + ".service.consumer.name");
    }

    @Override
    public String getScHost(Class type) {
        return getProperty(type.getName() + ".service.consumer.host");
    }
}
