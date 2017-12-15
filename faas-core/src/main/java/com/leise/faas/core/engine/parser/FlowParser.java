package com.leise.faas.core.engine.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.leise.faas.core.engine.template.jaxb.Flow;
import com.leise.faas.core.utils.JaxbUtils;

@Component
public class FlowParser {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value(value = "${faas.flow.locations}")
	private String filePath;

	public Flow parse(String flowId) throws FileNotFoundException {

		long begin = System.currentTimeMillis();
		String fullPath = new StringBuilder().append(filePath).append(flowId).append(".").append("xml").toString();

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = {};
		try {
			resources = resolver.getResources(fullPath);
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

		String flowContent = null;
		try {
			flowContent = IOUtils.toString(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Flow flow = JaxbUtils.xml2Object(flowContent, Flow.class);

		logger.info("解析xml:{}", JaxbUtils.object2Xml(flow));
		long end = System.currentTimeMillis();

		logger.info("解析文件耗时:{}", end - begin);
		return flow;
	}

	public static void main(String[] args) throws FileNotFoundException {
		FlowParser parser = new FlowParser();
		parser.parse("F:\\git\\github\\faas-platform\\faas-core\\src\\main\\resouces\\flow\\test.xml");
		// for (int i = 0; i < 100000; i++) {
		// parser.parse("F:\\git\\github\\faas-platform\\faas-core\\src\\main\\resouces\\test.xml");
		// }
	}

}
