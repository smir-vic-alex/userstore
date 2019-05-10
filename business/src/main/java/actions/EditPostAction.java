package actions;

import actionForms.CreatePostActionForm;
import com.smirix.entities.DelayedVKPost;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import com.smirix.senders.user.requests.UserGroupsRs;
import com.smirix.utils.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Calendar;
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

        if (ArrayUtils.isNotEmpty(form.getVkGroupId()) || StringUtils.isNotEmpty(form.getPostText())) {
            createPost(form, userId);
        }

        VKUser vkUser = ServiceFactory.getVK().getUser(userId);

        UserGroupsRs rs = ServiceFactory.getVK().getUserGroups(userId, false);
        List<VKGroup> groups = rs.getGroups();
        List<DelayedVKPost> delayedVKPosts = rs.getDelayedVKPosts();

        Long taskId = form.getTaskId();
        Long groupId = form.getGroupId();
        form.setVkUser(vkUser);

        if (taskId != null && taskId > 0 && groupId != null && groupId > 0) {
            if (isFoundTaskToEdit(form, delayedVKPosts, taskId, groupId))
                return show(mapping);
        }

        if (vkUser != null && vkUser.getVkUserId() != null) {
            form.setVkGroups(groups);
        }

        return show(mapping);
    }

    private boolean isFoundTaskToEdit(CreatePostActionForm form, List<DelayedVKPost> delayedVKPosts, Long taskId, Long groupId) {
        for (DelayedVKPost delayedVKPost : delayedVKPosts) {
            if (groupId.equals(delayedVKPost.getOwnerId().longValue()) && taskId.equals(delayedVKPost.getTaskId())) {
                form.setDelayedVKPost(delayedVKPost);
                return true;
            }
        }
        return false;
    }

    private void createPost(CreatePostActionForm form, Long userId) {

        if (form.validate()) {
            String message = form.getPostText();
//        List<String> attachments = form.getAttachments();

            Boolean fromGroup = form.getIsFromGroup();

            String dateExecute = getDateExecute(form);
            List<Long> groupIds = Arrays.asList(form.getVkGroupId());
            for (Long groupId : groupIds) {
                ServiceFactory.getVK().createPost(userId,
                        form.getTaskId(),
                        groupId.intValue(),
                        message,
                        null,
                        dateExecute,
                        fromGroup,
                        false);
            }

            if (form.getIsPlanned()) {
                setUserMessage("Пост запланирован и будет исполнен " + dateExecute);
            } else {
                setUserMessage("Пост отправлен");
            }
        }
    }

    private String getDateExecute(CreatePostActionForm form) {
        if (form.getIsPlanned()) {
            return form.getCalendar() + " " + form.getTime() + ":00";
        } else {
            return null;
        }
    }
}
