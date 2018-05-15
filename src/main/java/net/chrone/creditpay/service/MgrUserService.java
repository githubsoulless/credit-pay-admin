package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;

public interface MgrUserService {

	int updateMgrUser(MgrUser mgrUser);

	List<MenuInf> getMenuByUserRole(String roleId);

	MgrUser getMgrUserByLoginId(String username);

	int addMgrUser(MgrUser mgrUser);

	int getMgrUserByPageCount(Map<String, Object> map);

	List<MgrUser> getMgrUserByPage(Map<String, Object> map);

}

