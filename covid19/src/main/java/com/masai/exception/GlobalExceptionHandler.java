package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
	//here we are recommended to provide 4 Exception handlers with Error Details
	//1 - Custom Exception Handler
	//2 - Any Exception Handler
	//3 - No Handler found Exception
	//4 - Method Argument Invalid Exception
	
	//1st Custom Exception Handler
	
//	@ExceptionHandler(anyCustomExc.class)
//	public ResponseEntity<MyErrorDetails> myExcHandler( anyCustomExc ce , WebRequest wr ){
//		
//		System.out.println("Inside myExcHandler method of GlobalExceptionHandler class");
//		
//		MyErrorDetails err = new MyErrorDetails();
//		err.setTimeStamp(LocalDateTime.now());
//		err.setMsg(ce.getMessage());
//		err.setDetails(wr.getDescription(false));
//		
//		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//	}
	
	//we need to add all custom exception like IdCard Exception,PanCard Exception,AadharCard Exception, Member Exception 
	
	//2nd Any Exception Handler
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> anyExcHandler( Exception e, WebRequest wr ){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMsg(e.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	//3rd No Handler found Exception Handler
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myNoHandlerFoundExcHandler( NoHandlerFoundException ne ,WebRequest wr ){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMsg(ne.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	//4rth Method Arguments Invalid Exception Handler
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgInvalidExcHandler( MethodArgumentNotValidException me ){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMsg(me.getMessage());
		err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}
