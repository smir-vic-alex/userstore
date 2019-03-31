package com.smirix.rest.helpers;

import com.smirix.entities.VKGroupActor;
import com.smirix.entities.VKUserActor;
import com.vk.api.sdk.objects.UserAuthResponse;

import java.util.Map;

/**
 * Created by Виктор on 07.01.2019.
 */
public class ActorHelper {

    public VKGroupActor getVkGroupActor(Long userId, Map.Entry<Integer, String> entry) {
        VKGroupActor vkGroupNetwork = new VKGroupActor();
        vkGroupNetwork.setVkAccessCode(entry.getValue());
        vkGroupNetwork.setVkUserId(entry.getKey());
        vkGroupNetwork.setUserId(userId);
        vkGroupNetwork.setType("G");
        return vkGroupNetwork;
    }

    public VKUserActor getVkUserActor(UserAuthResponse vkRs, Long userId) {
        VKUserActor vkUserActor = new VKUserActor();
        vkUserActor.setVkAccessCode(vkRs.getAccessToken());
        vkUserActor.setVkUserId(vkRs.getUserId());
        vkUserActor.setUserId(userId);
        vkUserActor.setType("U");
        return vkUserActor;
    }
}
