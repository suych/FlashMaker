package org.suych.fm.web.service.strategy.xml.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.DataTypeTool;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlResultMapIdNode;
import org.suych.fm.util.generate.model.xml.XmlResultMapNode;
import org.suych.fm.util.generate.model.xml.XmlResultMapResultNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.PrimaryKeyInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_RESULT_MAP)
public class NodeResultMap implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlResultMapNode result = new XmlResultMapNode();
		XmlResultMapIdNode idNode = new XmlResultMapIdNode();
		PrimaryKeyInfoModel primaryKey = BaseInfo.getTableInfo().getPrimaryKey();
		String primaryKeyColumn = primaryKey.getColumnName();
		String primaryKeyProperty = primaryKey.getPropertyName();
		String primaryKeyJdbcType = DataTypeTool.parseDataType2JdbcType(primaryKey.getDataType());
		idNode.setColumn(primaryKeyColumn);
		idNode.setProperty(primaryKeyProperty);
		idNode.setJdbcType(primaryKeyJdbcType);

		List<XmlResultMapResultNode> resultNodes = new ArrayList<XmlResultMapResultNode>();
		for (FieldInfoModel field : BaseInfo.getTableInfo().getField()) {
			String columnName = field.getColumnName();
			if (primaryKeyColumn.equals(columnName)) {
				continue;
			}
			String propertyName = field.getPropertyName();
			String jdbcType = DataTypeTool.parseDataType2JdbcType(field.getDataType());
			XmlResultMapResultNode resultNode = new XmlResultMapResultNode();
			resultNode.setColumn(columnName);
			resultNode.setProperty(propertyName);
			resultNode.setJdbcType(jdbcType);
			resultNodes.add(resultNode);
		}

		result.setId(BaseInfo.getDomainClassFieldName());
		result.setType(BaseInfo.getDomainClassName());
		result.setIdNode(idNode);
		result.setResultNode(resultNodes);
		return result;
	}

}
