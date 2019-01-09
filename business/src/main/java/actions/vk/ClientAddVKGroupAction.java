package actions.vk;

import actionForms.ClientAddVKGroupActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import settings.ApplicationGlobalSetting;
import settings.SettingFactory;
import settings.vk.VKApiGroupSetting;
import utils.VKUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKGroupAction extends VKAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(SettingFactory.getSetting(ApplicationGlobalSetting.class).isUseStub())
            return mapping.findForward("stub");
        else
        {
            ClientAddVKGroupActionForm form = (ClientAddVKGroupActionForm) frm;
            String groupId = form.getGroupId();
            String url = VKUtils.insertGroupIdIntoRequestUrl(groupId, SettingFactory.getSetting(VKApiGroupSetting.class).getAuthUrl());
            return new ActionForward(url, true);
        }
    }
}
