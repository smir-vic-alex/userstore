package utils;


/**
 * Created by Виктор on 30.09.2017.
 */
public class VKUtils {

    public static final String VK_GROUP_IDS = "group_ids=";

    public static String insertGroupIdIntoRequestUrl(String param, String url){
        return StringUtils.addParameterValueInPath(VK_GROUP_IDS, param, url);
    }
}
