package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlDeleteNode;
import org.suych.fm.util.generate.model.xml.XmlForeachNode;
import org.suych.fm.web.model.model.PrimaryKeyInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_REMOVE_BY_PRIMARYKEYS)
public class NodeMethodRemoveByPrimaryKeys implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlDeleteNode result = new XmlDeleteNode();
		PrimaryKeyInfoModel primaryKey = BaseInfo.getTableInfo().getPrimaryKey();
		StringBuilder textOne = new StringBuilder();
		textOne.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.DELETE + SPACE + ConstantSqlSyntax.FROM
				+ RETURN_NEWLINE);
		textOne.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getTableName() + RETURN_NEWLINE);
		textOne.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		textOne.append(TAB + TAB + TAB + primaryKey.getColumnName() + RETURN_NEWLINE);
		textOne.append(TAB + TAB + ConstantSqlSyntax.IN + SPACE + LEFT_BRACKET + RETURN_NEWLINE);

		XmlForeachNode foreach = new XmlForeachNode();
		foreach.setCollection(ConstantMethodName.LIST);
		foreach.setItem(ConstantParameterName.PRIMARY_KEY);
		foreach.setSeparator(COMMA);
		String foreachText = RETURN_NEWLINE + TAB + TAB + TAB + NUMBER_SIGN + LEFT_BRACE
				+ ConstantParameterName.PRIMARY_KEY + RIGHT_BRACE + RETURN_NEWLINE + TAB + TAB;
		foreach.setText(foreachText);
		String textTwo = RETURN_NEWLINE + TAB + TAB + RIGHT_BRACKET;
		result.setId(ConstantMethodName.REMOVE_BY_PRIMARYKEYS);
		result.setTextOne(textOne.toString());
		result.setTextTwo(textTwo);
		result.setForeach(foreach);
		return result;
	}

}
