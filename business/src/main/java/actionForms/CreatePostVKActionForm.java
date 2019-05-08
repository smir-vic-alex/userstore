package actionForms;

import com.smirix.entities.VKGroup;
import com.smirix.entities.VKGroupActor;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Виктор on 15.10.2017.
 */
public class CreatePostVKActionForm extends ActionFormBase {
    private List<VKGroup> availableNetworks;
    private String groupId;
    private String message;

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

    public List<VKGroup> getAvailableNetworks() {
        return availableNetworks;
    }

    public void setAvailableNetworks(List<VKGroup> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
