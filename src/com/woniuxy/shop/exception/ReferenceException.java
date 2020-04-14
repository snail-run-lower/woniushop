package com.woniuxy.shop.exception;

public class ReferenceException extends ServiceException {
	public ReferenceException() {

	}

	public ReferenceException(String msg) {
		super(msg);
	}

	public ReferenceException(Throwable cause) {
		super(cause);
	}

	public ReferenceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
