package com.leise.faas.core.cache.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.leise.faas.core.engine.template.jaxb.Operation;
import com.leise.faas.core.engine.template.jaxb.Data;
import com.leise.faas.core.engine.template.jaxb.Target;

public class OperationMetadata {

	private Operation operation;

	private Map<String, Data> inParamsMap = new HashMap<>();
	private Map<String, Data> outParamsMap = new HashMap<>();
	private Map<String, Data> initParamsMap = new HashMap<>();
	private Map<String, Target> targetsMap = new HashMap<>();

	public OperationMetadata(Operation operation) {
		this.operation = operation;
		if (CollectionUtils.isNotEmpty(operation.getInParams())) {
			inParamsMap.putAll(initParamsMap(operation.getInParams()));
		}
		if (CollectionUtils.isNotEmpty(operation.getOutParams())) {
			outParamsMap.putAll(initParamsMap(operation.getOutParams()));
		}
		if (CollectionUtils.isNotEmpty(operation.getInitParams())) {
			initParamsMap.putAll(initParamsMap(operation.getInitParams()));
		}
		if (CollectionUtils.isNotEmpty(operation.getTargets())) {
			targetsMap.putAll(initTargetMap(operation.getTargets()));
		}

	}

	public Map<String, Data> initParamsMap(List<Data> params) {
		return params.stream().collect(Collectors.toMap(Data::getId, param -> param));
	}

	public Map<String, Target> initTargetMap(List<Target> targets) {
		return targets.stream().collect(Collectors.toMap(Target::getId, target -> target));
	}

	public Operation getOperation() {
		return operation;
	}

	public Map<String, Data> getInParamsMap() {
		return inParamsMap;
	}

	public Map<String, Data> getOutParamsMap() {
		return outParamsMap;
	}

	public Map<String, Data> getInitParamsMap() {
		return initParamsMap;
	}

	public Map<String, Target> getTargetsMap() {
		return targetsMap;
	}

}
