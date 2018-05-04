package org.suych.fm.util.generate.model.java;

import java.util.List;

import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantFieldNonAccessModifier;
import org.suych.fm.util.generate.model.BaseStructure;

/**
 * 字段结构
 */
public class FieldStructure extends BaseStructure {

	/**
	 * 字段注释
	 */
	private String comments;

	/**
	 * 注解
	 */
	private List<AnnotationStructure> annotation;

	/**
	 * 访问控制修饰符
	 */
	private ConstantFieldAccessModifier accessModifier;

	/**
	 * 非访问控制修饰符
	 */
	private List<ConstantFieldNonAccessModifier> nonAccessModifier;

	/**
	 * java类型
	 */
	private String javaType;

	/**
	 * 是否初始化，默认不初始化
	 */
	private Boolean initialization = false;

	/**
	 * 初始化语句
	 */
	private String initializationValue;

	@Override
	public String toString() {
		return "FieldStructure [comments=" + comments + ", annotation=" + annotation + ", accessModifier="
				+ accessModifier + ", nonAccessModifier=" + nonAccessModifier + ", javaType=" + javaType
				+ ", initialization=" + initialization + ", initializationValue=" + initializationValue + "]";
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<AnnotationStructure> getAnnotation() {
		return annotation;
	}

	public void setAnnotation(List<AnnotationStructure> annotation) {
		this.annotation = annotation;
	}

	public ConstantFieldAccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(ConstantFieldAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public List<ConstantFieldNonAccessModifier> getNonAccessModifier() {
		return nonAccessModifier;
	}

	public void setNonAccessModifier(List<ConstantFieldNonAccessModifier> nonAccessModifier) {
		this.nonAccessModifier = nonAccessModifier;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public Boolean getInitialization() {
		return initialization;
	}

	public void setInitialization(Boolean initialization) {
		this.initialization = initialization;
	}

	public String getInitializationValue() {
		return initializationValue;
	}

	public void setInitializationValue(String initializationValue) {
		this.initializationValue = initializationValue;
	}

}
