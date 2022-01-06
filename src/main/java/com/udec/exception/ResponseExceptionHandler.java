package com.udec.exception;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.udec.dto.ErrorDTO;



@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ObjectNotFoundExceptionHandler.class)
	public final ResponseEntity<ErrorDTO> objectNotFoundExceptionHandler(ObjectNotFoundExceptionHandler ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);			
	}
	
	@ExceptionHandler(ObjectItsCreatedExceptionHandler.class)
	public final ResponseEntity<ErrorDTO> objectItsCreatedExceptionHandler(ObjectItsCreatedExceptionHandler ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);			
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorDTO> nullPointerExceptionHandler(NullPointerException ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ErrorDTO> noSuchElementExceptionHandler(NoSuchElementException ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), "ID consultado no se encuentra en la base de datos", request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);			
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDTO> constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), ex.getConstraintViolations().toString() , request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);			
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<ErrorDTO> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.toString(), "pelicula con ID a eliminar no se encuentra en la base de datos", request.getDescription(false));
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);			
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDTO> exceptionHandler(Exception ex, WebRequest request){
		ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.name(), status.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorDTO error = new ErrorDTO(status.name(), status.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensaje="";
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			mensaje+=error.getDefaultMessage()+" ,";
		}
		ErrorDTO error = new ErrorDTO(status.name(), status.toString(), mensaje, request.getDescription(false));
		//ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.name(), status.toString(), "el formato de la fecha está mal, es aaaa-MM-dd" , request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	
	
}
