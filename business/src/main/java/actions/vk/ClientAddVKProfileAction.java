package actions.vk;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import settings.ApplicationGlobalSetting;
import settings.SettingFactory;
import settings.vk.VKApiUserSetting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKProfileAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(SettingFactory.getSetting(ApplicationGlobalSetting.class).isUseStub())
            return mapping.findForward("stub");
        else
           return new ActionForward(SettingFactory.getSetting(VKApiUserSetting.class).getAuthUrl(), true);
    }
}
