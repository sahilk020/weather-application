package com.jay.wishlistservice.caching;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

	@Override
    public void onEvent(
      CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("Cache added with [key = {}, Old Value = {}, New Value = {}",
          cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }

}
