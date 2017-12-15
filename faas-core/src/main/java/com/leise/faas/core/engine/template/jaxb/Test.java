package com.leise.faas.core.engine.template.jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Test {
	
	public static void main(String[] args) {
		
		Flow flow = new Flow();
		List<Operation> funcs = new ArrayList<>();
		Operation func = new Operation();
		func.setId("111");
		func.setName("222");
		func.setRefClass("333");
		List<Data> inputParams = new ArrayList<>();
		List<Data> outputParams = new ArrayList<>();
		List<Data> initParams = new ArrayList<>();
		
		List<Target> targets = new ArrayList<>();
		Target target = new Target("222", "#state == 'CA'");
		targets.add(target);
		Data param1 = new Data();
		Data param2 = new Data();
		Data initParam = new Data();
		initParam.setId("statement");
		initParam.setValue("CityMapper.findByState");
		initParams.add(initParam);
		param1.setId("aaa");
		param2.setId("bbb");
		inputParams.add(param1);
		inputParams.add(param2);
		outputParams.add(param2);
		func.setInParams(inputParams);
		func.setOutParams(outputParams);
		func.setInitParams(initParams);
		func.setTargets(targets);
		funcs.add(func);
		Operation func1 = new Operation();
		func1.setId("222");
		func1.setName("222");
		func1.setRefClass("333");
		func1.setInParams(inputParams);
		funcs.add(func1);
		flow.setOperations(funcs);
		flow.setId("111");
		flow.setName("test");
		System.out.println(object2Xml(flow));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Object(String xmlStr, Class<T> c) {
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr));

			return t;

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param object
	 *            对象
	 * @return 返回xmlStr
	 */
	public static String object2Xml(Object object) {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshal = context.createMarshaller();

			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
			marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8
			marshal.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息
			marshal.marshal(object, writer);

			return new String(writer.getBuffer());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
