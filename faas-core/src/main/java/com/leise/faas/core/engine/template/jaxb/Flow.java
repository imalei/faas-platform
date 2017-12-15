package com.leise.faas.core.engine.template.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "flow")
@XmlType(propOrder = { "id", "name", "inParams", "outParams", "operations" })
public class Flow {

	private String id;

	private String name;
	
	private List<Data> inParams;
	
	private List<Data> outParams;

	private List<Operation> operations;

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

	@XmlElementWrapper(name = "operations")
	@XmlElement(name = "operation", type = Operation.class, required = true)
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@XmlElementWrapper(name = "input")
	@XmlElement(name = "param", type = Data.class, required = true)
	public List<Data> getInParams() {
		return inParams;
	}

	public void setInParams(List<Data> inParams) {
		this.inParams = inParams;
	}
	
	@XmlElementWrapper(name = "output")
	@XmlElement(name = "param", type = Data.class, required = true)
	public List<Data> getOutParams() {
		return outParams;
	}

	public void setOutParams(List<Data> outParams) {
		this.outParams = outParams;
	}

}
