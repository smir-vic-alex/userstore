package actions.vk;

import actionForms.ShowClientAdminVKGroupActionForm;
import actions.ActionBase;
import com.smirix.entities.user.User;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Виктор on 15.09.2017.
 */
public class ShowClientAdminVKGroupsAction extends ActionBase
{
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ShowClientAdminVKGroupActionForm actionForm = (ShowClientAdminVKGroupActionForm) form;

        User user = UserUtils.getCurrentUser();
        actionForm.setVkGroups(ServiceFactory.getVK().getUserGroups(user.getId()));

        return success(mapping);
    }
}
