package utils;

import com.smirix.services.VkNetworkService;

/**
 * Created by Виктор on 07.01.2019.
 */
public class ServiceFactory {

    public static VkNetworkService getVK() {
        return BeanUtils.getBean(VkNetworkService.class);
    }
}
