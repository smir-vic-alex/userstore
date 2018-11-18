package actions;

import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 24.07.2017.
 */
public class OperationActionBase extends LookupDispatchAction {
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processActionForm();
        return super.execute(mapping, frm, request, response);
    }

    protected void saveError(HttpServletRequest request, String error) {
        ActionMessages msgs = new ActionMessages();
        ActionMessage msg = new ActionMessage(error, false);
        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        saveErrors(request, msgs);
    }

    public void createOperation() {
    }

    public void checkAccess() {
    }

    public void saveMessages() {
    }

    public void processActionForm() {
    }
}
