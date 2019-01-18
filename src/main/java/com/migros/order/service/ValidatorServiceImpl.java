package com.migros.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.migros.order.configuration.OrderServiceProperties;
import com.migros.order.exception.CustomerNotFoundException;
import com.migros.order.exception.ProductNotFoundException;
import com.migros.order.model.OrderDetails;
import com.migros.order.model.Orders;
import com.migros.order.utils.OrderServiceRestUtil;

@Service
public class ValidatorServiceImpl implements ValidatorService {

	@Autowired
	OrderServiceProperties orderServiceProperties;

	@Autowired
	OrderServiceRestUtil orderServiceRestUtil;

	@Override
	public boolean checkQuantityOfProduct(Orders orders) {

		String checkPath = orderServiceProperties.getProductServiceUrl() + "/getQuatityOfProduct/"
				+ orders.getOrderDetails().iterator().next().getProductId() + "/"
				+ orders.getOrderDetails().iterator().next().getProductQuantiy();

		ResponseEntity<String> responseEntity = orderServiceRestUtil.getRestponseEntity(checkPath);

		if (responseEntity == null) {

			throw new ProductNotFoundException("insufficient quainty of product");

		}

		return true;
	}

	@Override
	public boolean checkUser(Orders orders) {

		String checkPath = orderServiceProperties.getUserServiceUrl() + "/getUser/" + orders.getUserId();
		ResponseEntity<String> responseEntity = orderServiceRestUtil.getRestponseEntity(checkPath);
		if (responseEntity == null) {

			throw new CustomerNotFoundException("Customer not found !");

		}

		return true;
	}

	@Override
	public boolean checkProduct(Orders orders) {
		String checkPath = orderServiceProperties.getProductServiceUrl() + "/getQuatityOfProduct/"
				+ orders.getOrderDetails().iterator().next().getProductId();

		ResponseEntity<String> responseEntity = orderServiceRestUtil.getRestponseEntity(checkPath);

		if (responseEntity == null) {

			throw new ProductNotFoundException(
					"Product not found with id" + orders.getOrderDetails().iterator().next().getProductId());

		}

		return true;
	}

	@Override
	public boolean isValidOrder(Orders orders) {
		checkQuantityOfProduct(orders);
		checkUser(orders);
		checkProduct(orders);
		return true;
	}

}
