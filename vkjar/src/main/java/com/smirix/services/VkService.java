package com.smirix.services;

import com.smirix.entities.VKGroup;
import com.smirix.entities.VKGroupActor;
import com.smirix.entities.VKUserActor;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

/**
 * Created by Smirnov-VA on 07.09.2017.
 */
public class VkService extends BusinessService {

    public VKUserActor getVKUserNetworkByUserId(final Long userId) {
        return new HibernateExecutor<VKUserActor>().execute((session) ->
        {
            try {
                Query<VKUserActor> query = session.createNamedQuery("entities.get.user.vk.by.user.id", VKUserActor.class);
                query.setParameter("userId", userId);
                return query.getSingleResult();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return null;
            }
        });
    }

    public List<VKGroupActor> getVKGroupNetworksByUserId(final Long userId) {
        return new HibernateExecutor<List<VKGroupActor>>().execute((session) ->
        {
            try {
                Query<VKGroupActor> query = session.createNamedQuery("entities.get.groups.vk.by.user.id", VKGroupActor.class);
                query.setParameter("userId", userId);
                return query.list();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return null;
            }
        });
    }

    public List<VKGroup> getVKGroups(final Long userId) {
        return new HibernateExecutor<List<VKGroup>>().execute((session) ->
        {
            try {
                Query<VKGroup> query = session.createNamedQuery("com.smirix.entities.VKGroup.get.groups.list", VKGroup.class);
                query.setParameter("userId", userId);
                return query.list();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return Collections.emptyList();
            }
        });
    }

    public VKGroupActor getVKGroupNetworkByUserId(final Integer vkUserId) {
        return new HibernateExecutor<VKGroupActor>().execute((session) ->
        {
            try {
                Query<VKGroupActor> query = session.createNamedQuery("entities.get.group.vk.by.user.id", VKGroupActor.class);
                query.setParameter("vkUserId", vkUserId);
                return query.uniqueResult();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return null;
            }
        });
    }
}
