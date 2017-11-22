package com.leise.faas.core.engine.template;

import java.util.Map;

public class ProcesserTemplate {

	private InputTemplate inputTemplate;

	private OutputTemplate outputTemplate;

	private Map<String, TargetTemplate> targetTemplates;

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

}
