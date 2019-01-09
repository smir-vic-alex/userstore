package networks.vk.connectors;

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
import entities.VKUserNetwork;
import settings.vk.VKApiSetting;
import settings.SettingFactory;
import exeptions.connectors.ConnectorException;
import utils.StringUtils;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Коннектор для логина пользователем
 * Created by Smirnov Victor on 09.04.17.
 */
public class VKConnectorManager
{
    private VKConnectorManager(){
    }

    public static VKConnectorManager getInstance(){
        return new VKConnectorManager();
    }

    public UserAuthResponse authUser(String code) throws ConnectorException
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        VKApiSetting apiConfig = SettingFactory.getSetting(VKApiSetting.class);
        UserAuthResponse authResponse;
        try
        {
            authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(apiConfig.getApplicationId(), apiConfig.getApplicationSecretKey(), apiConfig.getApplicationRedirectUri(), code)
                    .execute();
        }
        catch (Exception e) {
            throw new ConnectorException();
        }

        if (authResponse == null)
            throw new ConnectorException();

        return authResponse;
    }

    public List<GroupFull> getGroups(VKUserNetwork vkUserNetwork)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserActor userActor = new UserActor(vkUserNetwork.getVkUserId(), vkUserNetwork.getVkAccessCode());
        try {
            GetResponse response = vk.groups().get(userActor).filter(GroupsGetFilter.ADMIN).execute();

            return vk.groups().getById(userActor).groupIds(StringUtils.integerListToListOfStrings(response.getItems())).execute();
        }
        catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public PostResponse createPost(UserActor actor, Integer groupId, String message, Integer time)
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
            return query.execute();
        }
        catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GroupAuthResponse authGroup(String code) throws ConnectorException
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        VKApiSetting apiConfig = SettingFactory.getSetting(VKApiSetting.class);

        GroupAuthResponse authResponse;
        try
        {
            authResponse = vk.oauth()
                    .groupAuthorizationCodeFlow(apiConfig.getApplicationId(), apiConfig.getApplicationSecretKey(), apiConfig.getApplicationRedirectUri(), code)
                    .execute();
        }
        catch (Exception e) {
            throw new ConnectorException();
        }

        if (authResponse == null)
            throw new ConnectorException();

        return authResponse;
    }

}
