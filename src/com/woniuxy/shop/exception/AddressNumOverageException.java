package com.woniuxy.shop.exception;

public class AddressNumOverageException extends ServiceException {
	public AddressNumOverageException() {

	}

	public AddressNumOverageException(String msg) {
		super(msg);
	}

	public AddressNumOverageException(Throwable cause) {
		super(cause);
	}

	public AddressNumOverageException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
