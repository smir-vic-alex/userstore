package com.smirix.rest.helpers;

import com.smirix.entities.ActorType;
import com.smirix.entities.VKGroupActor;
import com.smirix.entities.VKUserActor;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.services.VKConnectorManager;
import com.smirix.services.VkService;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * Created by Виктор on 07.01.2019.
 */
public class ActorHelper {

    @Autowired
    @Qualifier("vkConnectorManager")
    private VKConnectorManager vkConnectorManager;

    @Autowired
    @Qualifier("vkService")
    private VkService vkService;

    public Object authActor(AuthActorRq actorRq) {
        String code = actorRq.getCode();
        ActorType type = actorRq.getActorType();

        if (ActorType.USER == type) {
            return vkConnectorManager.authUser(code);
        } else if (ActorType.GROUP == type) {
            return vkConnectorManager.authGroup(code);
        } else {
            throw new RuntimeException("Неизвестный тип actorType " + type);
        }
    }

    public void saveOrUpdateVkCode(AuthActorRq authActorRq, Object vkRs) {
        ActorType type = authActorRq.getActorType();
        Long userId = authActorRq.getUserId();

        if (ActorType.USER == type) {
            saveVKUserActor((UserAuthResponse) vkRs, userId);
        } else if (ActorType.GROUP == type) {
            saveVKGroupActor((GroupAuthResponse) vkRs, userId);
        }
    }

    private void saveVKGroupActor(GroupAuthResponse vkRs, Long userId) {
        for (Map.Entry<Integer, String> entry : vkRs.getAccessTokens().entrySet())
        {
            VKGroupActor vkGroupNetwork = getVkGroupActor(userId, entry);
            vkService.saveOrUpdate(vkGroupNetwork, VKGroupActor.class);
        }
    }

    private void saveVKUserActor(UserAuthResponse vkRs, Long userId) {
        VKUserActor vkUserActor = getVkUserActor(vkRs, userId);
        vkService.saveOrUpdate(vkUserActor, VKUserActor.class);
    }

    private VKGroupActor getVkGroupActor(Long userId, Map.Entry<Integer, String> entry) {
        VKGroupActor vkGroupNetwork = new VKGroupActor();
        vkGroupNetwork.setVkAccessCode(entry.getValue());
        vkGroupNetwork.setVkUserId(entry.getKey());
        vkGroupNetwork.setUserId(userId);
        vkGroupNetwork.setType("G");
        return vkGroupNetwork;
    }

    private VKUserActor getVkUserActor(UserAuthResponse vkRs, Long userId) {
        VKUserActor vkUserActor = new VKUserActor();
        vkUserActor.setVkAccessCode(vkRs.getAccessToken());
        vkUserActor.setVkUserId(vkRs.getUserId());
        vkUserActor.setUserId(userId);
        vkUserActor.setType("U");
        return vkUserActor;
    }
}
