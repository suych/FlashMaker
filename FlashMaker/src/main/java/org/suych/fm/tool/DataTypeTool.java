package org.suych.fm.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.suych.fm.constant.ConstantJdbcType;
import org.suych.fm.constant.ConstantOracleType;

public class DataTypeTool {

	public static String parseDataType(String dataType) {
		dataType = dataType.toUpperCase();
		Pattern pattern_type = Pattern.compile(".*?(?=\\()");
		Matcher matcher_type = pattern_type.matcher(dataType);
		return matcher_type.find() ? matcher_type.group(0) : dataType;
	}

	public static String parseDataType2JdbcType(String data_Type) {
		String dataType = DataTypeTool.parseDataType(data_Type);
		if (ConstantOracleType.VARCHAR2.equals(dataType) || ConstantOracleType.NVARCHAR2.equals(dataType)) {
			return ConstantJdbcType.VARCHAR;
		} else if (ConstantOracleType.CHAR.equals(dataType)) {
			return ConstantJdbcType.CHAR;
		}
		if (ConstantOracleType.NUMBER.equals(dataType)) {
			return ConstantJdbcType.DOUBLE;
		}
		if (ConstantOracleType.FLOAT.equals(dataType)) {
			return ConstantJdbcType.FLOAT;
		}
		if (ConstantOracleType.DATE.equals(dataType)) {
			return ConstantJdbcType.DATE;
		}
		if (ConstantOracleType.TIMESTAMP.equals(dataType)) {
			return ConstantJdbcType.TIMESTAMP;
		}
		if (ConstantOracleType.BLOB.equals(dataType)) {
			return ConstantJdbcType.BLOB;
		}
		if (ConstantOracleType.CLOB.equals(dataType)) {
			return ConstantJdbcType.CLOB;
		}
		return "UnknowType"; // 其他类型暂未UnknowType，方便后续扩展
	}
}
