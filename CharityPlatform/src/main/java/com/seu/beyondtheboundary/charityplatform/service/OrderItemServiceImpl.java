package com.seu.beyondtheboundary.charityplatform.service;


import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * OrderItem 服务.
 *

 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem saveOrderItemService(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	@Override
	public void removeOrderItemService(Long id) {
		orderItemRepository.delete(id);
	}

	@Override
	public OrderItem getOrderItemById(Long id) {
		return orderItemRepository.getOne(id);
	}

	public OrderItem getOrderItemByOrderId(String orderid) {
		return orderItemRepository.findByOrderId(orderid);
	}

	public List<OrderItem> getOrderItemByStatus(Long status) { return orderItemRepository.findAllByStatus(status); }

}

