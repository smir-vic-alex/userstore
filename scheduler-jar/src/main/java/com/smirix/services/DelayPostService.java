package com.smirix.services;

import com.smirix.common.Pair;
import com.smirix.entities.DelayTask;
import com.smirix.entities.DelayedPost;
import com.smirix.entities.TaskStatus;
import com.smirix.hibernate.HibernateExecutor;
import com.smirix.requests.VKDelayPostRq;
import com.smirix.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.List;


/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-09
 */
public class DelayPostService extends BusinessService {

    public Pair<DelayTask, DelayedPost> getUserDelayedTask(Long userId, Long ownerId, String type) {
        return new HibernateExecutor<Pair<DelayTask, DelayedPost>>().execute((session) ->
                {
                    try {
                        Query<DelayTask> query = session.createNamedQuery("com.smirix.entities.DelayTask.getAllTasks", DelayTask.class);
                        query.setParameter("userId", userId);
                        query.setParameter("type", type);
                        query.setParameter("ownerId", ownerId);

                        List<DelayTask> tasks = query.getResultList();

                        if (CollectionUtils.isNotEmpty(tasks)) {
                            DelayedPost post = getDelayedPost(tasks.get(0).getDelayPostId(), userId, session);
                            if (post != null)
                                return new Pair<>(tasks.get(0), post);
                        }

                        return null;

                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    private DelayedPost getDelayedPost(Long postId, Long userId, Session session) {
        try {
            Query<DelayedPost> query = session.createNamedQuery("com.smirix.entities.DelayedPost.getByIdAndUser", DelayedPost.class);
            query.setParameter("id", postId);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(ERROR_MSG, e);
            return null;
        }
    }

    public DelayTask delayPost(VKDelayPostRq delayRq) {
        return new HibernateExecutor<DelayTask>().execute((session) ->
                {
                    try {
                        DelayedPost delayedPost = new DelayedPost();
                        delayedPost.setUserId(delayRq.getUserId());
                        delayedPost.setFromGroup(delayRq.getFromGroup());
                        delayedPost.setMessage(delayRq.getMessage());

                        saveOrUpdate(delayedPost, DelayedPost.class);

                        DelayTask delayTask = new DelayTask();
                        delayTask.setDelayPostId(delayedPost.getId());
                        delayTask.setFireDate(DateUtils.getDate(delayRq.getPublishDate()));
                        delayTask.setUserId(delayedPost.getUserId());
                        delayTask.setStatus(TaskStatus.PREPARED.getValue());
                        delayTask.setType("VK");
                        delayTask.setOwnerId(delayRq.getOwnerId().longValue());

                        saveOrUpdate(delayTask, DelayTask.class);

                        return delayTask;
                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        throw e;
                    }
                }
        );
    }

    public List<DelayTask> getTask(Calendar fromDate, Calendar toDate, String taskStatus) {
        return new HibernateExecutor<List<DelayTask>>().execute((session) ->
                {
                    try {
                        Query<DelayTask> query = session.createNamedQuery("com.smirix.entities.DelayTask.getTasks", DelayTask.class);
                        query.setParameter("fromDate", fromDate);
                        query.setParameter("toDate", toDate);
                        query.setParameter("status", taskStatus);

                        return query.list();
                    } catch (NoResultException e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    public DelayedPost getDelayedPost(Long postId, Long userId) {
        return new HibernateExecutor<DelayedPost>().execute((session) ->
            {
                try {
                    Query<DelayedPost> query = session.createNamedQuery("com.smirix.entities.DelayedPost.getById", DelayedPost.class);
                    query.setParameter("id", postId);
                    query.setParameter("userId", userId);
                    return query.getSingleResult();
                } catch (Exception e) {
                    LOGGER.error(ERROR_MSG, e);
                    return null;
                }
            }
        );
    }
}