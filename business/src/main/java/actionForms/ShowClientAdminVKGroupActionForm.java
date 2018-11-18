package actionForms;

import actions.vk.VKGroup;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Created by Виктор on 15.09.2017.
 */
public class ShowClientAdminVKGroupActionForm extends ActionForm {
    private List<VKGroup> vkGroups;

    public List<VKGroup> getVkGroups() {
        return vkGroups;
    }

    public void setVkGroups(List<VKGroup> vkGroups) {
        this.vkGroups = vkGroups;
    }
}
