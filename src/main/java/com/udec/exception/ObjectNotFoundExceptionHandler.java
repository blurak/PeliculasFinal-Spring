package com.udec.exception;

public class ObjectNotFoundExceptionHandler  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExceptionHandler(String mensaje) {
        super(mensaje);
    }
}
