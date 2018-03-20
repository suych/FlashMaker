package org.suych.fm.web.model.domain;

/**
 * 字段信息DO
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
	 * 字段长度
	 */
	private String data_length;

	/**
	 * 字段精度
	 */
	private String data_precision;

	/**
	 * 字段注释
	 */
	private String comments;

	@Override
	public String toString() {
		return "FieldInfoDO [column_name=" + column_name + ", data_type=" + data_type + ", data_length=" + data_length
				+ ", data_precision=" + data_precision + ", comments=" + comments + "]";
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

	public String getData_length() {
		return data_length;
	}

	public void setData_length(String data_length) {
		this.data_length = data_length;
	}

	public String getData_precision() {
		return data_precision;
	}

	public void setData_precision(String data_precision) {
		this.data_precision = data_precision;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
