package com.ale;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GuavaCacheTest {

    @Test
    public void whenCacheReachMaxSize_thenEviction() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

    @Test
    public void whenEntryLiveTimeExpire_thenEviction()
            throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().initialCapacity(3)
                            .expireAfterWrite(2, TimeUnit.MILLISECONDS)
                            .build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());
        Thread.sleep(300);
        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void whenNullValue_thenOptional() {
        CacheLoader<String, Optional<String>> loader;
        loader = new CacheLoader<String, java.util.Optional<String>>() {
            @Override
            public java.util.Optional<String> load(String key) {
                return java.util.Optional.ofNullable(getSuffix(key));
            }
        };

        LoadingCache<String, Optional<String>> cache;
        cache = CacheBuilder.newBuilder().initialCapacity(3).build(loader);

        assertEquals("txt", cache.getUnchecked("text.txt").get());
        assertFalse(cache.getUnchecked("hello").isPresent());
    }
    private String getSuffix(final String str) {
        int lastIndex = str.lastIndexOf('.');
        if (lastIndex == -1) {
            return null;
        }
        return str.substring(lastIndex + 1);
    }
}
