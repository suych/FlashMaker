package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.EXCLAMATION_POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.NULL;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
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
import org.suych.fm.util.generate.model.xml.XmlInsertNode;
import org.suych.fm.util.generate.model.xml.XmlTrimNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_SAVE_SELECTIVE)
public class NodeMethodSaveSelective implements INode {

	@Override
	public XmlCommonNode assemble() {
		TableInfoModel tableInfo = BaseInfo.getTableInfo();
		List<FieldInfoModel> fields = tableInfo.getField();

		XmlInsertNode result = new XmlInsertNode();
		StringBuilder text = new StringBuilder();
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.INSERT + SPACE + ConstantSqlSyntax.INTO
				+ RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getTableName());

		List<XmlTrimNode> trim = new ArrayList<XmlTrimNode>();
		XmlTrimNode trim1 = new XmlTrimNode();
		List<XmlIfNode> if1 = new ArrayList<XmlIfNode>();
		for (FieldInfoModel field : fields) {
			XmlIfNode ifNode = new XmlIfNode();
			ifNode.setTest(field.getColumnName() + SPACE + EXCLAMATION_POINT + EQUAL_SIGN + SPACE + NULL);
			ifNode.setText(RETURN_NEWLINE + TAB + TAB + TAB + TAB + field.getColumnName() + COMMA + RETURN_NEWLINE + TAB
					+ TAB + TAB);
			if1.add(ifNode);
		}
		trim1.setPrefix(LEFT_BRACKET);
		trim1.setSuffix(RIGHT_BRACKET);
		trim1.setSuffixOverrides(COMMA);
		trim1.setIfNode(if1);

		XmlTrimNode trim2 = new XmlTrimNode();
		List<XmlIfNode> if2 = new ArrayList<XmlIfNode>();
		for (FieldInfoModel field : fields) {
			XmlIfNode ifNode = new XmlIfNode();
			String fieldName = field.getColumnName();
			String jdbcType = DataTypeTool.parseDataType2JdbcType(field.getDataType());
			ifNode.setTest(fieldName + SPACE + EXCLAMATION_POINT + EQUAL_SIGN + SPACE + NULL);
			ifNode.setText(RETURN_NEWLINE + TAB + TAB + TAB + TAB + NUMBER_SIGN + LEFT_BRACE + fieldName + COMMA + SPACE
					+ ConstantSqlSyntax.JDBCTYPE + EQUAL_SIGN + jdbcType + RIGHT_BRACE + COMMA + RETURN_NEWLINE + TAB
					+ TAB + TAB);
			if2.add(ifNode);
		}
		trim2.setPrefix(ConstantSqlSyntax.VALUES + SPACE + LEFT_BRACKET);
		trim2.setSuffix(RIGHT_BRACKET);
		trim2.setSuffixOverrides(COMMA);
		trim2.setIfNode(if2);

		trim.add(trim1);
		trim.add(trim2);

		result.setId(ConstantMethodName.SAVE_SELECTIVE);
		result.setParameterType(BaseInfo.getDomainClassName());
		result.setText(text.toString());
		result.setTrim(trim);
		return result;
	}

}
