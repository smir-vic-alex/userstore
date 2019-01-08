package com.smirix.settings;

import com.smirix.services.properties.Property;
import com.smirix.services.properties.PropertyService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Виктор on 04.01.2019.
 */
public abstract class Setting {

    private volatile Map<String,String> map = new HashMap<>();
    private volatile boolean isUpdating = false;
    private long updatePeriod = -1;
    private final AtomicLong lastUpdateTime = new AtomicLong(0L);
    private String fileName;
    private PropertyService propertyService = new PropertyService();

    protected Setting(String fileName) {
        this.fileName = fileName;
        setUpdatePeriod(60000 * 10);
        loadPropertiesFromFile();
    }

    private void loadPropertiesFromFile(){
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)){
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry entry :properties.entrySet()){
                map.put((String)entry.getKey(), (String)entry.getValue());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void refreshPropertiesByDB(){
        isUpdating = true;
        Map<String,String> mapDB = new HashMap<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()){
            mapDB.put(entry.getKey(), getValue(entry));
        }
        map = mapDB;
        isUpdating = false;
        lastUpdateTime.set(System.currentTimeMillis());
    }

    private String getValue(Map.Entry<String, String> entry){
        Property property = propertyService.getPropertyByKey(entry.getKey());
        if(property != null){
            return property.getValue();
        }
        else {
            return entry.getValue();
        }
    }

    public String getProperty(String key) {
        return getProperty(key, "");
    }

    public String getProperty(String key, String defaultValue) {
        if (needUpdate())
            refreshPropertiesByDB();

        String value = map.get(key);
        if (value == null)
            return defaultValue;

        return value.trim();
    }

    private boolean needUpdate()
    {
        if (isUpdating)
            return false;

        long lstUpdateTime = lastUpdateTime.get();
        return lstUpdateTime <= 0 || (0 < updatePeriod &&  lstUpdateTime + updatePeriod < System.currentTimeMillis());
    }

    private void setUpdatePeriod(int periodForUpdate)
    {
        updatePeriod = periodForUpdate;
    }
}
