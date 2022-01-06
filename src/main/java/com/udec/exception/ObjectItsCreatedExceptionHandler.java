package com.udec.exception;

public class ObjectItsCreatedExceptionHandler extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ObjectItsCreatedExceptionHandler(String mensaje) {
		super(mensaje);
	}
}
