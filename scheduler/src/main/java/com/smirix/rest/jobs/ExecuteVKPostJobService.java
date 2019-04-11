package com.smirix.rest.jobs;

import com.smirix.entities.DelayTask;
import com.smirix.services.DelayPostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-10
 */
public class ExecuteVKPostJobService {

    @Autowired
    private DelayPostService delayPostService;

    public void doJob() {

        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();

        fromDate.add(Calendar.MINUTE, -3);
        toDate.add(Calendar.MINUTE, 3);

        List<DelayTask> tasks = delayPostService.getTask(fromDate, toDate);
    }
}
