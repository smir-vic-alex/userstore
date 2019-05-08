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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-09
 */
public class DelayPostService extends BusinessService {

    public List<Pair<DelayTask, DelayedPost>> getUserDelayedTask(Long userId, Long ownerId, String type, String status) {
        return new HibernateExecutor<List<Pair<DelayTask, DelayedPost>>>().execute((session) ->
                {
                    try {
                        Query<DelayTask> query = session.createNamedQuery("com.smirix.entities.DelayTask.getAllTasks", DelayTask.class);
                        query.setParameter("userId", userId);
                        query.setParameter("type", type);
                        query.setParameter("ownerId", ownerId);
                        query.setParameter("status", status);

                        List<DelayTask> tasks = query.getResultList();

                        if (CollectionUtils.isNotEmpty(tasks)) {
                            List<Pair<DelayTask, DelayedPost>> pairs = new ArrayList<>(tasks.size());

                            for (DelayTask task : tasks) {
                                DelayedPost post = getDelayedPost(task.getDelayPostId(), userId, session);
                                if (post != null)
                                    pairs.add(new Pair<>(task, post));
                            }
                            return pairs;

                        }

                        return Collections.emptyList();

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

    public DelayTask updateDelayedPost(DelayedPost delayedPost, DelayTask delayTask) {
        return new HibernateExecutor<DelayTask>().execute((session) ->
                {
                    try {
                        saveOrUpdate(delayedPost, DelayedPost.class);
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

    public DelayTask getTaskById(Long id) {
        return new HibernateExecutor<DelayTask>().execute((session) ->
                {
                    try {
                        Query<DelayTask> query = session.createNamedQuery("com.smirix.entities.DelayTask.getTaskById", DelayTask.class);
                        query.setParameter("id", id);

                        return query.list().get(0);
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

    public void saveOrUpdateTask(Object object) {
        super.saveOrUpdate(object, DelayTask.class);
    }
}