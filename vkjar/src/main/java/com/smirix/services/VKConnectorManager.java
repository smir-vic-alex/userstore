package com.smirix.services;

import com.smirix.entities.VKUserActor;
import com.smirix.settings.VKApiSetting;
import com.smirix.utils.StringUtils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import com.vk.api.sdk.queries.groups.GroupsGetFilter;
import com.vk.api.sdk.queries.wall.WallPostQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.List;

/**
 * Коннектор для логина пользователем
 * Created by Smirnov Victor on 09.04.17.
 */
public class VKConnectorManager
{
    @Autowired
    @Qualifier("vkApiSetting")
    private VKApiSetting vkApiSetting;

    public UserAuthResponse authUser(String code)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserAuthResponse authResponse;
        try
        {
            authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(vkApiSetting.getApplicationId(), vkApiSetting.getApplicationSecretKey(), vkApiSetting.getApplicationRedirectUri(), code)
                    .execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (authResponse == null) {
            throw new RuntimeException("com.smirix.services.VKConnectorManager#authUser authResponse == null");
        }
        return authResponse;
    }

    public List<GroupFull> getGroups(VKUserActor vkUserActor)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserActor userActor = new UserActor(vkUserActor.getVkUserId(), vkUserActor.getVkAccessCode());
        try {
            GetResponse response = vk.groups().get(userActor).filter(GroupsGetFilter.ADMIN).execute();

            return vk.groups().getById(userActor).groupIds(StringUtils.integerListToListOfStrings(response.getItems())).execute();
        }
        catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Integer createPost(UserActor actor, Integer groupId, String message, Integer time)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        try
        {
            WallPostQuery query = vk.wall().post(actor);

            query.ownerId(groupId);
            query.fromGroup(true);
            query.message(message);
            if(time != null) {
                query.publishDate((int)(System.currentTimeMillis() / 1000L) + time);
            }
            return query.execute().getPostId();
        }
        catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GroupAuthResponse authGroup(String code)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        GroupAuthResponse authResponse;
        try
        {
            authResponse = vk.oauth()
                    .groupAuthorizationCodeFlow(vkApiSetting.getApplicationId(), vkApiSetting.getApplicationSecretKey(), vkApiSetting.getApplicationRedirectUri(), code)
                    .execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (authResponse == null) {
            throw new RuntimeException("com.smirix.services.VKConnectorManager#authGroup authResponse == null");
        }

        return authResponse;
    }

}
