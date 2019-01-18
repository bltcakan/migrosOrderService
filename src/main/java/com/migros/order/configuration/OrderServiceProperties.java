package com.migros.order.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;

@ConfigurationProperties
@Order(value = 3)
public class OrderServiceProperties {

	@Value("${product.service.url}")
	private String productServiceUrl;

	@Value("${user.service.url}")
	private String userServiceUrl;

	public String getProductServiceUrl() {
		return productServiceUrl;
	}

	public void setProductServiceUrl(String productServiceUrl) {
		this.productServiceUrl = productServiceUrl;
	}

	public String getUserServiceUrl() {
		return userServiceUrl;
	}

	public void setUserServiceUrl(String userServiceUrl) {
		this.userServiceUrl = userServiceUrl;
	}

}
