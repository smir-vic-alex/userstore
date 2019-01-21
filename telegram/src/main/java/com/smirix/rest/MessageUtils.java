package com.smirix.rest;

import com.smirix.rest.elements.messages.Head;
import com.smirix.rest.elements.messages.Status;
import ru.json.schema2pojo.dto.DefaultAnswer;

/**
 * Created by Виктор on 15.03.2018.
 */
public class MessageUtils {

    private static final Long SUCCESS = 0L;
    private static final String DONE = "done";
    private static final String FAIL_MESSAGE = "fail";
    private static final Long FAIL = -1L;

    public static DefaultAnswer success(Head head) {
        return getMessage(SUCCESS, DONE, head);
    }

    public static DefaultAnswer fail(Head head) {
        return getMessage(FAIL, FAIL_MESSAGE, head);
    }

    private static DefaultAnswer getMessage(Long code, String message, Head head) {
        DefaultAnswer answer = new DefaultAnswer();
        answer.setStatus(new Status(code, message));
        answer.setBody(new com.smirix.senders.requests.DefaultAnswer());
        answer.setHead(head);
        return answer;
    }
}
