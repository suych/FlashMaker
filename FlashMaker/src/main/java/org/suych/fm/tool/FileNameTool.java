package org.suych.fm.tool;

import org.suych.fm.constant.ConstantSuffix;

/**
 * 文件名工具类
 */
public class FileNameTool {

	/**
	 * 组装类或者接口名
	 * 将数据表名首字母大写后，增加后缀
	 * 
	 * @param tableName 数据表名
	 * @param suffix 后缀
	 * @return
	 */
	public static String assembleClassOrInterfaceName(String tableName, ConstantSuffix suffix) {
		String result = "";
		if (tableName.length() > 1) {
			// 1.将表名全转为小写
			tableName = tableName.toLowerCase();
			// 2.将表名首字母大写
			String classNameFirstChar = tableName.substring(0, 1).toUpperCase();
			// 3.拼接后续表名
			String classNameOtherChar = tableName.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = tableName.toUpperCase();
		}
		result += suffix.getType();
		return result;
	}

}
