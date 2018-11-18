package settings.vk;

import settings.Setting;

/**
 * Общие настройки работы с networks.vk API
 * Created by Виктор on 20.05.2017.
 */
public class VKApiSetting extends Setting {

    private String applicationSecretKey;
    private Integer applicationId;
    private String applicationRedirectUri;
    private String version;

    public VKApiSetting(String fileName) {
        super(fileName);
    }

    @Override
    public void refreshPropertiesByCustomConfig() {
        applicationSecretKey = getProperty("settings.networks.vk.VKApiSetting.application.secret.key");
        applicationId = Integer.parseInt(getProperty("settings.networks.vk.VKApiSetting.application.id"));
        applicationRedirectUri = getProperty("settings.networks.vk.VKApiSetting.application.redirect.uri");
        version = getProperty("settings.networks.vk.VKApiSetting.application.version");
    }

    public String getApplicationSecretKey() {
        return applicationSecretKey;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public String getApplicationRedirectUri() {
        return applicationRedirectUri;
    }

    public String getVersion() {
        return version;
    }

}
