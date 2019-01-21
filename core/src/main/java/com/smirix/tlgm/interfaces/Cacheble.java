package com.smirix.tlgm.interfaces;

public interface Cacheble<C, K, V> {

    C getCache();

    V getCachedObj(K key);

}
