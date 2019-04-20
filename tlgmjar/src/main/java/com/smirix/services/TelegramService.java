package com.smirix.services;

import com.smirix.hibernate.HibernateExecutor;
import com.smirix.pojo.TelegramChannel;
import com.smirix.pojo.TelegramUser;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Виктор on 20.01.2019.
 */
public class TelegramService extends BusinessService {

    public List<TelegramUser> getTelegramUserByUserId(Long userId) {
        return new HibernateExecutor<List<TelegramUser>>().execute((session) ->
        {
            try {
                Query<TelegramUser> query = session.createNamedQuery("com.smirix.pojo.TelegramUser.getByUserId", TelegramUser.class);
                query.setParameter("userId", userId);

                return query.list();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return Collections.emptyList();
            }
        });
    }

    public List<TelegramChannel> getTelegramChannelByUserId (Long userId) {
        return new HibernateExecutor<List<TelegramChannel>>().execute((session) ->
        {
            try {
                Query<TelegramChannel> query = session.createNamedQuery("com.smirix.pojo.TelegramChannel.getChannelsByUserId", TelegramChannel.class);
                query.setParameter("userId", userId);

                return query.list();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return Collections.emptyList();
            }
        });
    }

    public List<TelegramChannel> getTelegramChannelByTelegramUserId (Long userTelegramId) {
        return new HibernateExecutor<List<TelegramChannel>>().execute((session) ->
        {
            try {
                Query<TelegramChannel> query = session.createNamedQuery("com.smirix.pojo.TelegramChannel.getChannelsByUserTelegramId", TelegramChannel.class);
                query.setParameter("userTelegramId", userTelegramId);

                return query.list();
            } catch (Exception e) {
                LOGGER.error(ERROR_MSG, e);
                return Collections.emptyList();
            }
        });
    }
}