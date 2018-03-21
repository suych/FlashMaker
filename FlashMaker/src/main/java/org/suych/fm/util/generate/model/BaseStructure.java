package org.suych.fm.util.generate.model;

public class BaseStructure {

	/**
	 * 结构名称
	 * 类名/接口名/XML名
	 */
	private String name;

	@Override
	public String toString() {
		return "BaseStructure [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
