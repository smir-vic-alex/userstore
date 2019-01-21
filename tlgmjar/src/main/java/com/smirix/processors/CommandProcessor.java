package com.smirix.processors;

import com.smirix.BotMessageUtils;
import com.smirix.pojo.Answer;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Обработчик запросов по команде (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class CommandProcessor extends BotMessageProcessor {
    @Override
    public BotApiMethod<? extends Serializable> process(Update update) {
        Answer answer = businessHandler.getAnswer(update.getMessage().getText());
        return BotMessageUtils.message(update.getMessage().getChatId(), answer.getText());
    }
}
