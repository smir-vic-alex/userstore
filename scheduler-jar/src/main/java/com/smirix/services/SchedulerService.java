package com.smirix.services;

import com.smirix.senders.SchedulerVKDelaySender;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-12
 */
public class SchedulerService {

    private SchedulerVKDelaySender vkDelaySender;

    public void setVkDelaySender(SchedulerVKDelaySender vkDelaySender) {
        this.vkDelaySender = vkDelaySender;
    }

    public void delayVKPost() {
        try {
            vkDelaySender.send(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
