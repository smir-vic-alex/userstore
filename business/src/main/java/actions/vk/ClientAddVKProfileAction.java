package actions.vk;

import actionForms.ClientAddVKProfileActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKProfileAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ClientAddVKProfileActionForm frm = (ClientAddVKProfileActionForm) form;

        String url = ServiceFactory.getVK().getUserActorAuthUrl();
        frm.setUserActorAuthUrl(url);

        return success(mapping);
    }
}