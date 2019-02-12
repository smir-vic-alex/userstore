package com.smirix.services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Виктор on 11.02.2019.
 */
public class PropertyFileLoaderService {

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
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Не удалось загрузить настройки", e);
        }
    }
}
