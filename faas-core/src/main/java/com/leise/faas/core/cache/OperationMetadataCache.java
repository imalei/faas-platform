package com.leise.faas.core.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leise.faas.core.cache.metadata.OperationMetadata;
import com.leise.faas.core.engine.template.jaxb.Operation;
import com.leise.faas.core.exception.FaasCoreException;

public class OperationMetadataCache {

	private static LoadingCache<Operation, OperationMetadata> operationMetadataLoadingCache = CacheBuilder.newBuilder()
			.build(new CacheLoader<Operation, OperationMetadata>() {
				@Override
				public OperationMetadata load(Operation key) throws Exception {
					return new OperationMetadata(key);
				}
			});

	public static OperationMetadata getOperationMetadata(Operation key) {
		try {
			return operationMetadataLoadingCache.get(key);
		} catch (ExecutionException e) {
			throw new FaasCoreException("EX_LOADING_CACHE", e);
		}
	}
}
