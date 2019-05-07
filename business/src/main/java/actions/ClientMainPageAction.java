package actions;

import actionForms.MainPageActionForm;
import com.smirix.entities.VKUser;
import com.smirix.senders.user.requests.UserGroupsRs;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Smirnov-VA on 20.07.2017.
 */
public class ClientMainPageAction extends ActionBase {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            MainPageActionForm form = (MainPageActionForm) frm;
            Long userId = UserUtils.getCurrentUser().getId();

            VKUser vkUser = ServiceFactory.getVK().getUser(userId);
            if (vkUser != null && vkUser.getVkUserId() != null) {
                UserUtils.setIsUserHasVKProfile(true);
                form.setVkUser(vkUser);
                UserGroupsRs rs = ServiceFactory.getVK().getUserGroups(userId, false);

                if (rs != null){
                    form.setVkGroups(rs.getGroups());
                    form.setDelayedVKPosts(rs.getDelayedVKPosts());
                }
            }

//        form.setTelegramBots(ServiceFactory.getTlgm().getUserBots(userId));
//        form.setTelegramChannels(ServiceFactory.getTlgm().getUserChannels(userId));
            return success(mapping);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
