package com.migros.order.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migros.order.dao.jpa.OrdersRepositoryImpl;
import com.migros.order.model.OrderDetails;
import com.migros.order.model.Orders;
import com.migros.order.model.Product;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersRepositoryImpl ordersRepositoryImpl;

	@Autowired
	ValidatorService validatorService;

	@Override
	public List<Orders> findAllOrders() {
		// TODO Auto-generated method stub
		return ordersRepositoryImpl.findAllOrders();
	}

	@Override
	public void createOrder(Orders orders) {
		validatorService.isValidOrder(orders);
		ordersRepositoryImpl.createOrder(orders);
	}

	@Override
	public void deleteOrder(long id) {
		ordersRepositoryImpl.deleteOrder(id);
	}

	@Override
	public Orders updateOrder(Orders orders) {
		return ordersRepositoryImpl.updateOrders(orders);

	}

	@Override
	public List<Orders> findOrderByUserId(int id) {
		// TODO Auto-generated method stub
		return ordersRepositoryImpl.findByUserId(id);
	}

	@Override
	public Orders findOrderById(int id) {
		return null;
		// TODO Auto-generated method stub

	}

}
