package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.OrderMapper;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Map<String, Object> getOrderByPageCount(Order order) {
		return orderMapper.getOrderByPageCount(order);
	}

	@Override
	public List<Order> getOrderByPage(Order order) {
		return orderMapper.getOrderByPage(order);
	}

}
