package com.migros.order.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.migros.order.model.Orders;

@Repository
public class OrdersRepositoryImpl implements OrderRepository {
	@PersistenceContext
	private EntityManager entityManger;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void createOrder(Orders orders) {
		orderRepository.createOrder(orders);

	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteOrder(id);
	}

	@Override
	public List<Orders> findByUserId(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(id);

	}

	@Override
	public Orders updateOrders(Orders orders) {
		// TODO Auto-generated method stub
		return orderRepository.updateOrders(orders);
	}

	@Override
	public List<Orders> findAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAllOrders();
	}

	@Override
	public List<Orders> findOrderByUserId(long id) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Orders findOrderById(long id) {
		return orderRepository.findOrderById(id);
	}

}
