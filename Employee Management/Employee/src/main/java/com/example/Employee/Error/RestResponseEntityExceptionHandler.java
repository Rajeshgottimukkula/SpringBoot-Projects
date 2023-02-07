package com.example.Employee.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseBody()
	public ErrorMessage employeeNotFoundExceptionHandler(EmployeeNotFoundException exception) {
		ErrorMessage errorMessage=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return errorMessage;
	}
	
	
	@ExceptionHandler(InvalidNameException.class)
	@ResponseBody()
	public ErrorMessage invalidNameHnadler(InvalidNameException exception) {
		ErrorMessage message=new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
		return message;
	}
	
	
	
	
}
