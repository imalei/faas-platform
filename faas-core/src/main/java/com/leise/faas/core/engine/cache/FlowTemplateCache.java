package com.leise.faas.core.engine.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.leise.faas.core.engine.template.FlowTemplate;

public class FlowTemplateCache {

	// 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
	LoadingCache<String, FlowTemplate> cache = CacheBuilder.newBuilder()
			// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
			.concurrencyLevel(8)
			// 设置写缓存后8秒钟过期
			.expireAfterWrite(18, TimeUnit.SECONDS)
			// 设置缓存容器的初始容量为10
			.initialCapacity(2)
			// 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
			.maximumSize(2)
			// 设置要统计缓存的命中率
			.recordStats()
			// 设置缓存的移除通知
			.removalListener(new RemovalListener<String, Object>() {
				public void onRemoval(RemovalNotification<String, Object> notification) {
					System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
				}
			})

			// build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
			.build(new CacheLoader<String, FlowTemplate>() {
				@Override
				public FlowTemplate load(String key) throws Exception {
					return null;
				}

			});

}
