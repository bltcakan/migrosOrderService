package com.migros.order.service;

import java.util.List;

import com.migros.order.model.Orders;

public interface OrderService {

	List<Orders> findAllOrders();

	List<Orders> findOrderByUserId(int id);
	
	Orders findOrderById(int id);

	void createOrder(Orders orders);

	Orders updateOrder(Orders orders);

	void deleteOrder(long id);

	
	

}
