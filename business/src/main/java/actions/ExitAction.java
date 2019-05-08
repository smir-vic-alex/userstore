package actions;

import com.smirix.services.apps.AppService;
import com.smirix.services.apps.AppServicesService;
import com.smirix.utils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-07
 */
public class ExitAction extends Action {
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserUtils.setUserIsLogin(false);
        UserUtils.setCurrentUser(null);

        AppService appService = BeanUtils.getBean("appServicesService", AppServicesService.class).getByType("AUTH");


        return new ActionForward(appService.getHost(), true);
    }
}
