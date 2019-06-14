package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.AppUser;

public interface DayTransService {

	void dayUserOrderStatics(String orderDt, List<AppUser> userList);

}
