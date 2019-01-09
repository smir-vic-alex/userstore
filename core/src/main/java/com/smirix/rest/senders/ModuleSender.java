package com.smirix.rest.senders;

import com.smirix.common.Sender;
import com.smirix.rest.elements.messages.Head;
import com.smirix.rest.elements.messages.Message;
import com.smirix.settings.SenderSettingBase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;


/**
 * Created by Виктор on 05.01.2019.
 */
public abstract class ModuleSender<Rq extends Serializable, Rs extends Serializable> implements Sender<Rq, Rs>{

    private SenderSettingBase senderSetting;

    public ModuleSender(SenderSettingBase senderSetting) {
        this.senderSetting = senderSetting;
    }

    @Override
    public Rs send(Rq requestEntity) throws Exception {
        try {
            Message<Rq> request = buildRequest(requestEntity);
            //todo добавить логирование
            Object rawResponse = execute(request);
            rawResponse = postExecute(rawResponse);

            Message<Rs> response = buildResponse(rawResponse);
            //todo добавить логирование
            return getResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected abstract Object execute(Message<Rq> request) throws Exception;
    protected abstract Object postExecute(Object response) throws Exception;
    protected abstract Message<Rs> buildResponse(Object message) throws Exception;
    protected abstract Class getType();

    private Rs getResult(Message<Rs> response) {
        return response.getBody();
    }

    private Message<Rq> buildRequest(Rq request) throws Exception {
        return new Message<>(getHead(), request);
    }

    private Head getHead() {
        Head head = new Head();
        head.setScUrl(senderSetting.getScHost(getType()) + senderSetting.getScUrl(getType()));
        head.setScName(senderSetting.getScName(getType()));
        head.setSpName(senderSetting.getSpName());
        head.setTime(Calendar.getInstance());
        head.setUuid(UUID.randomUUID().toString());

        return head;
    }
}
