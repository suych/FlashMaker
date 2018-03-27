package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.DataTypeTool;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlIncludeNode;
import org.suych.fm.util.generate.model.xml.XmlSelectNode;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_GET_BY_PRIMARYKEY)
public class NodeMethodGetByPrimaryKey implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlSelectNode result = new XmlSelectNode();
		String primaryKeyDataType = DataTypeTool
				.parseDataType2JdbcType(BaseInfo.getTableInfo().getPrimaryKeyDataType());

		StringBuilder textOne = new StringBuilder();
		textOne.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.SELECT);

		XmlIncludeNode include = new XmlIncludeNode();
		include.setRefid(ConstantParameterValue.BASE_COLUMN_LIST);

		StringBuilder textTwo = new StringBuilder();
		textTwo.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.FROM + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getTableName() + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getPrimaryKey() + SPACE + EQUAL_SIGN + SPACE
				+ NUMBER_SIGN + LEFT_BRACE + ConstantParameterName.PRIMARY_KEY + COMMA + SPACE
				+ ConstantSqlSyntax.JDBCTYPE + EQUAL_SIGN + primaryKeyDataType + RIGHT_BRACE);

		result.setId(ConstantMethodName.GET_BY_PRIMARYKEY);
		result.setResultType(BaseInfo.getDomainClassName());
		result.setTextOne(textOne.toString());
		result.setInclude(include);
		result.setTextTwo(textTwo.toString());
		return result;
	}

}
