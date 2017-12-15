package com.leise.faas.core.engine.template;

import java.util.HashMap;
import java.util.Map;

public class FlowTemplate {

	private String id;

	private String name;

	private Map<String, FuncTemplate> funcTemplates;

	private String startFuncId;

	public FlowTemplate(String id, String name) {
		this.id = id;
		this.name = name;
		funcTemplates = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, FuncTemplate> getFuncTemplates() {
		return funcTemplates;
	}

	public void setFuncTemplates(Map<String, FuncTemplate> funcTemplates) {
		this.funcTemplates = funcTemplates;
	}

	public String getStartFuncId() {
		return startFuncId;
	}

	public void setStartFuncId(String startFuncId) {
		this.startFuncId = startFuncId;
	}

}
