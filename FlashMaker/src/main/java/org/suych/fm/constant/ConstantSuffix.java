package org.suych.fm.constant;

public class ConstantSuffix {

	private String type;

	public static final ConstantSuffix JAVA_FILE = new ConstantSuffix(".java");
	public static final ConstantSuffix XML = new ConstantSuffix(".xml");

	public static final ConstantSuffix DOMAIN_OBJECT = new ConstantSuffix("DO");
	public static final ConstantSuffix MAPPER = new ConstantSuffix("Mapper");

	public ConstantSuffix(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
