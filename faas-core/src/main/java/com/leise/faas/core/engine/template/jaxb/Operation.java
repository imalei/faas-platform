package com.leise.faas.core.engine.template.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "name", "refClass", "initParams", "inParams", "outParams", "targets" })
public class Operation {

	private String id;

	private String name;

	private String refClass;

	private List<Data> inParams;

	private List<Data> outParams;

	private List<Data> initParams;

	private List<Target> targets;
	

	@XmlAttribute(required = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(required = true)
	public String getRefClass() {
		return refClass;
	}

	public void setRefClass(String refClass) {
		this.refClass = refClass;
	}

	@XmlElementWrapper(name = "input")
	@XmlElement(name = "data", type = Data.class)
	public List<Data> getInParams() {
		return inParams;
	}

	public void setInParams(List<Data> inParams) {
		this.inParams = inParams;
	}

	@XmlElementWrapper(name = "output")
	@XmlElement(name = "data", type = Data.class)
	public List<Data> getOutParams() {
		return outParams;
	}

	public void setOutParams(List<Data> outParams) {
		this.outParams = outParams;
	}

	@XmlElementWrapper(name = "init")
	@XmlElement(name = "data", type = Data.class)
	public List<Data> getInitParams() {
		return initParams;
	}

	public void setInitParams(List<Data> initParams) {
		this.initParams = initParams;
	}

	@XmlElementWrapper(name = "targets")
	@XmlElement(name = "target", type = Target.class)
	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

}
