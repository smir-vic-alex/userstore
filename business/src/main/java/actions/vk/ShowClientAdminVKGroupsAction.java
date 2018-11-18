package actions.vk;

import actionForms.ShowClientAdminVKGroupActionForm;
import actions.LookupDispatchAction;
import com.vk.api.sdk.objects.groups.GroupFull;
import entities.User;
import entities.VKUserNetwork;
import hibernate.services.NetworksService;
import networks.vk.connectors.VKConnectorManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 15.09.2017.
 */
public class ShowClientAdminVKGroupsAction extends LookupDispatchAction
{
    private static final NetworksService networkService = new NetworksService();

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        User user = UserUtils.getCurrentUser();
        VKUserNetwork vkUserNetwork = networkService.getVKUserNetworkByUserId(user.getId());
        VKConnectorManager connector = VKConnectorManager.getInstance();
        List<GroupFull> list =  connector.getGroups(vkUserNetwork);

        ShowClientAdminVKGroupActionForm actionForm = (ShowClientAdminVKGroupActionForm) form;
        actionForm.setVkGroups(convertToVKGroupList(list));

        return success(mapping);
    }

    private List<VKGroup> convertToVKGroupList(List<GroupFull> groupFulls)
    {
        List<VKGroup> vkGroupList = new ArrayList<>(groupFulls.size());
        for (GroupFull group : groupFulls) {
            VKGroup vkGroup = new VKGroup();

            vkGroup.setId(group.getId());
            vkGroup.setName(group.getName());
            vkGroup.setUrlPhoto(group.getPhoto50());
            vkGroupList.add(vkGroup);
        }
        return vkGroupList;
    }
}
