package utils;

import entities.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Виктор on 07.10.2017.
 */
public class UserUtils {

    private static final String IS_USER_LOGIN_ATTR = "isUserLogin";
    private static final boolean IS_USER_LOGIN = true;
    private static final boolean NOT_CREATE_SESSION = false;
    private static final String USER_ATTR = "user";
    public static final String ATTEMPTS_COUNT_ATTR = "attemptsCount";

    public static User getCurrentUser(){
        return (User) WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).getAttribute(USER_ATTR);
    }

    public static void setCurrentUser(User user){
        WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).setAttribute(USER_ATTR, user);
    }

    public static void setUserIsLogin(){
        WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).setAttribute(IS_USER_LOGIN_ATTR, IS_USER_LOGIN);
    }

    public static boolean isUserLogin(){
        HttpSession session = WebContext.getCurrentRequest().getSession();
        return session.getAttribute(IS_USER_LOGIN_ATTR) != null && (boolean) session.getAttribute(IS_USER_LOGIN_ATTR);
    }

    public static Integer getAttemptsCounts(){
        Integer attemptsCount = (Integer) WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).getAttribute(ATTEMPTS_COUNT_ATTR);
        return attemptsCount != null ? attemptsCount : 0;
    }

    public static void setAttemptsCounts(Integer attemptsCount){
        WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).setAttribute(ATTEMPTS_COUNT_ATTR, attemptsCount);
    }
}
