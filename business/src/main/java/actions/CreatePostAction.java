package actions;

import actionForms.CreatePostActionForm;
import com.smirix.entities.VKUser;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-23
 */
public class CreatePostAction extends ActionBase {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CreatePostActionForm form = (CreatePostActionForm) frm;
        Long userId = UserUtils.getCurrentUser().getId();

        VKUser vkUser = ServiceFactory.getVK().getUser(userId);
        if (vkUser != null && vkUser.getVkUserId() != null) {
            form.setVkGroups(ServiceFactory.getVK().getUserGroups(userId));
            form.setVkUser(vkUser);
        }

        return show(mapping);
    }
}
