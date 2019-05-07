package actions.vk;

import actionForms.MakePostInVKGroupActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class MakePostInVKGroupAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MakePostInVKGroupActionForm form = (MakePostInVKGroupActionForm) frm;

        Long userId = UserUtils.getCurrentUser().getId();
        List<Long> groupIds = Arrays.asList(form.getVkGroupId());
        String message = form.getPostText();
//        List<String> attachments = form.getAttachments();

        Boolean fromGroup = form.getIsFromGroup();

        for (Long groupId : groupIds) {
            ServiceFactory.getVK().createPost(userId,
                    form.getTaskId(),
                    groupId.intValue(),
                    message,
                    null,
                    form.getCalendar() + " " + form.getTime() + ":00",
                    fromGroup,
                    false);
        }
        return success(mapping);
    }
}
