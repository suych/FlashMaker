package org.suych.fm.util.generate.model.java;

/**
 * 参数结构
 */
public class ParamterStructure {

	/**
	 * 注解
	 */
	private AnnotationStructure annotation;

	/**
	 * 参数类型
	 */
	private String type;

	/**
	 * 参数名称
	 */
	private String name;

	@Override
	public String toString() {
		return "ParamterStructure [annotation=" + annotation + ", type=" + type + ", name=" + name + "]";
	}

	public AnnotationStructure getAnnotation() {
		return annotation;
	}

	public void setAnnotation(AnnotationStructure annotation) {
		this.annotation = annotation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
