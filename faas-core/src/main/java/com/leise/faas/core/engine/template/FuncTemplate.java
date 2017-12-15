package com.leise.faas.core.engine.template;

import java.util.HashMap;
import java.util.Map;

public class FuncTemplate {

	private String id;

	private String name;

	private String refClass;

	private InputTemplate inputTemplate;

	private OutputTemplate outputTemplate;

	private Map<String, TargetTemplate> targetTemplates;
	
	private Map<String, String> initParams;

	public FuncTemplate(String id, String name, String refClass) {
		this.id = id;
		this.name = name;
		this.refClass = refClass;
		targetTemplates = new HashMap<>();
		inputTemplate = new InputTemplate();
		outputTemplate = new OutputTemplate();
		initParams = new HashMap<>();
	}

	public InputTemplate getInputTemplate() {
		return inputTemplate;
	}

	public void setInputTemplate(InputTemplate inputTemplate) {
		this.inputTemplate = inputTemplate;
	}

	public OutputTemplate getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(OutputTemplate outputTemplate) {
		this.outputTemplate = outputTemplate;
	}

	public Map<String, TargetTemplate> getTargetTemplates() {
		return targetTemplates;
	}

	public void setTargetTemplates(Map<String, TargetTemplate> targetTemplates) {
		this.targetTemplates = targetTemplates;
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

	public String getRefClass() {
		return refClass;
	}

	public void setRefClass(String refClass) {
		this.refClass = refClass;
	}

	public Map<String, String> getInitParams() {
		return initParams;
	}

	public void setInitParams(Map<String, String> initParams) {
		this.initParams = initParams;
	}

}
