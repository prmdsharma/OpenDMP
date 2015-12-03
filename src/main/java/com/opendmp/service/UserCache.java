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
	private Cache<String, AtomicLong> userMinuteCache = CacheBuilder
			.newBuilder().maximumSize(100000)
			.expireAfterAccess(1, TimeUnit.MINUTES).build();
	private Cache<String, AtomicLong> userHourCache = CacheBuilder.newBuilder()
			.maximumSize(1000000).expireAfterAccess(1, TimeUnit.HOURS).build();
	private Cache<String, AtomicLong> userDayCache = CacheBuilder.newBuilder()
			.maximumSize(10000000).expireAfterAccess(1, TimeUnit.DAYS).build();

	public void updateCount(Cache<String, AtomicLong> cache, String key) {
		AtomicLong uHits = cache.getIfPresent(key);
		if (uHits == null) {
			uHits = new AtomicLong(1);
			cache.put(key, uHits);
		} else {
			uHits.incrementAndGet();
		}
	}

	public AtomicLong getCount(Cache<String, AtomicLong> cache, String key) {
		AtomicLong uHits = cache.getIfPresent(key);
		if (uHits == null) {
			return new AtomicLong(0);
		}
		return uHits;
	}

	public void updateUserMinuteCache(String key) {
		updateCount(userMinuteCache, key);
	}

	public AtomicLong getUseMinuterHits(String key) {
		return getCount(userMinuteCache, key);
	}

	public void updateUserHourCache(String key) {
		updateCount(userHourCache, key);
	}

	public AtomicLong getUserHourHits(String key) {

		return getCount(userHourCache, key);
	}

	public void updateUserDayCache(String key) {
		updateCount(userDayCache, key);
	}

	public AtomicLong getUserDayHits(String key) {
		return getCount(userDayCache, key);
	}
}
