package com.migros.order;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.migros.order.configuration.OrderServiceProperties;
import com.migros.order.model.OrderDetails;
import com.migros.order.model.Orders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigrosOrderServiceApplicationTests {

	@Autowired
	OrderServiceProperties orderServiceProperties;

	@Test
	public void contextLoads() {
	}

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void when_insert_valid_order() {

		Orders order = new Orders();
		order.setUserId(23);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProductId(3);
		orderDetails.setProductQuantiy(45);
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2.setProductId(13);
		orderDetails2.setProductQuantiy(4533);
		Set<OrderDetails> orderDetailSet = new HashSet<>();
		orderDetailSet.add(orderDetails);
		orderDetailSet.add(orderDetails2);
		order.setOrderDetails(orderDetailSet);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(order, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/order_service/order_service",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(201));

	}

	@Test
	public void when_insert_unvalid_order() {

		Orders order = new Orders();
		order.setUserId(23);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProductId(3);
		orderDetails.setProductQuantiy(45);
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2.setProductId(13);
		orderDetails2.setProductQuantiy(4533);
		Set<OrderDetails> orderDetailSet = new HashSet<>();
		orderDetailSet.add(orderDetails);
		orderDetailSet.add(orderDetails2);
		order.setOrderDetails(orderDetailSet);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(order, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/order_service/order_service",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(400));

	}

	@Test
	public void check_unknown_user_service() {
		Orders order = new Orders();
		order.setUserId(12);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProductId(3);
		orderDetails.setProductQuantiy(45);
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2.setProductId(13);
		orderDetails2.setProductQuantiy(4533);
		Set<OrderDetails> orderDetailSet = new HashSet<>();
		orderDetailSet.add(orderDetails);
		orderDetailSet.add(orderDetails2);
		order.setOrderDetails(orderDetailSet);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(order, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/order_service/order_service",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(400));

	}
}
