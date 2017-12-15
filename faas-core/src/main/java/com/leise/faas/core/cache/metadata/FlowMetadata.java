package com.leise.faas.core.cache.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.leise.faas.core.engine.template.jaxb.Flow;
import com.leise.faas.core.engine.template.jaxb.Operation;

public class FlowMetadata {

	private Flow flow;

	private Map<String, Operation> operationsMap = new HashMap<>();

	public FlowMetadata(Flow flow) {
		this.flow = flow;
		operationsMap.putAll(initOperationsMap(flow.getOperations()));
	}

	public Map<String, Operation> initOperationsMap(List<Operation> operations) {
		return operations.stream().collect(Collectors.toMap(Operation::getId, operation -> operation));
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public Map<String, Operation> getOperationsMap() {
		return operationsMap;
	}

	public void setOperationsMap(Map<String, Operation> operationsMap) {
		this.operationsMap = operationsMap;
	}

}
