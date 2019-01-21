package actionForms;

import com.smirix.pojo.TelegramChannel;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ShowClientTelegramChannelsActionForm extends ActionForm {
    private List<TelegramChannel> channels;

    public List<TelegramChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TelegramChannel> channels) {
        this.channels = channels;
    }
}
