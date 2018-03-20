package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.util.generate.GenerateXmlUtil;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlDeleteNode;
import org.suych.fm.util.generate.model.xml.XmlForeachNode;
import org.suych.fm.util.generate.model.xml.XmlInsertNode;
import org.suych.fm.util.generate.model.xml.XmlSelectNode;
import org.suych.fm.util.generate.model.xml.XmlStructure;
import org.suych.fm.util.generate.model.xml.XmlUpdateNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.IMapperXmlService;

@Service
public class MapperXmlServiceImpl implements IMapperXmlService {

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
		StringBuilder text = new StringBuilder();
		String domainClassName = BaseInfo.getDomainClassName();
		TableInfoModel tableInfo = BaseInfo.getTableInfo();
		String tableName = tableInfo.getTableName();
		String primaryKey = tableInfo.getPrimaryKey();
		List<FieldInfoModel> field = tableInfo.getField();
		// 1.list方法
		XmlSelectNode list = assembleMethodList(text, domainClassName, tableName, field);
		// 2.getById方法
		XmlSelectNode getById = assembleMethodGetById(domainClassName, tableName, primaryKey, field);
		// 3.save方法
		XmlInsertNode save = assembleMethodSave(domainClassName, tableName, field);
		// 4.updateById方法
		XmlUpdateNode updateById = assembleMethodUpdateById(domainClassName, tableName, primaryKey, field);
		// 5.removeByIds方法
		XmlDeleteNode removeByIds = assembleMethodRemoveByIds(tableName, primaryKey);
		result.add(list);
		result.add(getById);
		result.add(save);
		result.add(updateById);
		result.add(removeByIds);
		return result;
	}

	private XmlStructure assembleXml(List<XmlCommonNode> node) {
		XmlStructure result = new XmlStructure();
		result.setName(BaseInfo.getMapperXmlName());
		result.setDocType("-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		result.setRootNodeName("mapper");
		result.setNamespace(BaseInfo.getLocalPackage() + POINT + BaseInfo.getMapperInterfaceName());
		result.setNode(node);
		return result;
	}

	private XmlSelectNode assembleMethodList(StringBuilder text, String domainClassName, String tableName,
			List<FieldInfoModel> field) {
		XmlSelectNode list = new XmlSelectNode();
		list.setId(ConstantMethodName.LIST);
		list.setResultType(domainClassName);
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.SELECT + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getColumnName();
			if (i != j - 1) {
				text.append(fieldName + COMMA + SPACE);
			} else {
				text.append(fieldName);
			}
		}
		text.append(RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.FROM + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + tableName + RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + "1=1" + RETURN_NEWLINE + TAB);
		list.setText(text.toString());
		return list;
	}

	private XmlSelectNode assembleMethodGetById(String domainClassName, String tableName, String primaryKey,
			List<FieldInfoModel> field) {
		StringBuilder text;
		XmlSelectNode getById = new XmlSelectNode();
		getById.setId(ConstantMethodName.LIST);
		getById.setResultType(domainClassName);
		text = new StringBuilder();
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.SELECT + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getColumnName();
			if (i != j - 1) {
				text.append(fieldName + COMMA + SPACE);
			} else {
				text.append(fieldName);
			}
		}
		text.append(RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.FROM + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + tableName + RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + primaryKey + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN + LEFT_BRACE
				+ ConstantParameterName.ID + RIGHT_BRACE + RETURN_NEWLINE + TAB);
		getById.setText(text.toString());
		return getById;
	}

	private XmlInsertNode assembleMethodSave(String domainClassName, String tableName, List<FieldInfoModel> field) {
		StringBuilder text;
		XmlInsertNode save = new XmlInsertNode();
		save.setId(ConstantMethodName.SAVE);
		save.setParameterType(domainClassName);
		text = new StringBuilder();
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.INSERT + SPACE + ConstantSqlSyntax.INTO
				+ RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + tableName + SPACE + LEFT_BRACKET);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getColumnName();
			if (i != j - 1) {
				text.append(fieldName + COMMA + SPACE);
			} else {
				text.append(fieldName);
			}
		}
		text.append(RIGHT_BRACKET + RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.VALUES + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + LEFT_BRACKET);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getColumnName();
			if (i != j - 1) {
				text.append(NUMBER_SIGN + LEFT_BRACE + fieldName + RIGHT_BRACE + COMMA + SPACE);
			} else {
				text.append(NUMBER_SIGN + LEFT_BRACE + fieldName + RIGHT_BRACE);
			}
		}
		text.append(RIGHT_BRACKET + RETURN_NEWLINE + TAB);
		save.setText(text.toString());
		return save;
	}

	private XmlUpdateNode assembleMethodUpdateById(String domainClassName, String tableName, String primaryKey,
			List<FieldInfoModel> field) {
		StringBuilder text;
		XmlUpdateNode updateById = new XmlUpdateNode();
		updateById.setId(ConstantMethodName.UPDATE_BY_ID);
		updateById.setParameterType(domainClassName);
		text = new StringBuilder();
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.UPDATE + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + tableName + RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.SET + RETURN_NEWLINE);
		for (int i = 0, j = field.size(); i < j; i++) {
			if (field.get(i).getColumnName().equals(primaryKey)) {
				continue;
			}
			String fieldName = field.get(i).getColumnName();
			if (i != j - 1) {
				text.append(TAB + TAB + TAB + fieldName + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN + LEFT_BRACE
						+ fieldName + RIGHT_BRACE + COMMA + RETURN_NEWLINE);
			} else {
				text.append(TAB + TAB + TAB + fieldName + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN + LEFT_BRACE
						+ fieldName + RIGHT_BRACE + RETURN_NEWLINE);
			}
		}
		text.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + primaryKey + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN + LEFT_BRACE + primaryKey
				+ RIGHT_BRACE);
		text.append(RETURN_NEWLINE + TAB);
		updateById.setText(text.toString());
		return updateById;
	}

	private XmlDeleteNode assembleMethodRemoveByIds(String tableName, String primaryKey) {
		XmlDeleteNode removeByIds = new XmlDeleteNode();
		removeByIds.setId(ConstantMethodName.REMOVE_BY_IDS);
		StringBuilder textOne = new StringBuilder();
		textOne.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.DELETE + SPACE + ConstantSqlSyntax.FROM
				+ RETURN_NEWLINE);
		textOne.append(TAB + TAB + TAB + tableName + RETURN_NEWLINE);
		textOne.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		textOne.append(TAB + TAB + TAB + primaryKey + RETURN_NEWLINE);
		textOne.append(TAB + TAB + ConstantSqlSyntax.IN + SPACE + LEFT_BRACKET + RETURN_NEWLINE);

		XmlForeachNode foreach = new XmlForeachNode();
		foreach.setCollection(ConstantMethodName.LIST);
		foreach.setItem(ConstantParameterName.ID);
		foreach.setSeparator(COMMA);
		String foreachText = RETURN_NEWLINE + TAB + TAB + TAB + NUMBER_SIGN + LEFT_BRACE + ConstantParameterName.ID
				+ RIGHT_BRACE + RETURN_NEWLINE + TAB + TAB;
		foreach.setText(foreachText);
		String textTwo = RETURN_NEWLINE + TAB + TAB + RIGHT_BRACKET;
		removeByIds.setTextOne(textOne.toString());
		removeByIds.setTextTwo(textTwo);
		removeByIds.setForeach(foreach);
		return removeByIds;
	}

}
