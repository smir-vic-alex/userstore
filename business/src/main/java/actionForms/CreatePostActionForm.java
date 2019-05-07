package actionForms;

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
    private VKUser vkUser;
    private List<VKGroup> vkGroups;

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
