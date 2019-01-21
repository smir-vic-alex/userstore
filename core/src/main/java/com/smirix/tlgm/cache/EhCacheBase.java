package com.smirix.tlgm.cache;

import com.smirix.tlgm.interfaces.Cacheble;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

public abstract class EhCacheBase implements Cacheble<Cache, String, Object> {
    protected CacheManager cacheManager;
    protected String cacheName;

    public EhCacheBase(String xmlConfigPath, String cacheName) {
        this.cacheName = cacheName;
        cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(getClass().getResource(xmlConfigPath)));
        cacheManager.init();
    }

    public abstract Cache getCache();

    public abstract Object getCachedObj(String key);

}
