package actionForms;

import com.smirix.utils.WebContext;
import org.apache.struts.Globals;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-08
 */
public class RemovePostActionForm extends ActionFormBase {

    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean validate() {

        if (taskId == null || taskId <= 0) {
            WebContext.getCurrentRequest().setAttribute(Globals.ERROR_KEY, "Неверный идентификатор для удаления!");
            return false;
        }
        return true;
    }
}
