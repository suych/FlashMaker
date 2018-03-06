package org.suych.fm.web.model;

import java.util.Date;

public class ExampleModel extends CommonModel {

	private String example1;
	private int example2;
	private Date example3;
	private String example4;

	@Override
	public String toString() {
		return "ExampleModel [example1=" + example1 + ", example2=" + example2 + ", example3=" + example3
				+ ", example4=" + example4 + "]";
	}

	public String getExample1() {
		return example1;
	}

	public void setExample1(String example1) {
		this.example1 = example1;
	}

	public int getExample2() {
		return example2;
	}

	public void setExample2(int example2) {
		this.example2 = example2;
	}

	public Date getExample3() {
		return example3;
	}

	public void setExample3(Date example3) {
		this.example3 = example3;
	}

	public String getExample4() {
		return example4;
	}

	public void setExample4(String example4) {
		this.example4 = example4;
	}

}
