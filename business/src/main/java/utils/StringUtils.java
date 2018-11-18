package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 24.07.2017.
 */
public class StringUtils {

    public static boolean isEmpty(String string) {
        return !isNotEmpty(string);
    }

    public static boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isNotEmpty(String... strings) {
        if (strings != null) {
            for (String string : strings) {
                if (isEmpty(string)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static List<String> integerListToListOfStrings(List<Integer> list){
        List<String> newList = new ArrayList<String>(list.size());
        for (Integer myInt : list) {
            newList.add(String.valueOf(myInt));
        }
        return newList;
    }

    public static String addParameterValueInPath(String paramKey, String paramValue, String path)
    {
        StringBuilder sBuilder = new StringBuilder(path);
        int index = sBuilder.lastIndexOf(paramKey) + paramKey.length();
        return sBuilder.insert(index, paramValue).toString();
    }
}
