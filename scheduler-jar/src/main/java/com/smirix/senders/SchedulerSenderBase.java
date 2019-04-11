package com.smirix.senders;

import com.smirix.rest.senders.json.JsonHttpSender;

import java.io.Serializable;

/**
 * Class description
 *
 * @author sbrf-Smirnov-VA
 * @created on 2019-04-12
 */
public abstract class SchedulerSenderBase<Rq extends Serializable, Rs extends Serializable> extends JsonHttpSender<Rq, Rs> {
    private static final String SCHDLR_TYPE = "SCHDLR";

    @Override
    protected String getType() {
        return SCHDLR_TYPE;
    }
}
