package org.suych.fm.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
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
		// 1.基础列
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_BASE_COLUMN));
		// 2.list方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_LIST));
		// 3.getById方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_GET_BY_PRIMARYKEY));
		// 4.save方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_SAVE));
		// 5.saveSelective方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_SAVE_SELECTIVE));
		// 6.updateById方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_UPDATE_BY_PRIMARYKEY_SELECTIVE));
		// 7.removeByIds方法
		result.add(factory.assemble(ConstantStrategyComponentName.NODE_REMOVE_BY_PRIMARYKEYS));
		return result;
	}

	private XmlStructure assembleXml(List<XmlCommonNode> node) {
		XmlStructure result = new XmlStructure();
		result.setName(BaseInfo.getMapperXmlName());
		result.setDocType("-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		result.setRootNodeName("mapper");
		result.setNamespace(BaseInfo.getMapperInterfaceImportPath());
		result.setNode(node);
		return result;
	}

}
