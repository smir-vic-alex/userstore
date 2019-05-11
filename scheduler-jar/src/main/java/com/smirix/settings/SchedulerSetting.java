package com.smirix.settings;


/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-16
 */
public class SchedulerSetting extends Setting {

    private static final String PREFIX = "com.smirix.settings.SchedulerSetting.";
    private static final String TASK_MINUTES_FROM_SEARCH_KEY = PREFIX + "task.date.from.search";
    private static final String TASK_MINUTES_TO_SEARCH_KEY = PREFIX + "task.date.to.search";
    private static final String DIFF_MINUTES_BEFORE_POST_KEY = PREFIX + "diff.minutes.before.post";
    private static final String PRIVATE_FILE_PATH = PREFIX + "private.file.path";
    private static final String EXTERNAL_FILE_PATH = PREFIX + "external.file.path";

    public SchedulerSetting(String fileName) {
        super(fileName);
    }

    public int getTaskDateFromSearch() {
        return Integer.parseInt(getProperty(TASK_MINUTES_FROM_SEARCH_KEY));
    }

    public int getTaskDateToSearch() {
        return Integer.parseInt(getProperty(TASK_MINUTES_TO_SEARCH_KEY));
    }

    public int getDiffMinutesBeforePost() {
        return Integer.parseInt(getProperty(DIFF_MINUTES_BEFORE_POST_KEY));
    }

    public String getPrivateFilePath() {
        return getProperty(PRIVATE_FILE_PATH);
    }

    public String getExternalFilePath() {
        return getProperty(EXTERNAL_FILE_PATH);
    }
}
