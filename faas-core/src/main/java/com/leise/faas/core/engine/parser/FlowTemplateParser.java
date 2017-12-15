package com.leise.faas.core.engine.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.leise.faas.core.engine.constant.EngineConstant;
import com.leise.faas.core.engine.template.FlowTemplate;
import com.leise.faas.core.engine.template.FuncTemplate;
import com.leise.faas.core.engine.template.InputTemplate;
import com.leise.faas.core.engine.template.OutputTemplate;
import com.leise.faas.core.engine.template.ParamTemplate;
import com.leise.faas.core.engine.template.TargetTemplate;
import com.leise.faas.core.utils.XmlUtils;

@Component
public class FlowTemplateParser {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value(value = "${faas.flow.locations}")
	private String filePath;

	public FlowTemplate parse(String key) {

		long begin = System.currentTimeMillis();
		String fileName = key + ".xml";

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = {};
		try {
			resources = resolver.getResources(this.filePath + fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream inputStream = null;
		try {
			inputStream = resources[0].getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = XmlUtils.load(inputStream);
		Element rootElement = doc.getRootElement();
		Assert.isTrue(EngineConstant.ROOT_NODE.equalsIgnoreCase(rootElement.getName()),
				"根节点必须为" + EngineConstant.ROOT_NODE);

		FlowTemplate flowTemplate = buildFlowTemplate(rootElement);
		long end = System.currentTimeMillis();

		logger.info("解析文件耗时:{}", end - begin);
		return flowTemplate;
	}

	public FlowTemplate buildFlowTemplate(Element rootElement) {
		Element flowElement = rootElement.element(EngineConstant.FLOW_NODE);
		Map<String, String> attributes = XmlUtils.getNoteAttributeMap(flowElement);
		String id = attributes.get("id");
		String name = attributes.get("name");
		FlowTemplate flowTemplate = new FlowTemplate(id, name);
		buildFuncTemplates(flowElement, flowTemplate);
		return flowTemplate;
	}

	public void buildFuncTemplates(Element flowElement, FlowTemplate flowTemplate) {
		Element funcsElement = flowElement.element(EngineConstant.FUNCS_NODE);
		Iterator<Element> iterator = funcsElement.elementIterator(EngineConstant.FUNC_NODE);

		Map<String, FuncTemplate> funcsMap = new HashMap<>();

		while (iterator.hasNext()) {
			Element funcElement = iterator.next();
			Map<String, String> attributes = XmlUtils.getNoteAttributeMap(funcElement);
			String id = attributes.get("id");
			String name = attributes.get("name");
			String refClass = attributes.get("refClass");
			FuncTemplate funcTemplate = new FuncTemplate(id, name, refClass);
			funcTemplate.setInputTemplate(buildInput(funcElement));
			funcTemplate.setOutputTemplate(buildOutput(funcElement));
			funcTemplate.setTargetTemplates(buildTargets(funcElement));
			funcTemplate.setInitParams(buildInitParams(funcElement));
			funcsMap.put(id, funcTemplate);

			if (refClass.indexOf("StartFunc") != -1) {
				flowTemplate.setStartFuncId(id);
			}
		}

		flowTemplate.setFuncTemplates(funcsMap);
	}

	public InputTemplate buildInput(Element funcElement) {
		Element inputElement = funcElement.element(EngineConstant.INPUT_NODE);

		if (inputElement == null) {
			return null;
		}

		Iterator<Element> iterator = inputElement.elementIterator(EngineConstant.PARAM_NODE);
		Map<String, ParamTemplate> paramMap = new HashMap<>();
		while (iterator.hasNext()) {
			Element paramElement = iterator.next();
			Map<String, String> attributes = XmlUtils.getNoteAttributeMap(paramElement);
			String id = attributes.get("id");
			String name = attributes.get("name");
			ParamTemplate paramTemplate = new ParamTemplate(id, name);
			paramMap.put(id, paramTemplate);
		}

		InputTemplate inputTemplate = new InputTemplate();
		inputTemplate.setParams(paramMap);

		return inputTemplate;

	}

	public OutputTemplate buildOutput(Element funcElement) {
		Element outputElement = funcElement.element(EngineConstant.OUTPUT_NODE);

		if (outputElement == null) {
			return null;
		}

		Iterator<Element> iterator = outputElement.elementIterator(EngineConstant.PARAM_NODE);

		Map<String, ParamTemplate> paramMap = new HashMap<>();
		while (iterator.hasNext()) {
			Element paramElement = iterator.next();
			Map<String, String> attributes = XmlUtils.getNoteAttributeMap(paramElement);
			String id = attributes.get("id");
			String name = attributes.get("name");
			ParamTemplate paramTemplate = new ParamTemplate(id, name);
			paramMap.put(id, paramTemplate);
		}

		OutputTemplate outputTemplate = new OutputTemplate();
		outputTemplate.setParams(paramMap);

		return outputTemplate;
	}

	public Map<String, TargetTemplate> buildTargets(Element funcElement) {
		Element targetsElement = funcElement.element(EngineConstant.TARGETS_NODE);
		Iterator<Element> iterator = targetsElement.elementIterator(EngineConstant.TARGET_NODE);

		Map<String, TargetTemplate> targetsMap = new HashMap<>();
		while (iterator.hasNext()) {
			Element targetElement = iterator.next();
			Map<String, String> attributes = XmlUtils.getNoteAttributeMap(targetElement);
			String id = attributes.get("id");
			String condition = attributes.get("condition");
			TargetTemplate targetTemplate = new TargetTemplate(id, condition);
			targetsMap.put(id, targetTemplate);
		}

		return targetsMap;

	}
	
	public Map<String, String> buildInitParams(Element funcElement) {
		Element initElement = funcElement.element(EngineConstant.INIT_NODE);
		if (initElement == null) {
			return null;
		}
		
		Iterator<Element> iterator = initElement.elementIterator(EngineConstant.PARAM_NODE);

		Map<String, String> initParamMap = new HashMap<>();
		while (iterator.hasNext()) {
			Element targetElement = iterator.next();
			Map<String, String> attributes = XmlUtils.getNoteAttributeMap(targetElement);
			String initParamId = attributes.get("id");
			String initParamValue = attributes.get("value");
			initParamMap.put(initParamId, initParamValue);
		}

		return initParamMap;

	}

	public static void main(String[] args) {
		FlowTemplateParser parser = new FlowTemplateParser();
		parser.parse("F:\\git\\github\\faas-platform\\faas-core\\src\\main\\resouces\\test.xml");
		// for(int i=0 ; i<100000; i++) {
		// parser.parse("F:\\git\\github\\faas-platform\\faas-core\\src\\main\\resouces\\test.xml");
		// }
	}

}
