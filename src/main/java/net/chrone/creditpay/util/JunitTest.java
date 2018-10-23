package net.chrone.creditpay.util;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.service.MenuInfService;
import net.chrone.creditpay.service.RoleInfService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
        "classpath:applicationContext-mq.xml" })
public class JunitTest {
	@Autowired 
	private RoleInfService roleInfService;
	@Autowired
	private MenuInfService menuInfService;
	@Test
	public void test()throws Exception{
		
		
		List<MenuInf> menuList = menuInfService.getMenuAll();
		List<RoleMenu> roleMenuList = roleInfService.getRoleMenuByRoleId("900000000");
		List<MenuInf> finalMenuList = new ArrayList<>();
		for(MenuInf menuInf:menuList) {
			for(RoleMenu roleMenu : roleMenuList ) {
				if(menuInf.getMenuId().equals(roleMenu.getMenuId())) {
					finalMenuList.add(menuInf);
					break;
				}
			}
		}
		
	}
}

