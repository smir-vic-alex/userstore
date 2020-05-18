package utils;


import com.smirix.entities.user.User;
import com.smirix.utils.WebContext;

import javax.servlet.http.HttpSession;

/**
 * Created by Виктор on 07.10.2017
 */
public class UserUtils {

    private static final String IS_USER_LOGIN_ATTR = "isUserLogin";
    private static final boolean NOT_CREATE_SESSION = false;
    private static final String USER_ATTR = "user";
    private static final String ATTEMPTS_COUNT_ATTR = "attemptsCount";
    private static final String IS_USER_HAS_VK_PROFILE_ATTR = "isUserHasVKProfile";

    public static User getCurrentUser(){
        return (User) WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).getAttribute(USER_ATTR);
    }

    public static void setCurrentUser(User user){
        WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).setAttribute(USER_ATTR, user);
    }

    /**
     * Установить признак, что пользователь прошел аутентификацию
     * @param userIsLogin - false пользователь не прошел, true - прошел
     */
    public static void setUserIsLogin(boolean userIsLogin){
        WebContext.getCurrentRequest().getSession(NOT_CREATE_SESSION).setAttribute(IS_USER_LOGIN_ATTR, userIsLogin);
    }

    /**
     * @return является ли текущий пользователь аутентифицированным
     */
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

    public static boolean isUserHasVKProfile() {
        HttpSession session = WebContext.getCurrentRequest().getSession();
        return session.getAttribute(IS_USER_HAS_VK_PROFILE_ATTR) != null && (boolean) session.getAttribute(IS_USER_HAS_VK_PROFILE_ATTR);
    }

    public static void setIsUserHasVKProfile(boolean isHas) {
        WebContext.getCurrentRequest().getSession().setAttribute(IS_USER_HAS_VK_PROFILE_ATTR, isHas);
    }
}
