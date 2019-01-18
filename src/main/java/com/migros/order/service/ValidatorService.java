package com.migros.order.service;

import com.migros.order.model.Orders;

public interface ValidatorService {
	boolean checkQuantityOfProduct(Orders orders);

	boolean checkUser(Orders orders);

	boolean checkProduct(Orders orders);
	
	boolean isValidOrder(Orders orders);
}
