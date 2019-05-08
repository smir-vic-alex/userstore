package actions;

import com.smirix.utils.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smirnov-VA on 24.07.2017.
 */
public abstract class ActionBase extends DispatchAction {

    private static final String REQUEST_PARAMETER_NAME = "operation";
    protected static final String SUCCESS_FORWARD = "success";
    protected static final String FAIL_FORWARD = "fail";
    protected static final String SHOW_FORWARD = "show";
    private Map<String, String> keyMethodMap = null;
    private final Object synkRoot = new Object();

    protected Map<String, String> getKeyMethodMap() {
        return new HashMap<>();
    }

    protected String getLookupMapName(HttpServletRequest request, String keyName, ActionMapping mapping) throws ServletException {
        synchronized (synkRoot) {
            if (keyMethodMap == null) {
                keyMethodMap = getKeyMethodMap();
            }
        }

        String methodName = keyMethodMap.get(keyName);
        if (methodName == null) {
            String message = messages.getMessage("dispatch.lookup", mapping.getPath(), keyName);
            throw new ServletException(message);
        }

        return methodName;
    }

    protected final String getDefaultMethodName() {
        return "start";
    }

    protected String getParameter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestParam = request.getParameter(REQUEST_PARAMETER_NAME);
        if (requestParam != null)
            return requestParam;

        return null;
    }

    protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String name) throws Exception {
        return super.dispatchMethod(mapping, form, request, response, name);
    }

    protected final String getMethodName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String parameter)
            throws Exception {
        //проверяем защищенность реквеста
        if (parameter == null || parameter.length() == 0) {
            return getDefaultMethodName();
        }

        return getLookupMapName(request, parameter, mapping);
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.execute(mapping, form, request, response);
    }

    public abstract ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    protected ActionForward success(ActionMapping mapping){
        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected ActionForward fail(ActionMapping mapping){
        return mapping.findForward(FAIL_FORWARD);
    }

    protected ActionForward show(ActionMapping mapping){
        return mapping.findForward(SHOW_FORWARD);
    }

    protected void setUserMessage(String msg) {
        WebContext.getCurrentRequest().setAttribute(Globals.MESSAGES_KEY,msg);
    }

    protected void setUserErrorMessage(String msg) {
        WebContext.getCurrentRequest().setAttribute(Globals.ERROR_KEY,msg);
    }
}
