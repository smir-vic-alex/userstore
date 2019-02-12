package com.smirix.senders;

import com.smirix.rest.senders.json.JsonHttpSender;

import java.io.Serializable;

/**
 * Created by Виктор on 11.02.2019.
 */
public abstract class TelegramSenderBase<Rq extends Serializable, Rs extends Serializable> extends JsonHttpSender <Rq, Rs> {

    private static final String TLGM_TYPE = "TLGM";

    @Override
    protected String getType() {
        return TLGM_TYPE;
    }
}
