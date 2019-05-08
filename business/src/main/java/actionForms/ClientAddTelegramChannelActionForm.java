package actionForms;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ClientAddTelegramChannelActionForm extends ActionFormBase {

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

    @Override
    public boolean validate() {
        return true;
    }
}
