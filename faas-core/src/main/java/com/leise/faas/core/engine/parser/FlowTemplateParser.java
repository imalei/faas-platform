package com.leise.faas.core.engine.parser;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leise.faas.core.utils.XmlUtils;


public class FlowTemplateParser {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String parse(String filePath) {

		Document doc = XmlUtils.load(filePath);
		Element rootElement = doc.getRootElement(); 
		
		System.out.println("根节点：" + rootElement.getName()); // 拿到根节点的名称
		Iterator iter = rootElement.elementIterator("flow"); // 获取根节点下的子节点flow

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
