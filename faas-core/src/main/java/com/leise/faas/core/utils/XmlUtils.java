package com.leise.faas.core.utils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;

public class XmlUtils {

	private XmlUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Document load(String filePath) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static Document load(InputStream in) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static Document XMLparse(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static Map<String, String> getNoteAttributeMap(Element element) {

		Map<String, String> attributeMap = new HashMap<>();
		DefaultAttribute defaultAttribute = null;
		List<?> list = element.attributes();
		for (int i = 0; i < list.size(); i++) {
			defaultAttribute = (DefaultAttribute) list.get(i);
			attributeMap.put(defaultAttribute.getName(), defaultAttribute.getText());
		}
		return attributeMap;
	}

	public static String getNoteAttribute(Element element, String attributeName) {
		return getNoteAttributeMap(element).get(attributeName);
	}

	public static Element getRootElement(Document document) {
		return document.getRootElement();
	}

}
