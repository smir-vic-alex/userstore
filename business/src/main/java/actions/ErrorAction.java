package actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-10
 */
public class ErrorAction extends Action {
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return success(mapping);
    }
}
