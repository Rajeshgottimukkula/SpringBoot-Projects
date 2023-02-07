package com.example.productService1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService1.Model.*;
import com.example.productService1.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	//@PreAuthorize("hasAuthority('Admin')")
	@PostMapping()
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		
		
		long productId=productService.addProduct(productRequest);
		return new ResponseEntity<>(productId,HttpStatus.CREATED);
	}
	//@PreAuthorize("hasAuthority('Admin')||hasAuthority('Customer')||hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable long id){
		ProductResponse productResponse=productService.getProductById(id);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long id,@RequestParam long quantity){
		productService.reduceQuantity(id,quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
