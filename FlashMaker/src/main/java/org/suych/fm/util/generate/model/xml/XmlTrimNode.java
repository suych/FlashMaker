package org.suych.fm.util.generate.model.xml;

import java.util.List;

/**
 * trim节点
 */
public class XmlTrimNode {

	/**
	 * 前缀
	 */
	private String prefix;

	/**
	 * 后缀
	 */
	private String suffix;

	/**
	 * 去除多余的后缀内容
	 */
	private String suffixOverrides;

	/**
	 * 去除多余的前缀内容
	 */
	private String prefixOverrides;

	/**
	 * if节点
	 */
	private List<XmlIfNode> ifNode;

	@Override
	public String toString() {
		return "XmlTrimNode [prefix=" + prefix + ", suffix=" + suffix + ", suffixOverrides=" + suffixOverrides
				+ ", prefixOverrides=" + prefixOverrides + ", ifNode=" + ifNode + "]";
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffixOverrides() {
		return suffixOverrides;
	}

	public void setSuffixOverrides(String suffixOverrides) {
		this.suffixOverrides = suffixOverrides;
	}

	public String getPrefixOverrides() {
		return prefixOverrides;
	}

	public void setPrefixOverrides(String prefixOverrides) {
		this.prefixOverrides = prefixOverrides;
	}

	public List<XmlIfNode> getIfNode() {
		return ifNode;
	}

	public void setIfNode(List<XmlIfNode> ifNode) {
		this.ifNode = ifNode;
	}

}
