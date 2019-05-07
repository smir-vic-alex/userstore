package actions;

import com.smirix.entities.Token;
import com.smirix.entities.user.User;
import com.smirix.services.AuthenticationService;
import com.smirix.services.UserStoreService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.*;
import utils.UserUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by Smirnov-VA on 04.01.2019.
 */
public class AuthAction extends Action {

    private static final AuthenticationService authService = new AuthenticationService();
    private static final UserStoreService userService = new UserStoreService();

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        for (Cookie cookie : request.getCookies()) {
            if ("token".equals(cookie.getName()) && StringUtils.isNotEmpty(cookie.getValue())) {
                Token token = authService.getToken(cookie.getValue());
                if (token.getExpired().after(Calendar.getInstance())) {
                    User user = userService.getUserById(token.getUserId());
                    sessionUpdate(user);
                    authService.remove(token);

                    return mapping.findForward(SUCCESS_FORWARD);
                }
                authService.remove(token);
            }
        }

        return mapping.findForward("forbidden");
    }

    private void sessionUpdate(User user) {
        UserUtils.setUserIsLogin(true);
        UserUtils.setCurrentUser(user);
    }
}
