package actions.vk;

import entities.User;
import networks.vk.connectors.VKConnectorManager;

/**
 *
 * Created by Виктор on 30.09.2017.
 */
public class CodeVKGroupAction extends CodeVKAction {
    @Override
    protected void getVKDataAndSave(User user, String code) throws Exception
    {
        networkService.saveOrUpdateVkGroupsNetworkCode(user.getId(), VKConnectorManager.getInstance().authGroup(code));
    }
}
