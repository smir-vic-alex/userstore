package actions.telegram;

import actionForms.ClientAddTelegramBotActionForm;
import actions.Action;
import com.smirix.entities.user.User;
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
public class ClientAddTelegramBotAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientAddTelegramBotActionForm form = (ClientAddTelegramBotActionForm) frm;

        if (StringUtils.isEmpty(form.getName()) || StringUtils.isEmpty(form.getToken()))
            return mapping.findForward("start");

        User user = UserUtils.getCurrentUser();
        ServiceFactory.getTlgm().addBot(form.getToken(), form.getName(), user.getId());

        return success(mapping);
    }
}
