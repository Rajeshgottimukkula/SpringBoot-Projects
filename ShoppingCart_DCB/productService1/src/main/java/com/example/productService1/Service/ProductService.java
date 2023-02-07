package com.example.productService1.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productService1.Entity.Product;
import com.example.productService1.Exception.ProductNotFoundException;
import com.example.productService1.Exception.ProductServiceCustomException;
import com.example.productService1.Model.ProductRequest;
import com.example.productService1.Model.ProductResponse;
import com.example.productService1.Repository.*;
import com.example.productService1.Repository.ProductRepository;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class ProductService {

	

	@Autowired
	ProductRepository productRepository;
	
	public long addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		
		Product product=Product.builder()
				.productName(productRequest.getProductName())
				.price(productRequest.getPrice())
				.quantity(productRequest.getQuantity())
				.build();
		
		productRepository.save(product);
		log.info("Product Added");
		
		
		
		return product.getProductId();
	}

	public ProductResponse getProductById(long id) {
		// TODO Auto-generated method stub
		log.info("Get the product for id: {}",id);
		Product product=productRepository.findById(id).orElseThrow(()-> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
		
		
		ProductResponse productResponse=new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	public void reduceQuantity(long id, long quantity) {
		// TODO Auto-generated method stub
		log.info("reduce quantity {} for id {}",quantity,id);
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ProductServiceCustomException("Product with given Id not found","Product_Not_Found"));
		
		if(product.getQuantity()< quantity) {
			throw new ProductServiceCustomException("Product does not have suffiecient quantity","Insufficient Quantity");
		}
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product); 
		log.info("product quantity updated succesfully");
		
		
	}

	
}
