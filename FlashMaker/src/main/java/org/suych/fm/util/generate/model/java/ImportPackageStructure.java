package org.suych.fm.util.generate.model.java;

import java.util.Set;

/**
 * 引入包结构
 */
public class ImportPackageStructure {

	/**
	 * jdk自带包
	 */
	Set<String> javaPackage;

	/**
	 * 三方包
	 */
	Set<String> threePartyPackage;

	/**
	 * 自定义包
	 */
	Set<String> customPackage;

	@Override
	public String toString() {
		return "ImportPackageStructure [javaPackage=" + javaPackage + ", threePartyPackage=" + threePartyPackage
				+ ", customPackage=" + customPackage + "]";
	}

	public Set<String> getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(Set<String> javaPackage) {
		this.javaPackage = javaPackage;
	}

	public Set<String> getThreePartyPackage() {
		return threePartyPackage;
	}

	public void setThreePartyPackage(Set<String> threePartyPackage) {
		this.threePartyPackage = threePartyPackage;
	}

	public Set<String> getCustomPackage() {
		return customPackage;
	}

	public void setCustomPackage(Set<String> customPackage) {
		this.customPackage = customPackage;
	}

}
