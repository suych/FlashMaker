package org.suych.fm.util.generate.model.xml;

/**
 * select节点
 */
public class XmlSelectNode extends XmlCommonNode {

	/**
	 * 返回类型
	 */
	private String resultType;

	/**
	 * sql语句
	 */
	private String text;

	@Override
	public String toString() {
		return "XmlSelectNode [resultType=" + resultType + ", text=" + text + "]";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
