package com.vedantraut.bookwarm.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.vedantraut.bookwarm.dtos.CustomErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request){
		
		System.out.println("Custom Exception Method of MethodArgumentNotValidException invoked!!");
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Map<String, String> fieldErrors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->{
			fieldErrors.put(error.getField(),error.getDefaultMessage());
		});
		
		CustomErrorResponse err = new CustomErrorResponse(
				status.value(),
				status.getReasonPhrase(),
				ex.getMessage(),
				fieldErrors.toString(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(err, status);
	}
	
	@ExceptionHandler({AuthorNotFoundException.class,BookNotFoundException.class})
	public ResponseEntity<CustomErrorResponse> handleAuthorBookNotFoundException(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		System.out.println("Common Exception Handler of Author and Book Not Found is invoked!");
		
		CustomErrorResponse err = new CustomErrorResponse(
				status.value(),
				status.getReasonPhrase(),
				ex.getMessage(),
				request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<>(err, status);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, WebRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		System.out.println("Common Exception Handler is invoked!");
		
		CustomErrorResponse err = new CustomErrorResponse(
				status.value(),
				status.getReasonPhrase(),
				ex.getMessage(),
				request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<>(err, status);
	}
	
	
	
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<CustomErrorResponse> handleBookNotFoundException(Exception ex) {
//		
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		
//		CustomErrorResponse err = new CustomErrorResponse(
//				status.value(),
//				status.getReasonPhrase(),
//				ex.getMessage(),
//				LocalDateTime.now());
//		
//		return new ResponseEntity<>(err, status);
//		
//	}
	
	
	
}
