package actions.vk;

import actionForms.ClientAddVKGroupActionForm;
import com.smirix.utils.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Smirnov-VA on 20.07.2017
 */
public class ClientAddVKGroupAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientAddVKGroupActionForm form = (ClientAddVKGroupActionForm) frm;
        String groupId = form.getGroupId();

        if (StringUtils.isEmpty(groupId))
            throw new RuntimeException();

        String url = ServiceFactory.getVK().getGroupActorAuthUrl(groupId);
        form.setUserActorAuthUrl(url);

        return success(mapping);
    }
}
