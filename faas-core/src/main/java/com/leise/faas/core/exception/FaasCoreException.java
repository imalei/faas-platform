package com.leise.faas.core.exception;

public class FaasCoreException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5703637883547428034L;

	private final String errorCode;

	private Object[] params;

	public FaasCoreException(String errorCode) {
		this.errorCode = errorCode;
	}

	public FaasCoreException(String errorCode, Object[] params) {
		this.errorCode = errorCode;
		this.params = params;
	}

	public FaasCoreException(String errorCode, Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
