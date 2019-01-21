package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ClientAddTelegramChannelActionForm extends ActionForm {

    private String nameChannel;
    private Long botId;

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }
}
