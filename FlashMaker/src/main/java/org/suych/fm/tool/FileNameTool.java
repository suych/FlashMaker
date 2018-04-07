package org.suych.fm.tool;

import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.util.StringUtil;

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
					result += StringUtil.firstLetterToUpperCase(tableNamePiece);
				}
			} else {
				result = StringUtil.firstLetterToUpperCase(tableName);
			}

		} else {
			result = tableName.toUpperCase();
		}
		result += suffix.getType();
		return result;
	}

}
