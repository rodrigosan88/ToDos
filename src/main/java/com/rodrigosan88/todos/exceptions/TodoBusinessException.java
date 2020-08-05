package com.rodrigosan88.todos.exceptions;

public class TodoBusinessException extends Exception{

	private static final long serialVersionUID = 3849804886428132090L;

	public TodoBusinessException() {
		super();
	}
	
	public TodoBusinessException(String errorMessage) {
		super(errorMessage);
	}

	public TodoBusinessException(Throwable e) {
		super(e);
	}

	public TodoBusinessException(String errorMessage, Throwable e) {
		super(errorMessage, e);
	}

}
