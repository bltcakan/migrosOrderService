package com.migros.order.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.migros.order.dao.jpa.OrderRepository;
import com.migros.order.model.Orders;
import com.migros.order.service.OrderService;

@RestController
@RequestMapping("order_service")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/test")
	public String testOrderControllerService() {

		return "Hello From Order Service";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/createOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<URI> createOwner(@RequestBody Orders orders) {
		logger.debug("Order creating process is starting ");
		orderService.createOrder(orders);
		HttpHeaders responseHeader = new HttpHeaders();
		return new ResponseEntity<>(responseHeader, HttpStatus.CREATED);

	}

	@DeleteMapping("/orders/{id}")
	public void deletOrder(@PathVariable long id) {
		logger.debug("Order delete process is starting ");
		orderService.deleteOrder(id);
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Orders orders, @PathVariable long id) {
		
		Orders order = orderRepository.findOrderById(id);
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		orders.setId(id);
		orderRepository.createOrder(orders);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-all-orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> getSavedScheduledList() {
		logger.debug("Data conroller has been activated");
		return ResponseEntity.ok(orderService.findAllOrders());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<Set<String>> handleConstraintViolation(Exception ex, WebRequest request) {
		Throwable cause = ((TransactionSystemException) ex).getRootCause();
		if (cause instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause)
					.getConstraintViolations();

			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
					.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
							constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
					.collect(Collectors.toList()));
			logger.error("Exception has been occured" + messages.toString());

			return ResponseEntity.ok(messages);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
