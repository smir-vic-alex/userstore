package actionForms;

import com.smirix.pojo.TelegramChannel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ShowClientTelegramChannelsActionForm extends ActionFormBase {
    private List<TelegramChannel> channels;

    public List<TelegramChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TelegramChannel> channels) {
        this.channels = channels;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
