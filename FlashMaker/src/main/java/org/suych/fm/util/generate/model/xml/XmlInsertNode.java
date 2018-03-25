package org.suych.fm.util.generate.model.xml;

import java.util.List;

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

	/**
	 * trim节点
	 */
	private List<XmlTrimNode> trim;

	@Override
	public String toString() {
		return "XmlInsertNode [parameterType=" + parameterType + ", text=" + text + ", trim=" + trim + "]";
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

	public List<XmlTrimNode> getTrim() {
		return trim;
	}

	public void setTrim(List<XmlTrimNode> trim) {
		this.trim = trim;
	}

}
