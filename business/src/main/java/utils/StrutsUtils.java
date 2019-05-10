package utils;

import com.smirix.utils.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * Created by Smirnov-VA on 19.07.2017.
 */
public final class StrutsUtils {

    public static Object currentForm(PageContext pageContext) {
        try {
            return TagUtils.getInstance().lookup(pageContext, Constants.BEAN_KEY, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getErrorMessage() {
        return getMessage(Globals.ERROR_KEY);
    }

    public static String getUserMessage() {
        return getMessage(Globals.MESSAGES_KEY);
    }

    private static String getMessage(String key) {
        HttpServletRequest currentRequest = WebContext.getCurrentRequest();
        if (currentRequest != null) {
            Object msg = currentRequest.getAttribute(key);
            if (msg instanceof String) {
                currentRequest.removeAttribute(key);
                return (String) msg;
            }
        }

        return "";
    }
}
