package org.suych.fm.util.generate.model.xml;

/**
 * resultMap节点下的result节点
 */
public class XmlResultMapResultNode {

	/**
	 * 字段名
	 */
	private String column;

	/**
	 * 属性名
	 */
	private String property;

	/**
	 * jdbc类型
	 */
	private String jdbcType;

	@Override
	public String toString() {
		return "XmlResultMapResultNode [column=" + column + ", property=" + property + ", jdbcType=" + jdbcType + "]";
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

}
