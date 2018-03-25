package org.suych.fm.web.service.strategy.xml.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.NUMBER_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantSqlSyntax;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.DataTypeTool;
import org.suych.fm.util.generate.model.xml.XmlCommonNode;
import org.suych.fm.util.generate.model.xml.XmlInsertNode;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.strategy.xml.INode;

@Component(ConstantStrategyComponentName.NODE_SAVE)
public class NodeMethodSave implements INode {

	@Override
	public XmlCommonNode assemble() {
		TableInfoModel tableInfo = BaseInfo.getTableInfo();
		List<FieldInfoModel> fields = tableInfo.getField();

		XmlInsertNode result = new XmlInsertNode();
		StringBuilder text = new StringBuilder();
		text.append(RETURN_NEWLINE + TAB + TAB + ConstantSqlSyntax.INSERT + SPACE + ConstantSqlSyntax.INTO
				+ RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + tableInfo.getTableName() + SPACE + LEFT_BRACKET);
		for (int i = 0, j = fields.size(); i < j; i++) {
			String fieldName = fields.get(i).getColumnName();
			text.append(fieldName);
			if (i != j - 1) {
				text.append(COMMA + SPACE);
			}
		}
		text.append(RIGHT_BRACKET + RETURN_NEWLINE);
		text.append(TAB + TAB + ConstantSqlSyntax.VALUES + RETURN_NEWLINE);
		text.append(TAB + TAB + TAB + LEFT_BRACKET);
		for (int i = 0, j = fields.size(); i < j; i++) {
			FieldInfoModel field = fields.get(i);
			String fieldName = field.getColumnName();
			String jdbcType = DataTypeTool.parseDataType2JdbcType(field.getDataType());
			text.append(NUMBER_SIGN + LEFT_BRACE + fieldName + COMMA + SPACE + ConstantSqlSyntax.JDBCTYPE + EQUAL_SIGN
					+ jdbcType + RIGHT_BRACE);
			if (i != j - 1) {
				text.append(COMMA + RETURN_NEWLINE + TAB + TAB + TAB + SPACE);
			}
		}
		text.append(RIGHT_BRACKET + RETURN_NEWLINE + TAB);
		result.setId(ConstantMethodName.SAVE);
		result.setParameterType(BaseInfo.getDomainClassName());
		result.setText(text.toString());
		return result;
	}

}
