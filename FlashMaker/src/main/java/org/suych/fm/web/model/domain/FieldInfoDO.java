package org.suych.fm.web.model.domain;

/**
 * 字段信息DO
 * 
 * @author suych
 *
 */
public class FieldInfoDO {

	/**
	 * 字段名
	 */
	private String column_name;

	/**
	 * 字段类型
	 */
	private String data_type;

	/**
	 * 字段注释
	 */
	private String comments;

	@Override
	public String toString() {
		return "FieldInfoDO [column_name=" + column_name + ", data_type=" + data_type + ", comments=" + comments + "]";
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
