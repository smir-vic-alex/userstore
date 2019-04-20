package actions.vk;

import actionForms.CreatePostVKActionForm;
import actions.Action;
import com.smirix.entities.VKGroup;
import com.smirix.entities.user.User;
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
public class CreatePostVKAction extends Action {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CreatePostVKActionForm form = (CreatePostVKActionForm) frm;

        User user = UserUtils.getCurrentUser();
        List<VKGroup> groupNetworks = ServiceFactory.getVK().getUserGroups(user.getId(),false).getGroups();

        form.setAvailableNetworks(groupNetworks);
        return success(mapping);
    }
}