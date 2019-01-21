package com.smirix.processors;


import com.smirix.TelegramApiModuleException;
import com.smirix.tlgm.BeanFactory;


/**
 * Фабрика обработчиков сообщений
 * Created by Виктор on 09.03.2018.
 */
public class BotMessageProcessorsFactory extends BeanFactory {

    private static final String INVALID_PROCESSOR_TYPE = "Invalid processor type ";

    public BotMessageProcessor getProcessor(Class processorType) {
        if (processorType == CallBackProcessor.class)
            return createBean(CallBackProcessor.class);
        else if (processorType == CommandProcessor.class)
            return createBean(CommandProcessor.class);
        else {
            LOGGER.error(INVALID_PROCESSOR_TYPE + processorType.toString());
            throw new TelegramApiModuleException(INVALID_PROCESSOR_TYPE + processorType.toString());
        }
    }

}
