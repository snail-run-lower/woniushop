package com.woniuxy.shop.exception;

public class CategoryExistException extends ServiceException {
	public CategoryExistException() {

	}

	public CategoryExistException(String msg) {
		super(msg);
	}

	public CategoryExistException(Throwable cause) {
		super(cause);
	}

	public CategoryExistException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
