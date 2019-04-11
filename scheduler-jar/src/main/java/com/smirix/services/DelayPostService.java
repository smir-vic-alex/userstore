package com.smirix.services;

import com.smirix.entities.DelayTask;
import com.smirix.entities.DelayedPost;
import com.smirix.hibernate.HibernateExecutor;
import com.smirix.requests.VKDelayPostRq;
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

    public DelayTask delayPost(VKDelayPostRq delayRq) {
        return new HibernateExecutor<DelayTask>().execute((session) ->
                {
                    DelayedPost delayedPost = new DelayedPost();
                    delayedPost.setUserId(delayRq.getUserId());
                    delayedPost.setFromGroup(delayRq.getFromGroup());
                    delayedPost.setMessage(delayRq.getMessage());
                    delayedPost.setType("VK");

                    saveOrUpdate(delayedPost, DelayedPost.class);

                    DelayTask delayTask = new DelayTask();
                    delayTask.setDelayPostId(delayedPost.getId());
                    delayTask.setFireDate(delayRq.getPublishDate());
                    delayTask.setUserId(delayTask.getUserId());

                    saveOrUpdate(delayTask, DelayTask.class);

                    return delayTask;
                }
        );
    }

    public List<DelayTask> getTask(Calendar fromDate, Calendar toDate) {
        return new HibernateExecutor<List<DelayTask>>().execute((session) ->
                {
                    try {
                        Query<DelayTask> query = session.createNamedQuery("com.smirix.entities.DelayTask.getTasks", DelayTask.class);
                        query.setParameter("fromDate", fromDate);
                        query.setParameter("toDate", toDate);

                        return query.list();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }
}