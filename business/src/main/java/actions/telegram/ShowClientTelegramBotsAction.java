package actions.telegram;

import actionForms.ShowClientTelegramBotsActionForm;
import actions.Action;
import com.smirix.pojo.TelegramUser;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ShowClientTelegramBotsAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ShowClientTelegramBotsActionForm form = (ShowClientTelegramBotsActionForm) frm;
        List<TelegramUser> bots = ServiceFactory.getTlgm().getUserBots(UserUtils.getCurrentUser().getId());
        form.setBots(bots);

        return success(mapping);
    }
}
