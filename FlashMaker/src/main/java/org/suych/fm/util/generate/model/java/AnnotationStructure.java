package org.suych.fm.util.generate.model.java;

import java.util.Map;

import org.suych.fm.util.generate.model.BaseStructure;

/**
 * 注解结构
 */
public class AnnotationStructure extends BaseStructure {

	/**
	 * 属性
	 */
	private Map<String, String> attribute;

	@Override
	public String toString() {
		return "AnnotationStructure [attribute=" + attribute + "]";
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

}
