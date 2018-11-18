package utils;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;

import javax.servlet.jsp.PageContext;

/**
 * Created by SBT-Smirnov-VA on 19.07.2017.
 */
public final class StrutsUtils {

    public static Object currentForm(PageContext pageContext) {
        try {
            return TagUtils.getInstance().lookup(pageContext, Constants.BEAN_KEY, null);
        } catch (Exception e) {
            return null;
        }
    }
}
