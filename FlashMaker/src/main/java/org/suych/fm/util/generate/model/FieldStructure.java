package org.suych.fm.util.generate.model;

import java.util.List;

import org.suych.fm.constant.ConstantFieldAccessModifier;
import org.suych.fm.constant.ConstantFieldNonAccessModifier;

/**
 * 字段结构
 */
public class FieldStructure {

	/**
	 * 字段注释
	 */
	private String comments;

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
	 * 字段名
	 */
	private String name;

	@Override
	public String toString() {
		return "FieldStructure [comments=" + comments + ", accessModifier=" + accessModifier + ", nonAccessModifier="
				+ nonAccessModifier + ", javaType=" + javaType + ", name=" + name + "]";
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
