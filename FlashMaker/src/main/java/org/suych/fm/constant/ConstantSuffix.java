package org.suych.fm.constant;

public class ConstantSuffix {

	private String type;

	public static final ConstantSuffix JAVA_FILE = new ConstantSuffix(".java");
	public static final ConstantSuffix XML = new ConstantSuffix(".xml");

	public static final ConstantSuffix DOMAIN_OBJECT = new ConstantSuffix("DO");
	public static final ConstantSuffix MAPPER = new ConstantSuffix("Mapper");
	public static final ConstantSuffix SERVICE = new ConstantSuffix("Service");
	public static final ConstantSuffix SERVICE_IMPL = new ConstantSuffix("ServiceImpl");
	public static final ConstantSuffix CONTROLLER = new ConstantSuffix("Controller");

	public ConstantSuffix(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
