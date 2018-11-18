package hibernate.services;

import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import entities.Network;
import entities.VKGroupNetwork;
import entities.VKUserNetwork;
import hibernate.BusinessService;
import hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by SBT-Smirnov-VA on 07.09.2017.
 */
public class NetworksService extends BusinessService<Network> {

    public VKUserNetwork saveOrUpdateVkNetworkCode(final Long userId, final UserAuthResponse response)
    {
        return new HibernateExecutor<VKUserNetwork>().execute((session)->
        {
            VKUserNetwork vkUserNetwork = new VKUserNetwork();
            vkUserNetwork.setVkAccessCode(response.getAccessToken());
            vkUserNetwork.setVkUserId(response.getUserId());
            vkUserNetwork.setUserId(userId);
            //TODO спрятать тип в Network
            vkUserNetwork.setType("VK");
            session.saveOrUpdate(vkUserNetwork);
            return vkUserNetwork;
        });
    }

    public VKUserNetwork getVKUserNetworkByUserId(final Long userId)
    {
        return new HibernateExecutor<VKUserNetwork>().execute((session) ->
        {
            try
            {
                Query<VKUserNetwork> query = session.createNamedQuery("entities.get.user.vk.by.user.id", VKUserNetwork.class);
                query.setParameter("userId", userId);
                return query.getSingleResult();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public List<VKGroupNetwork> getVKGroupNetworksByUserId(final Long userId)
    {
        return new HibernateExecutor<List<VKGroupNetwork>>().execute((session) ->
        {
            try
            {
                Query<VKGroupNetwork> query = session.createNamedQuery("entities.get.groups.vk.by.user.id", VKGroupNetwork.class);
                query.setParameter("userId", userId);
                return query.list();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public VKGroupNetwork getVKGroupNetworkByUserId(final Integer vkUserId)
    {
        return new HibernateExecutor<VKGroupNetwork>().execute((session) ->
        {
            try
            {
                Query<VKGroupNetwork> query = session.createNamedQuery("entities.get.group.vk.by.user.id", VKGroupNetwork.class);
                query.setParameter("vkUserId", vkUserId);
                return query.uniqueResult();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    //TODO не происходит апдейт записи
    public VKGroupNetwork saveOrUpdateVkGroupsNetworkCode(final Long userId, final GroupAuthResponse response)
    {
        return new HibernateExecutor<VKGroupNetwork>().execute((session)->
        {
            for (Map.Entry<Integer, String> entry : response.getAccessTokens().entrySet())
            {
                VKGroupNetwork vkGroupNetwork = new VKGroupNetwork();
                vkGroupNetwork.setVkAccessCode(entry.getValue());
                vkGroupNetwork.setVkUserId(entry.getKey());
                vkGroupNetwork.setUserId(userId);
                vkGroupNetwork.setType("VKG");
                session.saveOrUpdate(vkGroupNetwork);
            }
            return null;
        });
    }

}
