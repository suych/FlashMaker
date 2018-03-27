package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlSqlNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_BASE_COLUMN)
public class NodeMethodBaseColumn implements INode {

	@Override
	public XmlCommonNode assemble() {
		XmlSqlNode result = new XmlSqlNode();
		StringBuilder text = new StringBuilder();
		List<FieldInfoModel> field = BaseInfo.getTableInfo().getField();
		text.append(RETURN_NEWLINE + TAB + TAB);
		for (int i = 0, j = field.size(); i < j; i++) {
			String fieldName = field.get(i).getColumnName();
			text.append(fieldName);
			if (i != j - 1) {
				text.append(COMMA + SPACE);
			}
		}
		text.append(RETURN_NEWLINE + TAB);
		result.setId(ConstantParameterValue.BASE_COLUMN_LIST);
		result.setText(text.toString());
		return result;
	}

}
