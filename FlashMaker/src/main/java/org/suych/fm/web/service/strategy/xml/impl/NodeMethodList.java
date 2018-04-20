package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlIncludeNode;
import org.suych.fm.util.generate.model.xml.XmlSelectNode;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_LIST)
public class NodeMethodList implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlSelectNode result = new XmlSelectNode();
		StringBuilder textOne = new StringBuilder();
		textOne.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.SELECT);
		XmlIncludeNode include = new XmlIncludeNode();
		include.setRefid(ConstantParameterValue.BASE_COLUMN_LIST);
		StringBuilder textTwo = new StringBuilder();
		textTwo.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.FROM + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + TAB + BaseInfo.getTableInfo().getTableName() + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + ConstantSqlSyntax.WHERE + RETURN_NEWLINE);
		textTwo.append(TAB + TAB + TAB + "1=1");
		result.setId(ConstantMethodName.LIST);
		result.setResultMap(BaseInfo.getDomainClassFieldName());
		result.setTextOne(textOne.toString());
		result.setInclude(include);
		result.setTextTwo(textTwo.toString());
		return result;
	}

}
