package com.chinarewards.posnet2.report.exception;

public class ServiceLevelException extends Exception {

	private static final long serialVersionUID = -9191668766860119680L;

	public ServiceLevelException() {
		super();
	}

	public ServiceLevelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLevelException(String message) {
		super(message);
	}

	public ServiceLevelException(Throwable cause) {
		super(cause);
	}

}
