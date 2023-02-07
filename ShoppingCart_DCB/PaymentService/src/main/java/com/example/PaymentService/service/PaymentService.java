package com.example.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentMode;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.PaymentResponse;
import com.example.PaymentService.repository.PaymentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentService {

	
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public long doPayment(PaymentRequest paymentRequest) {
		// TODO Auto-generated method stub
		
		log.info("Recording PaymentDetails:{}",paymentRequest);
		
		TransactionDetails transactionDetails=TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId())
				.referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount())
				.build();
		
		paymentRepository.save(transactionDetails);
		log.info("Transaction completed with Id:{}",transactionDetails.getId());
		
		return transactionDetails.getId();
	}
    
	public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
		// TODO Auto-generated method stub
	TransactionDetails transactionDetails=paymentRepository.findByOrderId(orderId);
	log.info(transactionDetails);
	
	PaymentResponse paymentResponse=PaymentResponse.builder()
			.paymentId(transactionDetails.getId())
			.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
			.orderId(transactionDetails.getOrderId())
			.status(transactionDetails.getPaymentStatus())
			.amount(transactionDetails.getAmount())
			.paymentDate(transactionDetails.getPaymentDate())
			.build();
	
	
	return paymentResponse;
		
	}

}
