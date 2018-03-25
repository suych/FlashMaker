package org.suych.fm.util.generate.model.xml;

/**
 * include节点
 */
public class XmlIncludeNode {

	/**
	 * 引用id
	 */
	private String refid;

	@Override
	public String toString() {
		return "XmlIncludeNode [refid=" + refid + "]";
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

}
