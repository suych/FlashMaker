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
			if (tableName.indexOf("_") != -1) {
				// 是否有下划线
				String[] tableNameArray = tableName.split("_");
				for (String tableNamePiece : tableNameArray) {
					// 驼峰写法命名
					result += firstLetterToUpperCase(tableNamePiece);
				}
			} else {
				result = firstLetterToUpperCase(tableName);
			}

		} else {
			result = tableName.toUpperCase();
		}
		result += suffix.getType();
		return result;
	}

	/**
	 * 将字符串首字母大写
	 * 
	 * @param param
	 * @return
	 */
	public static String firstLetterToUpperCase(String param) {
		String result = "";
		if (param.length() > 1) {
			String classNameFirstChar = param.substring(0, 1).toUpperCase(); // 类名首字母小写
			String classNameOtherChar = param.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = param.toUpperCase();
		}
		return result;
	}

	/**
	 * 将字符串首字母小写
	 * 
	 * @param param
	 * @return
	 */
	public static String firstLetterToLowerCase(String param) {
		String result = "";
		if (param.length() > 1) {
			String classNameFirstChar = param.substring(0, 1).toLowerCase(); // 类名首字母小写
			String classNameOtherChar = param.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = param.toUpperCase();
		}
		return result;
	}

}
