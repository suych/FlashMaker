package org.suych.fm.util.generate.model.xml;

/**
 * sql节点
 */
public class XmlSqlNode extends XmlCommonNode {

	/**
	 * sql语句
	 */
	private String text;

	@Override
	public String toString() {
		return "XmlSqlNode [text=" + text + "]";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
