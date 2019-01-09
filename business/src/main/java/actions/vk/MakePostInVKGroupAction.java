package actions.vk;

import actionForms.MakePostInVKGroupActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MakePostInVKGroupAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MakePostInVKGroupActionForm form = (MakePostInVKGroupActionForm) frm;

        Long userId = UserUtils.getCurrentUser().getId();
        Integer groupId = form.getGroupId();
        String message = form.getMessage();
        List<String> attachments = form.getAttachments();
        Integer publishDate = form.getPublishDate();
        Boolean fromGroup = form.getFromGroup();

        ServiceFactory.getVK().createPost(userId,
                groupId,
                message,
                attachments,
                publishDate,
                fromGroup);

        return success(mapping);
    }
}
