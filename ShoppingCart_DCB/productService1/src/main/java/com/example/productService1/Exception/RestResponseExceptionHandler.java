package com.example.productService1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseExceptionHandler {

	@ExceptionHandler(ProductServiceCustomException.class)
	@ResponseBody
	@ResponseStatus
	public ResponseEntity<ErrorMessage> ProductServiceCustomExceptionHandler(ProductServiceCustomException exception) {
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(),exception.errorCode);
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseBody()
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage ProductNotFoundExceptionHandler(ProductNotFoundException exception) {
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(),"Product Not Found");
		return errorMessage;
	}
	
	
	
	
}
