package utils;

import com.smirix.services.TlgmNetworkService;
import com.smirix.services.VkNetworkService;
import com.smirix.utils.BeanUtils;

/**
 * Created by Виктор on 07.01.2019.
 */
public class ServiceFactory {

    public static VkNetworkService getVK() {
        return BeanUtils.getBean(VkNetworkService.class);
    }

    public static TlgmNetworkService getTlgm() {
        return BeanUtils.getBean(TlgmNetworkService.class);
    }
}
