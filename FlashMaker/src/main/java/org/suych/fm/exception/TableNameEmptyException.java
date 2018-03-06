package org.suych.fm.exception;

public class TableNameEmptyException extends RuntimeException {

	public TableNameEmptyException() {

	}

	public TableNameEmptyException(String message) {
		super(message);
	}

	public TableNameEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableNameEmptyException(Throwable cause) {
		super(cause);
	}

}
