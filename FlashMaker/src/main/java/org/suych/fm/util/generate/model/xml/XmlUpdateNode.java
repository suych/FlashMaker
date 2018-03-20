package org.suych.fm.util.generate.model.xml;

/**
 * update节点
 */
public class XmlUpdateNode extends XmlCommonNode {

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
		return "XmlUpdateNode [parameterType=" + parameterType + ", text=" + text + "]";
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
