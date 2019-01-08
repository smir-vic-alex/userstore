package actions.vk;

import actionForms.CodeVKActionForm;
import com.smirix.entities.ActorType;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by Виктор on 30.09.2017.
 */
public class CodeVKGroupAction extends VKAction  {

    @Override
    public ActionForward start (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CodeVKActionForm codeVKActionForm = (CodeVKActionForm) form;
        ServiceFactory.getVK().authActor(codeVKActionForm.getCode(), UserUtils.getCurrentUser().getId(), ActorType.GROUP);

        return success(mapping);
    }
}
