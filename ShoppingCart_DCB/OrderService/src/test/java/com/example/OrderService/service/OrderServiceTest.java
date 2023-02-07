package com.example.OrderService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.OrderService.entity.Order;
import com.example.OrderService.exception.CustomException;
import com.example.OrderService.external.client.PaymentService;
import com.example.OrderService.external.client.ProductService;
import com.example.OrderService.external.request.PaymentRequest;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.model.PaymentMode;
import com.example.OrderService.model.PaymentResponse;
import com.example.OrderService.model.ProductResponse;
import com.example.OrderService.repository.OrderRepository;
@SpringBootTest
class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	ProductService productService;
	
	@Mock
	PaymentService paymentService;
	
	@Mock
	RestTemplate restTemplate;
	
	
	@InjectMocks
	OrderService orderService=new OrderService();
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	
	
	
	

	@DisplayName("Get order- Success scenario")
	@Test
	void test_when_Order_success() {
		
		//Mocking- Internal calls
		
		
		
		Order order=getMockOrder();
		Mockito.when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		
		Mockito.when(restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class)).thenReturn(getMockProductResponse());
		Mockito.when(restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), PaymentResponse.class)).thenReturn(getMockPaymentResponse());
		
		//Actual method calling
		
		OrderResponse orderResponse=orderService.getOrderDetails(1);
		
		
		
		//Verification
		Mockito.verify(orderRepository, times(1)).findById(anyLong());
		Mockito.verify(restTemplate, times(1)).getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);
		Mockito.verify(restTemplate, times(1)).getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), PaymentResponse.class);
		
		//Assertions
		
		Assertions.assertNotNull(orderResponse);
		Assertions.assertEquals(order.getId(), orderResponse.getOrderId());
		
	}
	
	
	
	
	@DisplayName("Get Order - Failure scenario")
	@Test
	void test_When_Get_Order_Not_Found() {
		
		//Mocking the Internal calls
		
		Mockito.when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		
		//Actual calling
		//OrderResponse orderResponse=orderService.getOrderDetails(1);
		
		
		
		
		//Assertions
		
		CustomException exception=Assertions.assertThrows(CustomException.class,()-> orderService.getOrderDetails(1));
		
		Assertions.assertEquals("ORDER_NOT_FOUND",exception.getErrorCode());
		Assertions.assertEquals(404, exception.getStatus());
		
		//Verification
		Mockito.verify(orderRepository,times(1)).findById(anyLong());
		
	}
	
	
	
	
	@DisplayName("Place order- Success Scenario")
	@Test
	void test_Place_order_success() {
		OrderRequest orderRequest=getMockOrderRequest();
		Order order=getMockOrder();
		
		
		//Mocking the internal calls
Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);
Mockito.when(productService.reduceQuantity(anyLong(), anyLong())).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
Mockito.when(paymentService.doPayment(any(PaymentRequest.class))).thenReturn(new ResponseEntity<Long>(1L,HttpStatus.OK));
		
		
		//Actual call
		
		long orderId=orderService.placeOrder(orderRequest);
		
		//Verification
		Mockito.verify(orderRepository,times(2)).save(any());
		Mockito.verify(productService,times(1)).reduceQuantity(anyLong(), anyLong());
		Mockito.verify(paymentService,times(1)).doPayment(any(PaymentRequest.class));
		
		
		//Assertion
		
		
		assertEquals(order.getId(), orderId);
		
	}
	
	
	
	
	
	@DisplayName("Place_order_payment_failed_Scenario")
	@Test
	void test_when_place_order_payment_failed_then_order_placed() {
		OrderRequest orderRequest=getMockOrderRequest();
		Order order=getMockOrder();
		
		//Mocking the internal calls
		
		Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);
		Mockito.when(productService.reduceQuantity(anyLong(), anyLong())).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
		Mockito.when(paymentService.doPayment(any(PaymentRequest.class))).thenThrow(new RuntimeException());
		
		
		//Actual call
		

		long orderId=orderService.placeOrder(orderRequest);
		
		
		//Verification
		Mockito.verify(orderRepository,times(2)).save(any());
		Mockito.verify(productService,times(1)).reduceQuantity(anyLong(), anyLong());
		Mockito.verify(paymentService,times(1)).doPayment(any(PaymentRequest.class));
		
		//Assertion
		
		
		

		assertEquals(order.getId(), orderId);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	private OrderRequest getMockOrderRequest() {
		// TODO Auto-generated method stub
		return OrderRequest.builder()
				.productId(1)
				.quantity(20)
				.paymentMode(PaymentMode.CASH)
				.totalAmount(10000)
				.build();
	}
	private PaymentResponse getMockPaymentResponse() {
		// TODO Auto-generated method stub
		return PaymentResponse.builder()
				.paymentId(1)
				.paymentDate(Instant.now())
				.paymentMode(PaymentMode.CASH)
				.status("ACCEPTED")
				.amount(20000)
				.orderId(1)
				.build();
	}

	private ProductResponse getMockProductResponse() {
		// TODO Auto-generated method stub
		return ProductResponse.builder()
				.productId(2)
				.productName("Iphone")
				.price(80000)
				.quantity(200)
				.build();
	}

	private Order getMockOrder() {
		return Order.builder()
				.orderStatus("PLACED")
				.orderDate(Instant.now())
				.id(1)
				.amount(100)
				.productId(2)
				.quantity(200)
				.build();
	}
}
