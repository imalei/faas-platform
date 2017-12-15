package com.leise.faas.core.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leise.faas.core.cache.metadata.FlowMetadata;
import com.leise.faas.core.engine.template.jaxb.Flow;
import com.leise.faas.core.exception.FaasCoreException;

public class FlowMetadataCache {

	private static LoadingCache<Flow, FlowMetadata> flowMetadataLoadingCache = CacheBuilder.newBuilder()
			.build(new CacheLoader<Flow, FlowMetadata>() {
				@Override
				public FlowMetadata load(Flow key) throws Exception {
					return new FlowMetadata(key);
				}
			});

	public static FlowMetadata getFlowMetadata(Flow key) {
		try {
			return flowMetadataLoadingCache.get(key);
		} catch (ExecutionException e) {
			throw new FaasCoreException("EX_LOADING_CACHE", e);
		}
	}
}
