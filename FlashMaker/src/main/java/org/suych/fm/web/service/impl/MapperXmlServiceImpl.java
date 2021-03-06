package org.suych.fm.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMapperXml;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.GenerateXmlUtil;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlStructure;
import org.suych.fm.web.service.IMapperXmlService;
import org.suych.fm.web.service.strategy.xml.NodeFactory;

@Service
public class MapperXmlServiceImpl implements IMapperXmlService {

	@Autowired
	NodeFactory factory;

	@Override
	public void generate() {
		// 1.组装节点集合
		List<XmlCommonNode> node = assembleNode();
		// 2.组装XML结构
		XmlStructure mapperXML = assembleXml(node);
		// 3.按规范输出至文件
		GenerateXmlUtil.generate(mapperXML);
	}

	private List<XmlCommonNode> assembleNode() {
		List<XmlCommonNode> result = new ArrayList<XmlCommonNode>();
		// 1.resultMap定义
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_RESULT_MAP));
		// 2.基础列
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_BASE_COLUMN));
		// 3.list方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_LIST));
		// 4.getById方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_GET_BY_PRIMARYKEY));
		// 5.save方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_SAVE));
		// 6.saveSelective方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_SAVE_SELECTIVE));
		// 7.updateById方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_UPDATE_BY_PRIMARYKEY_SELECTIVE));
		// 8.removeByIds方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_REMOVE_BY_PRIMARYKEYS));
		return result;
	}

	private XmlStructure assembleXml(List<XmlCommonNode> node) {
		XmlStructure result = new XmlStructure();
		result.setName(BaseInfo.getMapperXmlName());
		result.setDocType(ConstantMapperXml.DOCTYPE);
		result.setRootNodeName(ConstantMapperXml.ROOT_NODE_NAME);
		result.setNamespace(BaseInfo.getMapperInterfaceImportPath());
		result.setNode(node);
		return result;
	}

}
