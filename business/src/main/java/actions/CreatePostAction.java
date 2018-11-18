package actions;

import actionForms.CreatePostActionForm;
import entities.User;
import entities.VKGroupNetwork;
import hibernate.services.NetworksService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Виктор on 15.10.2017.
 */
public class CreatePostAction extends OperationActionBase {
    private static final NetworksService networksService = new NetworksService();

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CreatePostActionForm form = (CreatePostActionForm) frm;

        User user = UserUtils.getCurrentUser();
        List<VKGroupNetwork> groupNetworks = networksService.getVKGroupNetworksByUserId(user.getId());

        form.setAvailableNetworks(groupNetworks);
        return success(mapping);
    }
}
