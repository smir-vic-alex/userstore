package com.smirix.services;

import com.smirix.entities.VKGroupActor;
import com.smirix.entities.VKUserActor;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by SBT-Smirnov-VA on 07.09.2017.
 */
public class VkService extends BusinessService {

    public VKUserActor getVKUserNetworkByUserId(final Long userId)
    {
        return new HibernateExecutor<VKUserActor>().execute((session) ->
        {
            try
            {
                Query<VKUserActor> query = session.createNamedQuery("entities.get.user.vk.by.user.id", VKUserActor.class);
                query.setParameter("userId", userId);
                return query.getSingleResult();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public List<VKGroupActor> getVKGroupNetworksByUserId(final Long userId)
    {
        return new HibernateExecutor<List<VKGroupActor>>().execute((session) ->
        {
            try
            {
                Query<VKGroupActor> query = session.createNamedQuery("entities.get.groups.vk.by.user.id", VKGroupActor.class);
                query.setParameter("userId", userId);
                return query.list();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public VKGroupActor getVKGroupNetworkByUserId(final Integer vkUserId)
    {
        return new HibernateExecutor<VKGroupActor>().execute((session) ->
        {
            try
            {
                Query<VKGroupActor> query = session.createNamedQuery("entities.get.group.vk.by.user.id", VKGroupActor.class);
                query.setParameter("vkUserId", vkUserId);
                return query.uniqueResult();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
