package org.suych.fm.web.model.model;

/**
 * 字段信息Model
 */
public class FieldInfoModel {

	/**
	 * 字段名，数据库表的原字段名
	 */
	private String columnName;

	/**
	 * 属性名，DO类的属性名，字段名经过加工后成为属性名
	 * 去掉字段名的下划线，首字母小写，驼峰写法
	 */
	private String propertyName;

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
		return "FieldInfoModel [columnName=" + columnName + ", propertyName=" + propertyName + ", dataType=" + dataType
				+ ", dataLength=" + dataLength + ", dataPrecision=" + dataPrecision + ", comments=" + comments + "]";
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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
