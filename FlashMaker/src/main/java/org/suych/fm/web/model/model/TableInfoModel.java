package org.suych.fm.web.model.model;

import java.util.List;

/**
 * 表信息Model
 */
public class TableInfoModel {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 类型Table or View
	 */
	private String tableType;

	/**
	 * 表注释
	 */
	private String comments;

	/**
	 * 主键
	 */
	private String primaryKey;

	/**
	 * 字段信息
	 */
	private List<FieldInfoModel> field;

	@Override
	public String toString() {
		return "TableInfoModel [tableName=" + tableName + ", tableType=" + tableType + ", comments=" + comments
				+ ", primaryKey=" + primaryKey + ", field=" + field + "]";
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<FieldInfoModel> getField() {
		return field;
	}

	public void setField(List<FieldInfoModel> field) {
		this.field = field;
	}

}
