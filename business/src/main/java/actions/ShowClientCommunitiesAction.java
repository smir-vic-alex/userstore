package actions;

import actionForms.ShowClientCommunitiesActionForm;
import com.smirix.entities.VKGroup;
import com.smirix.entities.user.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Виктор on 15.09.2017.
 */
public class ShowClientCommunitiesAction extends ActionBase
{
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ShowClientCommunitiesActionForm actionForm = (ShowClientCommunitiesActionForm) form;
        User user = UserUtils.getCurrentUser();

        setVKGroups(actionForm, user);
        setTelegram(actionForm, user);

        return success(mapping);
    }

    private void setTelegram(ShowClientCommunitiesActionForm actionForm, User user) {

    }

    private void setVKGroups(ShowClientCommunitiesActionForm actionForm, User user) {
        List<VKGroup> storedVKGroups = ServiceFactory.getVK().getUserGroups(user.getId(), false).getGroups();
        List<VKGroup> vkGroupList = ServiceFactory.getVK().getUserGroups(user.getId(), true).getGroups();

        for (VKGroup storedGroup : storedVKGroups) {
            CollectionUtils.filterInverse(vkGroupList, vkGroup -> storedGroup.getVkId().equals(vkGroup.getVkId()));
        }


        actionForm.setNotConnectedVkGroups(vkGroupList);
        actionForm.setVkGroups(storedVKGroups);
    }
}