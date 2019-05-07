package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKGroupActionForm extends ActionForm {
    private String groupId;
    private String userActorAuthUrl;

    public String getUserActorAuthUrl() {
        return userActorAuthUrl;
    }

    public void setUserActorAuthUrl(String userActorAuthUrl) {
        this.userActorAuthUrl = userActorAuthUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
