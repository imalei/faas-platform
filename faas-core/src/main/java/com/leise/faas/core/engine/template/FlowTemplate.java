package com.leise.faas.core.engine.template;

import java.util.Map;

public class FlowTemplate {

	private String id;

	private String name;

	private Map<String, ProcesserTemplate> processerTemplate;

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

	public Map<String, ProcesserTemplate> getProcesserTemplate() {
		return processerTemplate;
	}

	public void setProcesserTemplate(Map<String, ProcesserTemplate> processerTemplate) {
		this.processerTemplate = processerTemplate;
	}

}
