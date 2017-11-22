package com.leise.faas.core.engine.parser;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class FlowTemplateParser {

	public String parse(String xml) {

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		} // 将字符串转为XML
		Element rootElement = doc.getRootElement(); // 获取根节点
		System.out.println("根节点：" + rootElement.getName()); // 拿到根节点的名称
		Iterator iter = rootElement.elementIterator("head"); // 获取根节点下的子节点head

		// 遍历head节点
		while (iter.hasNext()) {
			Element recordEle = (Element) iter.next();
			String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
			System.out.println("title:" + title);
			Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script
			// 遍历Header节点下的Response节点
			while (iters.hasNext()) {
				Element itemElement = (Element) iters.next();
				String username = itemElement.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
				String password = itemElement.elementTextTrim("password");
				System.out.println("username:" + username);
				System.out.println("password:" + password);
			}
		}

		return null;
	}


}
