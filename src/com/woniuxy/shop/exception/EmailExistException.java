package com.woniuxy.shop.exception;

public class EmailExistException extends ServiceException {
	public EmailExistException() {

	}

	public EmailExistException(String msg) {
		super(msg);
	}

	public EmailExistException(Throwable cause) {
		super(cause);
	}

	public EmailExistException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
