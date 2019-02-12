package com.smirix.senders;

import com.smirix.rest.senders.json.JsonHttpSender;

import java.io.Serializable;

/**
 * Created by Виктор on 13.02.2019.
 */
public abstract class VKSenderBase<Rq extends Serializable, Rs extends Serializable> extends JsonHttpSender<Rq, Rs> {

    private static final String VK_TYPE = "VK";

    @Override
    protected String getType() {
        return VK_TYPE;
    }
}
