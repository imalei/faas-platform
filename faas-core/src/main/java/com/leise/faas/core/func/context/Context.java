package com.leise.faas.core.func.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

	private Map<String, Object> inParams;

	private Map<String, Object> outParams;
	
	public Context() {
		inParams = new HashMap<>();
		outParams = new HashMap<>();
	}

	public Map<String, Object> getInParams() {
		return inParams;
	}

	public void setInParams(Map<String, Object> inParams) {
		this.inParams = inParams;
	}

	public Map<String, Object> getOutParams() {
		return outParams;
	}

	public void setOutParams(Map<String, Object> outParams) {
		this.outParams = outParams;
	}

}
