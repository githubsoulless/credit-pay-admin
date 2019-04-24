package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.mapper.OrderMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.Card;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CreditRootBankMapper creditRootBankMapper;
	@Override
	public Map<String, Object> getOrderByPageCount(Order order) {
		return orderMapper.getOrderByPageCount(order);
	}

	@Override
	public List<Order> getOrderByPage(Order order) {
		List<Order> list = orderMapper.getOrderByPage(order);
		for(Order l:list){
			CreditRootBank creditRootBank = creditRootBankMapper.selectByPrimaryKey(l.getBankNo());
			if(creditRootBank!=null){
				l.setBankNm(creditRootBank.getBankNm());
			}
		}
		return list;
	}

}
