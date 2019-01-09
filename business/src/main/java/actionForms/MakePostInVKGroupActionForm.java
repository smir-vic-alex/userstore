package actionForms;

import org.apache.struts.action.ActionForm;

import java.util.List;

public class MakePostInVKGroupActionForm extends ActionForm {
    private String message;
    private Integer groupId;
    private String typeNetwork;
    private String time;
    private boolean fromGroup;

    public String getTypeNetwork() {
        return typeNetwork;
    }

    public void setTypeNetwork(String typeNetwork) {
        this.typeNetwork = typeNetwork;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getAttachments() {
        return null;
    }

    public Boolean getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(boolean fromGroup) {
        this.fromGroup = fromGroup;
    }
}
