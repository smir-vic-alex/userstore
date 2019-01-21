package com.smirix;


import com.smirix.pojo.Answer;

/**
 * Интерфейс бизнес обработчиков сообщений
 * Created by Виктор on 09.03.2018.
 */
public interface AnswerBusinessHandler {
    Answer getAnswer(String articleTitleName);
}
