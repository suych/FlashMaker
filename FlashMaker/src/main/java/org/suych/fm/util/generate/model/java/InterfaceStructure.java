package org.suych.fm.util.generate.model.java;

import java.util.List;

import org.suych.fm.constant.ConstantInterfaceAccessModifier;
import org.suych.fm.util.generate.model.BaseStructure;

/**
 * 接口结构
 */
public class InterfaceStructure extends BaseStructure {

	/**
	 * 本地包名
	 */
	private String localPackage;

	/**
	 * 引入包名
	 */
	private ImportPackageStructure importPackage;

	/**
	 * 接口注释
	 */
	private String comments;

	/**
	 * 访问控制修饰符
	 */
	private ConstantInterfaceAccessModifier acessModifier;

	/**
	 * 是否继承其他接口
	 * true-是/false-否
	 */
	private Boolean extendsInterface;

	/**
	 * 父类名称
	 */
	private String baseInterfaceName;

	/**
	 * 方法
	 */
	private List<MethodStructure> method;

	@Override
	public String toString() {
		return "InterfaceStructure [localPackage=" + localPackage + ", importPackage=" + importPackage + ", comments="
				+ comments + ", acessModifier=" + acessModifier + ", extendsInterface=" + extendsInterface
				+ ", baseInterfaceName=" + baseInterfaceName + ", method=" + method + "]";
	}

	public String getLocalPackage() {
		return localPackage;
	}

	public void setLocalPackage(String localPackage) {
		this.localPackage = localPackage;
	}

	public ImportPackageStructure getImportPackage() {
		return importPackage;
	}

	public void setImportPackage(ImportPackageStructure importPackage) {
		this.importPackage = importPackage;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ConstantInterfaceAccessModifier getAcessModifier() {
		return acessModifier;
	}

	public void setAcessModifier(ConstantInterfaceAccessModifier acessModifier) {
		this.acessModifier = acessModifier;
	}

	public Boolean getExtendsInterface() {
		return extendsInterface;
	}

	public void setExtendsInterface(Boolean extendsInterface) {
		this.extendsInterface = extendsInterface;
	}

	public String getBaseInterfaceName() {
		return baseInterfaceName;
	}

	public void setBaseInterfaceName(String baseInterfaceName) {
		this.baseInterfaceName = baseInterfaceName;
	}

	public List<MethodStructure> getMethod() {
		return method;
	}

	public void setMethod(List<MethodStructure> method) {
		this.method = method;
	}

}
