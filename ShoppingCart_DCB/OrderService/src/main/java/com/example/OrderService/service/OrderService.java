package com.example.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.OrderService.entity.Order;
import com.example.OrderService.exception.CustomException;
import com.example.OrderService.external.client.PaymentService;
import com.example.OrderService.external.client.ProductService;
import com.example.OrderService.external.request.PaymentRequest;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.model.PaymentResponse;
import com.example.OrderService.model.ProductResponse;
import com.example.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {
	
	
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public long placeOrder(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		
		//Order entity -save the data with status created
		//Product service -Block products reduce quantity
		//Payment service - Payments -Success->Complete,else cancelled
		
		
		
		log.info("Placing order request :{}",orderRequest);
		
		
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("Creating order with status CREATED");
		
		Order order=Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		
		order=orderRepository.save(order);
		
		log.info("Invoking product service to fetch the product for id:{}",order.getProductId());
		
		
		
		
		log.info("Calling Payment service to complete the payment");
		
		PaymentRequest paymentRequest=PaymentRequest.builder()
                                      .orderId(order.getId())
                                      .paymentMode(orderRequest.getPaymentMode())
                                      .amount(orderRequest.getTotalAmount())
                                      .build();
		
		
		String orderStatus=null;
		
		log.info("{}",paymentRequest);
		
		
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment done succesfully. Changing the order status to OK");
			orderStatus="OK";
		}
		catch(Exception e) {
			log.info("Error occured in Payment, Changing payment status to Payment_Failed");
			orderStatus="Payment_Failed";
		}
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
		
		log.info("Order placed succesfully with Order Id:{}",order.getId());
		
		
		
		
		
		return order.getId();
	}


	public OrderResponse getOrderDetails(long id) {
		// TODO Auto-generated method stub
		log.info("Get order details for ID:{}",id);
		Order order=orderRepository.findById(id).orElseThrow(()->new CustomException("Order Details not found for the the Id: "+id,"ORDER_NOT_FOUND",404));
		
		ProductResponse productResponse=restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);
		log.info(productResponse);
		
		
		PaymentResponse paymentResponse=restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), PaymentResponse.class);
		
		log.info(paymentResponse);
		
		OrderResponse orderResponse=new OrderResponse(order.getId(),order.getOrderDate(),order.getOrderStatus(),order.getAmount(),productResponse,paymentResponse);
		
		
		
	
		
		
		
		return orderResponse;
	}

}
