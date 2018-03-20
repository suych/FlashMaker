package org.suych.fm.util.generate.model.xml;

/**
 * delete节点
 */
public class XmlDeleteNode extends XmlCommonNode {

	/**
	 * sql语句第一部分
	 */
	private String textOne;

	/**
	 * foreach节点
	 */
	private XmlForeachNode foreach;

	/**
	 * sql语句第二部分
	 */
	private String textTwo;

	@Override
	public String toString() {
		return "XmlDeleteNode [textOne=" + textOne + ", foreach=" + foreach + ", textTwo=" + textTwo + "]";
	}

	public String getTextOne() {
		return textOne;
	}

	public void setTextOne(String textOne) {
		this.textOne = textOne;
	}

	public XmlForeachNode getForeach() {
		return foreach;
	}

	public void setForeach(XmlForeachNode foreach) {
		this.foreach = foreach;
	}

	public String getTextTwo() {
		return textTwo;
	}

	public void setTextTwo(String textTwo) {
		this.textTwo = textTwo;
	}

}
