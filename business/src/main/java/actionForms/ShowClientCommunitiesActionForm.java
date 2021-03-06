package actionForms;

import com.smirix.entities.VKGroup;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Виктор on 15.09.2017.
 */
public class ShowClientCommunitiesActionForm extends ActionFormBase {
    private List<VKGroup> vkGroups;
    private List<VKGroup> notConnectedVkGroups;

    public List<VKGroup> getVkGroups() {
        return vkGroups;
    }

    public void setVkGroups(List<VKGroup> vkGroups) {
        this.vkGroups = vkGroups;
    }

    public List<VKGroup> getNotConnectedVkGroups() {
        return notConnectedVkGroups;
    }

    public void setNotConnectedVkGroups(List<VKGroup> notConnectedVkGroups) {
        this.notConnectedVkGroups = notConnectedVkGroups;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
