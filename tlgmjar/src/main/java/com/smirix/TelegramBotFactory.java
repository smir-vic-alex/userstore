package com.smirix;


import com.smirix.tlgm.BeanFactory;

public class TelegramBotFactory extends BeanFactory {


    public TelegramBot getNewBot() {
        return createBean(TelegramBot.class);
    }
}
