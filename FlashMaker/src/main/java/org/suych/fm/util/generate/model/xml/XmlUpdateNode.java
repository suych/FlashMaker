package org.suych.fm.util.generate.model.xml;

import java.util.List;

/**
 * update节点
 */
public class XmlUpdateNode extends XmlCommonNode {

	/**
	 * 参数类型
	 */
	private String parameterType;

	/**
	 * sql语句第一部分
	 */
	private String textOne;

	/**
	 * trim节点
	 */
	private List<XmlTrimNode> trim;

	/**
	 * sql语句第二部分
	 */
	private String textTwo;

	@Override
	public String toString() {
		return "XmlUpdateNode [parameterType=" + parameterType + ", textOne=" + textOne + ", trim=" + trim
				+ ", textTwo=" + textTwo + "]";
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getTextOne() {
		return textOne;
	}

	public void setTextOne(String textOne) {
		this.textOne = textOne;
	}

	public List<XmlTrimNode> getTrim() {
		return trim;
	}

	public void setTrim(List<XmlTrimNode> trim) {
		this.trim = trim;
	}

	public String getTextTwo() {
		return textTwo;
	}

	public void setTextTwo(String textTwo) {
		this.textTwo = textTwo;
	}

}
