package com.example.PaymentService.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PaymentService.entity.TransactionDetails;
@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetails,Long>{

	

	TransactionDetails findByOrderId(long orderId);
	
}
