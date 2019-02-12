package com.smirix.services.apps;

import com.smirix.hibernate.HibernateExecutor;
import com.smirix.services.BusinessService;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виктор on 10.02.2019.
 */
public class AppServicesService extends BusinessService {

    private static final Object sync = new Object();
    private static volatile Map<String, AppService> serviceMap = new HashMap<>();

    private AppService getAppServiceByType(String type) {
        return new HibernateExecutor<AppService>().execute((session) ->
                {
                    try {
                        Query<AppService> query = session.createNamedQuery("com.smirix.services.apps.AppService.getAppServiceByType", AppService.class);
                        query.setParameter("type", type);

                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
        );
    }

    public AppService getByType(String type) {
        if (serviceMap.get(type) == null) {
            synchronized (sync) {
                return serviceMap.putIfAbsent(type, getAppServiceByType(type));
            }
        }
        return serviceMap.get(type);
    }
}
