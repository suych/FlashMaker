package org.suych.fm.util.generate.model.xml;

/**
 * insert节点
 */
public class XmlInsertNode extends XmlCommonNode {

	/**
	 * 参数类型
	 */
	private String parameterType;

	/**
	 * sql语句
	 */
	private String text;

	@Override
	public String toString() {
		return "XmlInsertNode [parameterType=" + parameterType + ", text=" + text + "]";
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
