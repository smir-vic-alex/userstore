package actionForms;

import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import com.smirix.pojo.TelegramChannel;
import com.smirix.pojo.TelegramUser;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-24
 */
public class MainPageActionForm extends ActionForm {

    private VKUser vkUser;
    private List<VKGroup> vkGroups;
    private List<TelegramUser> telegramBots;
    private List<TelegramChannel> telegramChannels;

    public VKUser getVkUser() {
        return vkUser;
    }

    public void setVkUser(VKUser vkUser) {
        this.vkUser = vkUser;
    }

    public List<VKGroup> getVkGroups() {
        return vkGroups;
    }

    public void setVkGroups(List<VKGroup> vkGroups) {
        this.vkGroups = vkGroups;
    }

    public List<TelegramUser> getTelegramBots() {
        return telegramBots;
    }

    public void setTelegramBots(List<TelegramUser> telegramBots) {
        this.telegramBots = telegramBots;
    }

    public List<TelegramChannel> getTelegramChannels() {
        return telegramChannels;
    }

    public void setTelegramChannels(List<TelegramChannel> telegramChannels) {
        this.telegramChannels = telegramChannels;
    }
}
