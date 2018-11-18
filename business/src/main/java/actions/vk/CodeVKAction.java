package actions.vk;

import actionForms.CodeVKActionForm;
import com.vk.api.sdk.objects.UserAuthResponse;
import entities.User;
import hibernate.services.NetworksService;
import networks.vk.connectors.VKConnectorManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 21.07.2017.
 */
public class CodeVKAction extends VKAction {

    protected static final NetworksService networkService = new NetworksService();

    @Override
    public ActionForward start (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CodeVKActionForm codeVKActionForm = (CodeVKActionForm) form;
        getVKDataAndSave(UserUtils.getCurrentUser(), codeVKActionForm.getCode());

        return success(mapping);
    }

    protected void getVKDataAndSave(User user, String code) throws Exception{
        UserAuthResponse authResponse = VKConnectorManager.getInstance().authUser(code);
        networkService.saveOrUpdateVkNetworkCode( user.getId(), authResponse);
    }

}
