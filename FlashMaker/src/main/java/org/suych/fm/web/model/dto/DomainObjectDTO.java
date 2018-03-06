package org.suych.fm.web.model.dto;

import java.util.List;
import java.util.Set;

import org.suych.fm.web.model.bo.FieldInfoBO;

public class DomainObjectDTO {

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
	private String classComments;

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 字段名/字段类型/字段注释
	 */
	private List<FieldInfoBO> fieldInfo;

	@Override
	public String toString() {
		return "DomainObjectDTO [localPackage=" + localPackage + ", importPackage=" + importPackage + ", classComments="
				+ classComments + ", className=" + className + ", fieldInfo=" + fieldInfo + "]";
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

	public String getClassComments() {
		return classComments;
	}

	public void setClassComments(String classComments) {
		this.classComments = classComments;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<FieldInfoBO> getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(List<FieldInfoBO> fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

}
