package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.model.RoleMenu;

public interface RoleInfService {

	List<RoleInf> getRoleInfAll();

	int getRoleInfByPageCount(Map<String, Object> map);

	List<RoleInf> getRoleInfByPage(Map<String, Object> map);

	void add(RoleInf roleInf, List<RoleMenu> roleMenus);

	RoleInf getRoleInfByRoleId(String roleId);

	List<RoleMenu> getRoleMenuByRoleId(String roleId);

	void updateRoleMenu(RoleInf roleInf, List<RoleMenu> roleMenus);

	RoleInf getRoleInfByRoleNm(String roleNm);

	RoleMenu getRoleMenuByKey(String menuId, String roleId);

}
