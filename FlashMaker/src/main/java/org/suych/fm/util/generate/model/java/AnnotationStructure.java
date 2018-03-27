package org.suych.fm.util.generate.model.java;

import java.util.Map;

import org.suych.fm.util.generate.model.BaseStructure;

/**
 * 注解结构
 */
public class AnnotationStructure extends BaseStructure {

	/**
	 * 直接赋值
	 */
	private String value;

	/**
	 * 属性赋值
	 */
	private Map<String, String> attribute;

	@Override
	public String toString() {
		return "AnnotationStructure [value=" + value + ", attribute=" + attribute + "]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

}
