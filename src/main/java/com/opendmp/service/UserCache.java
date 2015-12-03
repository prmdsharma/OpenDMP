package com.opendmp.service;

/**
 * Created by pramod.sharma on 02/12/15.
 */

import org.springframework.stereotype.Service;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import com.google.common.cache.Cache;

@Service
public class UserCache {
    private Cache<String, AtomicLong> userMinuteCache = CacheBuilder.newBuilder().maximumSize(100000).expireAfterAccess(1, TimeUnit.MINUTES).build();
    private Cache<String, AtomicLong> userHourCache = CacheBuilder.newBuilder().maximumSize(1000000).expireAfterAccess(1,TimeUnit.HOURS).build();
    private Cache<String, AtomicLong> userDayCache = CacheBuilder.newBuilder().maximumSize(10000000).expireAfterAccess(1,TimeUnit.DAYS).build();

    public void updateUserMinuteCache(String key) {
        AtomicLong uHits = new AtomicLong(0);
        userMinuteCache.getIfPresent(uHits);
        uHits.incrementAndGet();
        userMinuteCache.put(key,uHits);
    }

    public AtomicLong getUseMinuterHits(String key) {
        AtomicLong uHits = new AtomicLong();
        userMinuteCache.getIfPresent(uHits);

        return uHits;
    }
    public void updateUserHourCache(String key) {
        AtomicLong uHits = new AtomicLong(0);
        userHourCache.getIfPresent(uHits);
        uHits.incrementAndGet();
        userHourCache.put(key, uHits);
    }

    public AtomicLong getUserHourHits(String key) {
        AtomicLong uHits = new AtomicLong();
        userHourCache.getIfPresent(uHits);

        return uHits;
    }

    public void updateUserDayCache(String key) {
        AtomicLong uHits = new AtomicLong(0);
        userDayCache.getIfPresent(uHits);
        uHits.incrementAndGet();
        userDayCache.put(key, uHits);
    }

    public AtomicLong getUserDayHits(String key) {
        AtomicLong uHits = new AtomicLong();
        userDayCache.getIfPresent(uHits);

        return uHits;
    }
}
