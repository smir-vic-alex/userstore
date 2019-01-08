package actions.vk;

import actionForms.ClientAddVKGroupActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKGroupAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientAddVKGroupActionForm form = (ClientAddVKGroupActionForm) frm;
        String groupId = form.getGroupId();
        String url = ServiceFactory.getVK().getGroupActorAuthUrl(groupId);
        return new ActionForward(url, true);
    }
}
