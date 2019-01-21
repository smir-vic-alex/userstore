package actionForms;

import com.smirix.pojo.TelegramUser;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ShowClientTelegramBotsActionForm extends ActionForm {

    private List<TelegramUser> bots;

    public List<TelegramUser> getBots() {
        return bots;
    }

    public void setBots(List<TelegramUser> bots) {
        this.bots = bots;
    }
}
