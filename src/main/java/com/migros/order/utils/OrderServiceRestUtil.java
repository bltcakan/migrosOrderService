package com.migros.order.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.migros.order.model.Orders;
import com.netflix.ribbon.proxy.annotation.Http;

@Component
public class OrderServiceRestUtil {

	public ResponseEntity getRestponseEntity(String path) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);

		if (response.getStatusCode() != HttpStatus.OK) {

			return null;
		}

		return response;

	}

}
