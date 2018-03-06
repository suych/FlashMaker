package org.suych.fm.web.model.bo;

public class FieldInfoBO {

	/**
	 * 字段名
	 */
	private String columnName;

	/**
	 * java类型
	 */
	private String javaType;

	/**
	 * 字段注释
	 */
	private String comments;

	@Override
	public String toString() {
		return "FieldInfoBO [columnName=" + columnName + ", javaType=" + javaType + ", comments=" + comments + "]";
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
