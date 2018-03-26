package org.suych.fm.util.generate.model.java;

import java.util.List;
import java.util.Map;

import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodNonAccessModifier;

/**
 * 方法结构
 */
public class MethodStructure {

	/**
	 * 方法注释
	 */
	private String comments;

	/**
	 * 注解
	 */
	private List<AnnotationStructure> annotation;

	/**
	 * 访问控制修饰符
	 */
	private ConstantMethodAccessModifier accessModifier;

	/**
	 * 非访问控制修饰符
	 */
	private List<ConstantMethodNonAccessModifier> nonAccessModifier;

	/**
	 * 返回值
	 */
	private String returnValue;

	/**
	 * 方法名
	 */
	private String name;

	/**
	 * 参数
	 */
	private List<ParamterStructure> parameter;

	/**
	 * 方法体
	 */
	private String methodBody;

	@Override
	public String toString() {
		return "MethodStructure [comments=" + comments + ", annotation=" + annotation + ", accessModifier="
				+ accessModifier + ", nonAccessModifier=" + nonAccessModifier + ", returnValue=" + returnValue
				+ ", name=" + name + ", parameter=" + parameter + ", methodBody=" + methodBody + "]";
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

	public ConstantMethodAccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(ConstantMethodAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public List<ConstantMethodNonAccessModifier> getNonAccessModifier() {
		return nonAccessModifier;
	}

	public void setNonAccessModifier(List<ConstantMethodNonAccessModifier> nonAccessModifier) {
		this.nonAccessModifier = nonAccessModifier;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ParamterStructure> getParameter() {
		return parameter;
	}

	public void setParameter(List<ParamterStructure> parameter) {
		this.parameter = parameter;
	}

	public String getMethodBody() {
		return methodBody;
	}

	public void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}

}
