package actions;

import actionForms.CreatePostActionForm;
import com.smirix.entities.DelayedVKPost;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import com.smirix.senders.user.requests.UserGroupsRs;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-23
 */
public class EditPostAction extends ActionBase {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CreatePostActionForm form = (CreatePostActionForm) frm;
        Long userId = UserUtils.getCurrentUser().getId();
        VKUser vkUser = ServiceFactory.getVK().getUser(userId);

        UserGroupsRs rs = ServiceFactory.getVK().getUserGroups(userId, false);
        List<VKGroup> groups = rs.getGroups();
        List<DelayedVKPost> delayedVKPosts = rs.getDelayedVKPosts();

        Long taskId = form.getTaskId();
        Long groupId = form.getGroupId();
        form.setVkUser(vkUser);

        if (taskId != null && taskId > 0 && groupId != null && groupId > 0) {
            for (DelayedVKPost delayedVKPost : delayedVKPosts) {
                if (groupId.equals(delayedVKPost.getOwnerId().longValue()) && taskId.equals(delayedVKPost.getTaskId())) {
                    form.setDelayedVKPost(delayedVKPost);
                    return show(mapping);
                }
            }
        }


        if (vkUser != null && vkUser.getVkUserId() != null) {
            form.setVkGroups(groups);
        }

        return show(mapping);
    }
}
