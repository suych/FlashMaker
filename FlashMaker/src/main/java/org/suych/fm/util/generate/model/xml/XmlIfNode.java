package org.suych.fm.util.generate.model.xml;

/**
 * if节点
 */
public class XmlIfNode {

	/**
	 * 条件判断
	 */
	private String test;

	/**
	 * sql语句
	 */
	private String text;

	@Override
	public String toString() {
		return "XmlIfNode [test=" + test + ", text=" + text + "]";
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
