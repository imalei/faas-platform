package com.leise.faas.core.engine.template;

import java.util.HashMap;
import java.util.Map;

public class InputTemplate {

	private Map<String, ParamTemplate> params;

	public InputTemplate() {
		params = new HashMap<>();
	}

	public Map<String, ParamTemplate> getParams() {
		return params;
	}

	public void setParams(Map<String, ParamTemplate> params) {
		this.params = params;
	}

}
