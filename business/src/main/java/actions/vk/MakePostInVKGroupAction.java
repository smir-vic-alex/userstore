package actions.vk;

import actionForms.MakePostInVKGroupActionForm;
import com.vk.api.sdk.client.actors.UserActor;
import entities.User;
import entities.VKGroupNetwork;
import entities.VKUserNetwork;
import hibernate.services.NetworksService;
import networks.vk.connectors.VKConnectorManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakePostInVKGroupAction extends VKAction
{
    private static final NetworksService networksService = new NetworksService();
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        MakePostInVKGroupActionForm form = (MakePostInVKGroupActionForm) frm;

        User user = UserUtils.getCurrentUser();
        VKGroupNetwork groupNetwork = networksService.getVKGroupNetworkByUserId(Integer.parseInt(form.getGroupId()));
        VKUserNetwork userNetwork = networksService.getVKUserNetworkByUserId(user.getId());

        VKConnectorManager.getInstance().createPost(getUserActor(userNetwork), -groupNetwork.getVkUserId(), form.getMessage(), form.getTime());

        return success(mapping);
    }

    private UserActor getUserActor(VKUserNetwork vkUserNetwork){
        return new UserActor(vkUserNetwork.getVkUserId(), vkUserNetwork.getVkAccessCode());
    }
}
