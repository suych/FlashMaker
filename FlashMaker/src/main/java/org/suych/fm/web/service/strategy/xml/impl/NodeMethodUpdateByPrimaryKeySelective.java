package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.EXCLAMATION_POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.NULL;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.DataTypeTool;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlIfNode;
import org.suych.fm.util.generate.model.xml.XmlTrimNode;
import org.suych.fm.util.generate.model.xml.XmlUpdateNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_UPDATE_BY_PRIMARYKEY_SELECTIVE)
public class NodeMethodUpdateByPrimaryKeySelective implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlUpdateNode result = new XmlUpdateNode();
		TableInfoModel tableInfo = BaseInfo.getTableInfo();
		String primaryKey = tableInfo.getPrimaryKey();
		List<FieldInfoModel> fields = tableInfo.getField();
		String primaryKeyDataType = DataTypeTool
				.parseDataType2JdbcType(BaseInfo.getTableInfo().getPrimaryKeyDataType());

		StringBuilder textOne = new StringBuilder();
		textOne.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.UPDATE + RETURN_NEWLINE);
		textOne.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getTableName() + RETURN_NEWLINE);
		textOne.append(TAB + TAB + ConstantSqlSyntax.SET);

		List<XmlTrimNode> trim = new ArrayList<XmlTrimNode>();
		XmlTrimNode trim1 = new XmlTrimNode();
		List<XmlIfNode> if1 = new ArrayList<XmlIfNode>();
		for (FieldInfoModel field : fields) {
			if (field.getColumnName().equals(primaryKey)) {
				continue;
			}
			XmlIfNode ifNode = new XmlIfNode();
			String fieldName = field.getColumnName();
			String jdbcType = DataTypeTool.parseDataType2JdbcType(field.getDataType());
			StringBuilder text = new StringBuilder();
			text.append(RETURN_NEWLINE + TAB + TAB + TAB + TAB + fieldName + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN
					+ LEFT_BRACE + fieldName + COMMA + SPACE + ConstantSqlSyntax.JDBCTYPE + EQUAL_SIGN + jdbcType
					+ RIGHT_BRACE + COMMA + RETURN_NEWLINE + TAB + TAB + TAB);
			ifNode.setTest(field.getColumnName() + SPACE + EXCLAMATION_POINT + EQUAL_SIGN + SPACE + NULL);
			ifNode.setText(text.toString());
			if1.add(ifNode);
		}
		trim1.setSuffixOverrides(COMMA);
		trim1.setIfNode(if1);
		trim.add(trim1);

		StringBuilder textTwo = new StringBuilder();
		textTwo.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + TAB + primaryKey + SPACE + EQUAL_SIGN + SPACE + NUMBER_SIGN + LEFT_BRACE + primaryKey
				+ COMMA + SPACE + ConstantSqlSyntax.JDBCTYPE + EQUAL_SIGN + primaryKeyDataType + RIGHT_BRACE);

		result.setId(ConstantMethodName.UPDATE_BY_PRIMARYKEY_SELECTIVE);
		result.setParameterType(BaseInfo.getDomainClassName());
		result.setTextOne(textOne.toString());
		result.setTrim(trim);
		result.setTextTwo(textTwo.toString());
		return result;
	}

}
