package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.Order;

public interface OrderService {

	Map<String, Object> getOrderByPageCount(Order order);

	List<Order> getOrderByPage(Order order);

}
