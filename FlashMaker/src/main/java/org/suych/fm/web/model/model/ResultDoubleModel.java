package org.suych.fm.web.model.model;

public class ResultDoubleModel<A, B> {

	public final A first;
	public final B second;

	public ResultDoubleModel(A first, B second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "DoubleResult [first=" + first + ", second=" + second + "]";
	}

}
