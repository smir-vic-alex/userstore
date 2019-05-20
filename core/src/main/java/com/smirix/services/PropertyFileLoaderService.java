package com.smirix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Виктор on 11.02.2019.
 */
public class PropertyFileLoaderService {

    protected static Logger LOGGER = LoggerFactory.getLogger(PropertyFileLoaderService.class);
    private static final String ERROR_MESSAGE = "Не удалось загрузить настройки";

    public Map<String,String> loadPropertiesFromFile(String fileName){

        Map<String,String> map = new HashMap<>();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)){

            Properties properties = new Properties();
            properties.load(inputStream);

            for (Map.Entry entry :properties.entrySet()){
                map.put((String)entry.getKey(), (String)entry.getValue());
            }

            return map;
        }
        catch (Throwable e){

            LOGGER.error(ERROR_MESSAGE);
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }
}
