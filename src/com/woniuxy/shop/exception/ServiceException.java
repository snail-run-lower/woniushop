package com.woniuxy.shop.exception;

public class ServiceException extends RuntimeException{
	public ServiceException() {

	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
