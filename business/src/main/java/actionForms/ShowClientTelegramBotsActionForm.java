package actionForms;

import com.smirix.pojo.TelegramUser;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ShowClientTelegramBotsActionForm extends ActionFormBase {

    private List<TelegramUser> bots;

    public List<TelegramUser> getBots() {
        return bots;
    }

    public void setBots(List<TelegramUser> bots) {
        this.bots = bots;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
