package com.example.productService1.Exception;

public class ProductServiceCustomException extends RuntimeException  {

	
	
	String errorCode;
	
	public ProductServiceCustomException(String message,String errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	
}
