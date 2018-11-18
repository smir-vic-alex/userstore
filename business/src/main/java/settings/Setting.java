package settings;

import property.Property;
import property.PropertyService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Базовый класс настроек приложения
 * Created by Smirnov Victor on 09.04.17.
 */
public abstract class Setting {

    private volatile Map<String,String> map = new HashMap<>();
    private String fileName;
    private PropertyService propertyService = new PropertyService();

    protected Setting(String fileName) {
        this.fileName = fileName;
        loadPropertiesFromFile();
        refreshPropertiesByDB();
        refreshPropertiesByCustomConfig();
    }

    private void loadPropertiesFromFile(){
        try(InputStream inputStream = Property.class.getResourceAsStream(fileName)){
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

    protected void refreshPropertiesByDB(){
        for (Map.Entry<String, String> entry : map.entrySet()){
            map.put(entry.getKey(), getValue(entry));
        }
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
        return map.get(key);
    }

    public abstract void refreshPropertiesByCustomConfig();
}
