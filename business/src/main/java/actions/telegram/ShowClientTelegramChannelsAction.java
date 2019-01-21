package actions.telegram;

import actionForms.ShowClientTelegramChannelsActionForm;
import actions.Action;
import com.smirix.pojo.TelegramChannel;
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
public class ShowClientTelegramChannelsAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ShowClientTelegramChannelsActionForm form = (ShowClientTelegramChannelsActionForm) frm;
        List<TelegramChannel> channelList = ServiceFactory.getTlgm().getUserChannels(UserUtils.getCurrentUser().getId());
        form.setChannels(channelList);

        return success(mapping);
    }
}
