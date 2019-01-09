package actions;

import actionForms.CreatePostActionForm;
import com.smirix.entities.user.User;
import com.smirix.entities.VKGroupActor;
import com.smirix.services.VkService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Виктор on 15.10.2017.
 */
public class CreatePostAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CreatePostActionForm form = (CreatePostActionForm) frm;

        User user = UserUtils.getCurrentUser();
        List<VKGroupActor> groupNetworks = null;//ServiceFactory.getVK().getVKGroupNetworksByUserId(user.getId());

        form.setAvailableNetworks(groupNetworks);
        return success(mapping);
    }
}
