package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKGroupActionForm extends ActionForm {
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
