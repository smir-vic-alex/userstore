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
 * @created on 2019-03-23
 */
public class CreatePostAction extends ActionBase {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        return show(mapping);
    }
}
