package actions.vk;

import actionForms.MakePostInVKGroupActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.ServiceFactory;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakePostInVKGroupAction extends VKAction {

    private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MakePostInVKGroupActionForm form = (MakePostInVKGroupActionForm) frm;

        Long userId = UserUtils.getCurrentUser().getId();
        Integer groupId = form.getVkGroupId()[0].intValue();
        String message = form.getPostText();
//        List<String> attachments = form.getAttachments();
        Calendar publishDate = Calendar.getInstance();
        publishDate.setTime(getDate(form.getCalendar(), form.getTime()));

        Integer publishDateSeconds = (int) ((publishDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 1000L);
        Boolean fromGroup = form.getIsFromGroup();


        ServiceFactory.getVK().createPost(userId,
                groupId,
                message,
                null,
                publishDateSeconds,
                fromGroup);

        return success(mapping);
    }


    private DateFormat getFormatter() {
        return (DateFormat)formatter.clone();
    }

    private Date getDate(String dateString, String timeString) {
        Date date = null;
        if (!"".equals(dateString)) {
            try {
                date = getFormatter().parse(dateString + " " + timeString + ":00");
            } catch (ParseException e) {
                throw new RuntimeException("Ошибка при получении даты " + e);
            }
        }
        return date;
    }
}
