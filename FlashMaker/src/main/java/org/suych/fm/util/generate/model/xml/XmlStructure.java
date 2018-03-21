package org.suych.fm.util.generate.model.xml;

import java.util.List;

import org.suych.fm.util.generate.model.BaseStructure;

/**
 * XML结构
 */
public class XmlStructure extends BaseStructure {

	/**
	 * 文档类型
	 */
	private String docType;

	/**
	 * 根节点
	 */
	private String rootNodeName;

	/**
	 * 命名空间
	 */
	private String namespace;

	/**
	 * 节点集合
	 */
	List<XmlCommonNode> node;

	@Override
	public String toString() {
		return "XmlStructure [docType=" + docType + ", rootNodeName=" + rootNodeName + ", namespace=" + namespace
				+ ", node=" + node + "]";
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getRootNodeName() {
		return rootNodeName;
	}

	public void setRootNodeName(String rootNodeName) {
		this.rootNodeName = rootNodeName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public List<XmlCommonNode> getNode() {
		return node;
	}

	public void setNode(List<XmlCommonNode> node) {
		this.node = node;
	}

}
