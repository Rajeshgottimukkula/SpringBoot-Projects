package com.example.OrderService.controller;



//import java.io.IOException;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;

import com.example.OrderService.OrderServiceConfig;
import com.example.OrderService.entity.Order;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.PaymentMode;
import com.example.OrderService.repository.OrderRepository;
import com.example.OrderService.service.OrderService;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
//import com.google.common.net.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.util.StreamUtils.copyToString;



@SpringBootTest({"server.port=0"})
@EnableConfigurationProperties
@AutoConfigureMockMvc
@ContextConfiguration(classes= {OrderServiceConfig.class})
public class OrderControllerTest {
    
	@Autowired
	private OrderService orderService;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	MockMvc mockMvc;
	
	static WireMockExtension wireMockServer=WireMockExtension.newInstance()
			.options(WireMockConfiguration.wireMockConfig()
					.port(8761)).build();
	
	
	private ObjectMapper objectMapper=new ObjectMapper()
			.findAndRegisterModules()
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	
	    @BeforeEach
	    void setup() throws IOException {
	        getProductDetailsResponse();
	        doPayment();
	        getPaymentDetails();
	        reduceQuantity();
	    }

	    private void reduceQuantity() {
	       // circuitBreakerRegistry.circuitBreaker("external").reset();
	        wireMockServer.stubFor(WireMock.put(urlMatching("/product/reduceQuantity/.*"))
	                .willReturn(aResponse()
	                        .withStatus(HttpStatus.OK.value())
	                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));
	    }

	    private void getPaymentDetails() throws IOException {
	       // circuitBreakerRegistry.circuitBreaker("external").reset();
	        wireMockServer.stubFor(get(urlMatching("/payment/.*"))
	                .willReturn(aResponse()
	                        .withStatus(HttpStatus.OK.value())
	                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
	                        .withBody(
	                                copyToString(
	                                        OrderControllerTest.class
	                                                .getClassLoader()
	                                                .getResourceAsStream("mock/GetPayment.json"),
	                                        defaultCharset()
	                                )
	                        )));
	    }

	    private void doPayment() {
	        wireMockServer.stubFor(post(urlEqualTo("/payment"))
	                .willReturn(aResponse()
	                        .withStatus(HttpStatus.OK.value())
	                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));
	    }

	    private void getProductDetailsResponse() throws IOException {
	        // GET /product/1
	        wireMockServer.stubFor(WireMock.get("/product/1")
	                .willReturn(aResponse()
	                        .withStatus(HttpStatus.OK.value())
	                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
	                        .withBody(copyToString(
	                                OrderControllerTest.class
	                                        .getClassLoader()
	                                        .getResourceAsStream("mock/GetProduct.json"),
	                                defaultCharset()
	                        ))));

	    }
	    
	    
	    
	    private OrderRequest getMockOrderRequest() {
	        return OrderRequest.builder()
	                .productId(1)
	                .paymentMode(PaymentMode.CASH)
	                .quantity(10)
	                .totalAmount(200)
	                .build();
	    }
	    
	    
	    
	    @Test
	    public void test_WhenPlaceOrder_DoPayment_Success() throws Exception {
	        //First Place Order
	        // Get Order by Order Id from Db and check
	        //Check Output

	        OrderRequest orderRequest = getMockOrderRequest();
	        MvcResult mvcResult
	                = mockMvc.perform(MockMvcRequestBuilders.post("/order/placeOrder")
	                        //.with(jwt().authorities(new SimpleGrantedAuthority("Customer")))
	                        .contentType(MediaType.APPLICATION_JSON_VALUE)
	                        .content(objectMapper.writeValueAsString(orderRequest))
	                ).andExpect(MockMvcResultMatchers.status().isOk())
	                .andReturn();

	        String orderId = mvcResult.getResponse().getContentAsString();

	        Optional<Order> order = orderRepo.findById(Long.valueOf(orderId));
	        assertTrue(order.isPresent());

	        Order o = order.get();
	        assertEquals(Long.parseLong(orderId), o.getId());
	        assertEquals("PLACED", o.getOrderStatus());
	        assertEquals(orderRequest.getTotalAmount(), o.getAmount());
	        assertEquals(orderRequest.getQuantity(), o.getQuantity());

	    }
			
	
	
	
}
