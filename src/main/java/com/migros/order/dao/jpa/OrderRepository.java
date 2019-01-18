package com.migros.order.dao.jpa;

import java.util.List;

import com.migros.order.model.Orders;

public interface OrderRepository {

	void createOrder(Orders orders);

	void deleteOrder(Long id);

	List<Orders> findByUserId(int id);

	Orders updateOrders(Orders orders);

	List<Orders> findAllOrders();

	List<Orders> findOrderByUserId(long id);

	Orders findOrderById(long id);

}
