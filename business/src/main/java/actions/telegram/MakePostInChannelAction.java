package actions.telegram;

import actionForms.MakePostInChannelActionForm;
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
public class MakePostInChannelAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        MakePostInChannelActionForm form = (MakePostInChannelActionForm) frm;
        Long userId = UserUtils.getCurrentUser().getId();
        form.setChannels(ServiceFactory.getTlgm().getUserChannels(userId));
        if (StringUtils.isEmpty(form.getChannelName()))
            return mapping.findForward("show");

        ServiceFactory.getTlgm().sendMessage(form.getMessage(), form.getChannelName(), userId);

        return success(mapping);
    }
}
