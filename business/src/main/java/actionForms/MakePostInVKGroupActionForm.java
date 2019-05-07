package actionForms;

import org.apache.struts.action.ActionForm;

public class MakePostInVKGroupActionForm extends ActionForm {
    private Long[] vkGroupId;
    private String postText;
    private String calendar;
    private String time;
    private boolean isPlanned;
    private boolean isFromGroup;
    private boolean isAddSign;
    private boolean isCommercial;
    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long[] getVkGroupId() {
        return vkGroupId;
    }

    public void setVkGroupId(Long[] vkGroupId) {
        this.vkGroupId = vkGroupId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public boolean getIsPlanned() {
        return isPlanned;
    }

    public void setIsPlanned(boolean isPlanned) {
        this.isPlanned = isPlanned;
    }

    public void setIsFromGroup(boolean isFromGroup) {
        this.isFromGroup = isFromGroup;
    }

    public boolean getIsFromGroup() {
        return isFromGroup;
    }

    public boolean getIsAddSign() {
        return isAddSign;
    }

    public void setIsAddSign(boolean isAddSign) {
        this.isAddSign = isAddSign;
    }

    public boolean getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(boolean isCommercial) {
        this.isCommercial = isCommercial;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
