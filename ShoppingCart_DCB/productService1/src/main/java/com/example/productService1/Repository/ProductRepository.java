package com.example.productService1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productService1.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
