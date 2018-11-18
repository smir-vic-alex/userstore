package actionForms;

import entities.VKGroupNetwork;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Created by Виктор on 15.10.2017.
 */
public class CreatePostActionForm extends ActionForm {
    private List<VKGroupNetwork> availableNetworks;
    private String networkType;
    private String groupId;
    private String message;

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VKGroupNetwork> getAvailableNetworks() {
        return availableNetworks;
    }

    public void setAvailableNetworks(List<VKGroupNetwork> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }
}
