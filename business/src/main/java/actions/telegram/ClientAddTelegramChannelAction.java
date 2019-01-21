package actions.telegram;

import actionForms.ClientAddTelegramChannelActionForm;
import actions.Action;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ClientAddTelegramChannelAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientAddTelegramChannelActionForm form = (ClientAddTelegramChannelActionForm) frm;

        if (StringUtils.isEmpty(form.getNameChannel()))
            return mapping.findForward("show");

        Long userId = UserUtils.getCurrentUser().getId();
        ServiceFactory.getTlgm().addChannel(userId, form.getBotId(), form.getNameChannel());

        return success(mapping);
    }
}
