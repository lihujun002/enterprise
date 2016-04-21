package org.tiger.framework.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class XmlUtil {

	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	private static final String DEFAULT_ROOT_ELEMENT = "xml";// 默认的根标签

	/**
	 * xml字符串转为Map。
	 * 
	 * @param xml
	 * @return Map<String, Object>
	 * @throws Exception 
	 */
	public static Map<String, Object> toMap(String xml) throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		Map<String, Object> map = (Map<String, Object>) xml2map(doc.getRootElement());
		return map;
	}
	
	private static Object xml2map(Element element) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText());
			if (!element.isRootElement()) {
				return element.getText();
			}
		}else if (elements.size() == 1) {
			map.put(elements.get(0).getName(), xml2map(elements.get(0)));
		}else if (elements.size() > 1) {
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = tempMap.get(string).getNamespace();
				List<Element> elements2 = element.elements(new QName(string,namespace));
				if(elements2.size() > 1) {
					List<Object> list = new ArrayList<Object>();
					for (Element ele : elements2) {
						list.add(xml2map(ele));
					}
					map.put(string, list);
				}else{
					map.put(string, xml2map(elements2.get(0)));
				}
			}
		}
		return map;
	}
	

	/**
	 * Map转为xml字符串。
	 * 
	 * @param xml
	 * @param xml
	 * @return String
	 */
	public static String toXml(Map<String, Object> map, String rootElement) {

		rootElement = StringUtils.isEmpty(rootElement) ? DEFAULT_ROOT_ELEMENT : rootElement;
		StringBuffer sb = new StringBuffer("<" + rootElement + ">");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append("<").append(entry.getKey()).append(">").append("<![CDATA[").append(entry.getValue()).append("]]>")
					.append("</").append(entry.getKey()).append(">");
		}
		sb.append("</").append(rootElement).append(">");
		System.out.println(sb.toString());
		return sb.toString();
	}

}
