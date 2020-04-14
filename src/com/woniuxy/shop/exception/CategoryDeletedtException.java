package com.woniuxy.shop.exception;

public class CategoryDeletedtException extends ServiceException {
	public CategoryDeletedtException() {

	}

	public CategoryDeletedtException(String msg) {
		super(msg);
	}

	public CategoryDeletedtException(Throwable cause) {
		super(cause);
	}

	public CategoryDeletedtException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
