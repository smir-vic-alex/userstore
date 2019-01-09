package actionForms;

import org.apache.struts.action.ActionForm;

import java.util.List;

public class MakePostInVKGroupActionForm extends ActionForm {
    private String message;
    private Integer groupId;
    private String typeNetwork;
    private int time;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getAttachments() {
        return null;
    }

    public Integer getPublishDate() {
        return null;
    }

    public Boolean getFromGroup() {
        return null;
    }
}
