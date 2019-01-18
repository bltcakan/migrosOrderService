package com.migros.order.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.migros.order.validation.ValidOrderQuantity;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetailSeqGen")
	@SequenceGenerator(name = "orderDetailSeqGen", sequenceName = "orderDetailSeqGen_Seq")
	private Long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ordersId")
	private Orders orders;

	@Column(name = "product_id")
	private int productId;

	@ValidOrderQuantity
	@Column(name = "product_quantity")
	private int productQuantiy;

	@Column(name = "unit_price")
	private int unitPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductQuantiy() {
		return productQuantiy;
	}

	public void setProductQuantiy(int productQuantiy) {
		this.productQuantiy = productQuantiy;
	}

}
