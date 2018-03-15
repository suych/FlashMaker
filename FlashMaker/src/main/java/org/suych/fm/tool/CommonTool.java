package org.suych.fm.tool;

public class CommonTool {

	/**
	 * 组装类或者接口名
	 * 将数据表名首字母大写后，增加后缀
	 * 
	 * @param tableName 数据表名
	 * @param suffix 后缀
	 * @return
	 */
	public static String assembleClassOrInterfaceName(String tableName, String suffix) {
		String result = "";
		if (tableName.length() > 1) {
			String classNameFirstChar = tableName.substring(0, 1).toUpperCase(); // 类名首字母大写
			String classNameOtherChar = tableName.substring(1);
			result = classNameFirstChar + classNameOtherChar;
		} else {
			result = tableName.toUpperCase();
		}
		result += suffix;
		return result;
	}
}
