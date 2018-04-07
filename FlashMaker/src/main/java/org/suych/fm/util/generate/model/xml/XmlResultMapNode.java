package org.suych.fm.util.generate.model.xml;

import java.util.List;

/**
 * resultMap节点
 */
public class XmlResultMapNode extends XmlCommonNode {

	/**
	 * 类型
	 */
	private String type;

	/**
	 * id节点
	 */
	private XmlResultMapIdNode idNode;

	/**
	 * result节点
	 */
	private List<XmlResultMapResultNode> resultNode;

	@Override
	public String toString() {
		return "XmlResultMapNode [type=" + type + ", idNode=" + idNode + ", resultNode=" + resultNode + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public XmlResultMapIdNode getIdNode() {
		return idNode;
	}

	public void setIdNode(XmlResultMapIdNode idNode) {
		this.idNode = idNode;
	}

	public List<XmlResultMapResultNode> getResultNode() {
		return resultNode;
	}

	public void setResultNode(List<XmlResultMapResultNode> resultNode) {
		this.resultNode = resultNode;
	}

}
