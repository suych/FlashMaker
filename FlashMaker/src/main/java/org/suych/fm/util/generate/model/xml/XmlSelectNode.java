package org.suych.fm.util.generate.model.xml;

/**
 * select节点
 */
public class XmlSelectNode extends XmlCommonNode {

	/**
	 * 返回类型
	 */
	private String resultMap;

	/**
	 * sql语句第一部分
	 */
	private String textOne;

	/**
	 * include节点
	 */
	private XmlIncludeNode include;

	/**
	 * sql语句第二部分
	 */
	private String textTwo;

	@Override
	public String toString() {
		return "XmlSelectNode [resultMap=" + resultMap + ", textOne=" + textOne + ", include=" + include + ", textTwo="
				+ textTwo + "]";
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

	public String getTextOne() {
		return textOne;
	}

	public void setTextOne(String textOne) {
		this.textOne = textOne;
	}

	public XmlIncludeNode getInclude() {
		return include;
	}

	public void setInclude(XmlIncludeNode include) {
		this.include = include;
	}

	public String getTextTwo() {
		return textTwo;
	}

	public void setTextTwo(String textTwo) {
		this.textTwo = textTwo;
	}

}
