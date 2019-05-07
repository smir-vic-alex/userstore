package actionForms;

import com.smirix.entities.DelayedVKPost;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-23
 */
public class CreatePostActionForm extends ActionForm {

    private Long taskId;
    private Long groupId;
    private VKUser vkUser;
    private List<VKGroup> vkGroups;
    private DelayedVKPost delayedVKPost;

    public DelayedVKPost getDelayedVKPost() {
        return delayedVKPost;
    }

    public void setDelayedVKPost(DelayedVKPost delayedVKPost) {
        this.delayedVKPost = delayedVKPost;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public VKUser getVkUser() {
        return vkUser;
    }

    public void setVkUser(VKUser vkUser) {
        this.vkUser = vkUser;
    }

    public List<VKGroup> getVkGroups() {
        return vkGroups;
    }

    public void setVkGroups(List<VKGroup> vkGroups) {
        this.vkGroups = vkGroups;
    }

    public boolean getIsOneGroup() {
        return CollectionUtils.isNotEmpty(vkGroups) && vkGroups.size() == 1;
    }
}
