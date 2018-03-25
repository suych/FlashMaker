package org.suych.fm.web.service.strategy.xml;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;

/**
 * 节点策略工厂
 */
@Component
public class NodeFactory {

	@Autowired
	Map<String, INode> methodStrategy = new HashMap<String, INode>();

	/**
	 * 组装方法
	 * 
	 * @param methodName
	 * @return
	 */
	public XmlCommonNode assemble(String methodName) {
		INode node = methodStrategy.get(methodName);
		return node.assemble();
	}

}
