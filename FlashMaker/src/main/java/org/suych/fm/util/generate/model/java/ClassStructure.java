package org.suych.fm.util.generate.model.java;

import java.util.List;
import java.util.Set;

import org.suych.fm.constant.ConstantClassAccessModifier;
import org.suych.fm.constant.ConstantClassNonAccessModifier;
import org.suych.fm.util.generate.model.BaseStructure;

/**
 * 类结构
 */
public class ClassStructure extends BaseStructure {

	/**
	 * 本地包名
	 */
	private String localPackage;

	/**
	 * 引入包名
	 */
	private Set<String> importPackage;

	/**
	 * 类注释
	 */
	private String comments;

	/**
	 * 注解
	 */
	private List<AnnotationStructure> annotation;

	/**
	 * 访问控制修饰符
	 */
	private ConstantClassAccessModifier acessModifier;

	/**
	 * 类非访问控制修饰符
	 */
	private ConstantClassNonAccessModifier nonAccessModifier;

	/**
	 * 是否继承其他类 
	 * true-是/false-否
	 */
	private Boolean extendsClass;

	/**
	 * 父类名称
	 */
	private String baseClassName;

	/**
	 * 是否实现接口
	 * true-是/false-否
	 */
	private Boolean implementInterface;

	/**
	 * 接口名称列表
	 */
	private List<String> interfaceName;

	/**
	 * 字段
	 */
	private List<FieldStructure> field;

	/**
	 * 方法
	 */
	private List<MethodStructure> method;

	@Override
	public String toString() {
		return "ClassStructure [localPackage=" + localPackage + ", importPackage=" + importPackage + ", comments="
				+ comments + ", annotation=" + annotation + ", acessModifier=" + acessModifier + ", nonAccessModifier="
				+ nonAccessModifier + ", extendsClass=" + extendsClass + ", baseClassName=" + baseClassName
				+ ", implementInterface=" + implementInterface + ", interfaceName=" + interfaceName + ", field=" + field
				+ ", method=" + method + "]";
	}

	public String getLocalPackage() {
		return localPackage;
	}

	public void setLocalPackage(String localPackage) {
		this.localPackage = localPackage;
	}

	public Set<String> getImportPackage() {
		return importPackage;
	}

	public void setImportPackage(Set<String> importPackage) {
		this.importPackage = importPackage;
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

	public ConstantClassAccessModifier getAcessModifier() {
		return acessModifier;
	}

	public void setAcessModifier(ConstantClassAccessModifier acessModifier) {
		this.acessModifier = acessModifier;
	}

	public ConstantClassNonAccessModifier getNonAccessModifier() {
		return nonAccessModifier;
	}

	public void setNonAccessModifier(ConstantClassNonAccessModifier nonAccessModifier) {
		this.nonAccessModifier = nonAccessModifier;
	}

	public Boolean getExtendsClass() {
		return extendsClass;
	}

	public void setExtendsClass(Boolean extendsClass) {
		this.extendsClass = extendsClass;
	}

	public String getBaseClassName() {
		return baseClassName;
	}

	public void setBaseClassName(String baseClassName) {
		this.baseClassName = baseClassName;
	}

	public Boolean getImplementInterface() {
		return implementInterface;
	}

	public void setImplementInterface(Boolean implementInterface) {
		this.implementInterface = implementInterface;
	}

	public List<String> getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(List<String> interfaceName) {
		this.interfaceName = interfaceName;
	}

	public List<FieldStructure> getField() {
		return field;
	}

	public void setField(List<FieldStructure> field) {
		this.field = field;
	}

	public List<MethodStructure> getMethod() {
		return method;
	}

	public void setMethod(List<MethodStructure> method) {
		this.method = method;
	}

}
