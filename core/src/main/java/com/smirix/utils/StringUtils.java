package com.smirix.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public static List<String> integerCollectionToListOfStrings(Collection<Integer> collection){
        List<String> newList = new ArrayList<String>(collection.size());
        for (Integer myInt : collection) {
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
