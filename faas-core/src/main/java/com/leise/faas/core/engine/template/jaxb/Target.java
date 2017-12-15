package com.leise.faas.core.engine.template.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "condition" })
public class Target {

	private String id;

	private String condition;

	public Target() {

	}

	public Target(String id, String condition) {
		this.id = id;
		this.condition = condition;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
