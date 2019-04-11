package com.smirix.rest.jobs;

import com.smirix.utils.BeanUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Виктор on 07.10.2017.
 */
public class ExecuteVKPostJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BeanUtils.getBean(ExecuteVKPostJobService.class).doJob();
    }
}
