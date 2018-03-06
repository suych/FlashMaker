package org.suych.fm.web.model.dto;

public class ResultDoubleDTO<A, B> {

	public final A first;
	public final B second;

	public ResultDoubleDTO(A first, B second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "DoubleResult [first=" + first + ", second=" + second + "]";
	}

}
