package com.smirix.services;


import com.smirix.utils.StringUtils;

import java.util.List;

/**
 * Created by Виктор on 30.09.2017.
 */
public class VKUtils {

    public static final String VK_GROUP_IDS = "group_ids=";

    public static String insertGroupIdIntoRequestUrl(List<String> ids, String url){

        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String id : ids) {
            sb.append(prefix);
            prefix = ",";
            sb.append(id);
        }

        return StringUtils.addParameterValueInPath(VK_GROUP_IDS, sb.toString(), url);
    }
}
