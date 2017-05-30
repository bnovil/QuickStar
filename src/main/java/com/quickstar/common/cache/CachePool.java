package com.quickstar.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @description 手工缓存实现
 */
public class CachePool implements Cache {
    private static CachePool cachePool;
    private Map<Object, Object> cacheItems = new ConcurrentHashMap<>();

    private CachePool() {
    }

    public static CachePool getInstance() {
        if (cachePool == null) {
            synchronized (CachePool.class) {
                if (cachePool == null) {
                    cachePool = new CachePool();
                }
            }
        }
        return cachePool;
    }

    public int size() {
        return cacheItems.size();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return this.cacheItems;
    }

    @Override
    public ValueWrapper get(Object o) {
        Object value = cacheItems.get(o);
        return toWrapper(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object o, Class<T> aClass) {
        if (cacheItems.containsKey(o)) {
            return (T) cacheItems.get(o);
        }
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {
        cacheItems.put(o, o1);
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {
        if (cacheItems.containsKey(o)) {
            cacheItems.remove(o);
        }
    }

    @Override
    public void clear() {
        cacheItems.clear();
    }

    private static final Object NULL_HOLDER = new NullHolder();

    private static class NullHolder implements Serializable {
    }

    private ValueWrapper toWrapper(Object value) {
        return (value != null ? new SimpleValueWrapper(fromStoreValue(value)) : null);
    }

    private Object fromStoreValue(Object storeValue) {
        if (storeValue == NULL_HOLDER) {
            return null;
        }
        return storeValue;
    }
}