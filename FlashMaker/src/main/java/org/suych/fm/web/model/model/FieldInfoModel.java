package org.suych.fm.web.model.model;

/**
 * 字段信息Model
 */
public class FieldInfoModel {

	/**
	 * 字段名
	 */
	private String columnName;

	/**
	 * 字段类型
	 */
	private String dataType;

	/**
	 * 字段长度
	 */
	private String dataLength;

	/**
	 * 字段精度
	 */
	private String dataPrecision;

	/**
	 * 字段注释
	 */
	private String comments;

	@Override
	public String toString() {
		return "FieldInfoModel [columnName=" + columnName + ", dataType=" + dataType + ", dataLength=" + dataLength
				+ ", dataPrecision=" + dataPrecision + ", comments=" + comments + "]";
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(String dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
