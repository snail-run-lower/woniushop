package com.woniuxy.shop.exception;

public class DAOException extends RuntimeException {
	public DAOException() {

	}

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
