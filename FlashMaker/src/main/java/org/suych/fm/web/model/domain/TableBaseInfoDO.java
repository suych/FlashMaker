package org.suych.fm.web.model.domain;

/**
 * 表基础信息DO
 */
public class TableBaseInfoDO {

	/**
	 * 表名
	 */
	private String table_name;

	/**
	 * 类型Table or View
	 */
	private String table_type;

	/**
	 * 表注释
	 */
	private String comments;

	@Override
	public String toString() {
		return "TableBaseInfoDO [table_name=" + table_name + ", table_type=" + table_type + ", comments=" + comments
				+ "]";
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_type() {
		return table_type;
	}

	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
