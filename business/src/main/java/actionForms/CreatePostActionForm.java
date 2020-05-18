package actionForms;

import com.smirix.entities.DelayedVKPost;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import com.smirix.utils.DateUtils;
import com.smirix.utils.StringUtils;
import com.smirix.utils.WebContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Smirnov-VA
 * @created on 2019-03-23
 */
public class CreatePostActionForm extends ActionFormBase {

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}");

    private static Pattern TIME_PATTERN = Pattern.compile("^\\d{2}:\\d{2}:00");

    private Long taskId;
    private Long groupId;
    private VKUser vkUser;
    private List<VKGroup> vkGroups;
    private DelayedVKPost delayedVKPost;
    private Long[] vkGroupId;
    private String postText;
    private String calendar;
    private String time;
    private boolean isPlanned;
    private boolean isFromGroup;
    private boolean isAddSign;
    private boolean isCommercial;
    private FormFile fileUpload;

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long[] getVkGroupId() {
        return vkGroupId;
    }

    public void setVkGroupId(Long[] vkGroupId) {
        this.vkGroupId = vkGroupId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public boolean getIsPlanned() {
        return isPlanned;
    }

    public void setIsPlanned(boolean isPlanned) {
        this.isPlanned = isPlanned;
    }

    public void setIsFromGroup(boolean isFromGroup) {
        this.isFromGroup = isFromGroup;
    }

    public boolean getIsFromGroup() {
        return isFromGroup;
    }

    public boolean getIsAddSign() {
        return isAddSign;
    }

    public void setIsAddSign(boolean isAddSign) {
        this.isAddSign = isAddSign;
    }

    public boolean getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(boolean isCommercial) {
        this.isCommercial = isCommercial;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DelayedVKPost getDelayedVKPost() {
        return delayedVKPost;
    }

    public void setDelayedVKPost(DelayedVKPost delayedVKPost) {
        this.delayedVKPost = delayedVKPost;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

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

    public boolean getIsOneGroup() {
        return CollectionUtils.isNotEmpty(vkGroups) && vkGroups.size() == 1;
    }

    @Override
    public boolean validate() {
        if (ArrayUtils.isEmpty(getVkGroupId())) {
            return error("Необходимо выбрать хотя бы одну группу для постинга!");
        }

        if (getIsPlanned()) {
            String calendar = getCalendar();
            if (calendar == null || !DATE_PATTERN.matcher(calendar).matches()) {
                return error("Неверный формат даты!");
            }

            String time = getTime();
            if (time == null || !TIME_PATTERN.matcher(time + ":00").matches()) {
                return error( "Неверный формат времени!");
            }

            if (Calendar.getInstance().after(DateUtils.getDate(calendar + " " + time + ":00"))) {
                return error("Дата запланированного поста не может быть меньше текущей!");
            }

        }

        if (StringUtils.isEmpty(getPostText()) && getFileUpload() == null) {
            return error("Заполните текст поста или добавьте картинку");
        }

        return true;
    }

    private boolean error(String s) {
        WebContext.getCurrentRequest().setAttribute(Globals.ERROR_KEY, s);
        return false;
    }
}
