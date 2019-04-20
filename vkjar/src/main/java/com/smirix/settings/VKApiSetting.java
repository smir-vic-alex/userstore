package com.smirix.settings;


import com.smirix.entities.ActorType;
import com.smirix.services.VKUtils;

import java.util.List;

/**
 * Общие настройки работы с networks.vk API
 * Created by Виктор on 20.05.2017.
 */
public class VKApiSetting extends Setting {
    private static final String PREFIX = "com.smirix.settings.VKApiSetting.";
    private static final String USER_PREFIX = PREFIX + "user.";
    private static final String GROUP_PREFIX = PREFIX + "group.";
    private static final String DIFF_MINUTES_BEFORE_POST_KEY = PREFIX + "diff.minutes.before.post";

    public VKApiSetting(String fileName) {
        super(fileName);
    }

    public String getApplicationSecretKey() {
        return getProperty(PREFIX + "application.secret.key");
    }

    public Integer getApplicationId() {
        return Integer.parseInt(getProperty(PREFIX + "application.id"));
    }

    public String getApplicationRedirectUri() {
        return getProperty(PREFIX + "application.redirect.uri");
    }

    public String getVersion() {
        return getProperty(PREFIX + "application.version");
    }

    public String getAuthUrl(ActorType type, List<String> ids) {
        String prefix;
        if (ActorType.USER == type) {
            prefix = USER_PREFIX;
        } else if (ActorType.GROUP == type) {
            prefix = GROUP_PREFIX;
        } else {
            return null;
        }
        String appAuthorizeConnectionUrl = getProperty(prefix + "application.authorize.connection.url");
        String appDisplay = getProperty(prefix + "application.display");
        String responseType = getProperty(prefix + "response.type");
        String scope = getProperty(prefix + "scope");

        if (ActorType.GROUP == type)
            appAuthorizeConnectionUrl = VKUtils.insertGroupIdIntoRequestUrl(ids, appAuthorizeConnectionUrl);

        return String.format(appAuthorizeConnectionUrl, getApplicationId(),
                appDisplay, getApplicationRedirectUri(),
                responseType, scope, getVersion());
    }

    public int getDiffMinutesBeforePost() {
        return Integer.parseInt(getProperty(DIFF_MINUTES_BEFORE_POST_KEY));
    }
}
