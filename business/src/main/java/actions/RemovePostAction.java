package actions;

import actionForms.RemovePostActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-08
 */
public class RemovePostAction extends ActionBase {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RemovePostActionForm form = (RemovePostActionForm) frm;

        if(form.validate()) {
            Long id = ServiceFactory.getScheduler().removePost(form.getTaskId());

            if (id != null && id > 0) {
                setUserMessage("Пост удален");
            } else {
                setUserErrorMessage("По техническим причинам удалить пост не удалось");
            }
        }

        return success(mapping);
    }
}
