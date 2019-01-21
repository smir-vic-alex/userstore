package actionForms;

import com.smirix.pojo.TelegramChannel;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class MakePostInChannelActionForm extends ActionForm {

    private List<TelegramChannel> channels;
    private String channelName;
    private String message;

    public List<TelegramChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TelegramChannel> channels) {
        this.channels = channels;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
