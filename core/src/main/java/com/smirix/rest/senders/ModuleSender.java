package com.smirix.rest.senders;

import com.smirix.common.Sender;
import com.smirix.rest.elements.messages.Head;
import com.smirix.rest.elements.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;


/**
 * Created by Виктор on 05.01.2019.
 */
public abstract class ModuleSender<Rq extends Serializable, Rs extends Serializable> implements Sender<Rq, Rs>{

    protected static Logger LOGGER = LoggerFactory.getLogger(ModuleSender.class);

    @Override
    public Rs send(Rq requestEntity) throws Exception {
        try {
            Message<Rq> request = buildRequest(requestEntity);

            LOGGER.info("Отправка запроса uuid = " + request.getHead().getUuid());

            Object rawResponse = execute(request);
            rawResponse = postExecute(rawResponse);
            Message<Rs> response = buildResponse(rawResponse);

            LOGGER.info("Обработка ответа uuid = " + response.getHead().getUuid());

            return getResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected abstract Object execute(Message<Rq> request) throws Exception;
    protected abstract Object postExecute(Object response) throws Exception;
    protected abstract Message<Rs> buildResponse(Object message) throws Exception;
    protected abstract String getPath();

    private Rs getResult(Message<Rs> response) {
        return response.getBody();
    }

    private Message<Rq> buildRequest(Rq request) throws Exception {
        return new Message<>(getHead(), request);
    }

    private Head getHead() {
        Head head = new Head();
        head.setScUrl(getPath());
        head.setTime(Calendar.getInstance());
        head.setUuid(UUID.randomUUID().toString());

        return head;
    }
}
