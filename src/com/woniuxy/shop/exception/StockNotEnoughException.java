package com.woniuxy.shop.exception;

public class StockNotEnoughException extends ServiceException {
	public StockNotEnoughException() {

	}

	public StockNotEnoughException(String msg) {
		super(msg);
	}

	public StockNotEnoughException(Throwable cause) {
		super(cause);
	}

	public StockNotEnoughException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
