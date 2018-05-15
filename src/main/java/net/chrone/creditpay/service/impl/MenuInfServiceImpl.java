package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.MenuInfMapper;
import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MenuInfExample;
import net.chrone.creditpay.service.MenuInfService;

/**
 * 菜单
 * @author aojiong
 *
 */
@Service
public class MenuInfServiceImpl implements MenuInfService{
	@Autowired
	private MenuInfMapper menuInfMapper;

	@Override
	public List<MenuInf> getMenuAll() {
		MenuInfExample example = new MenuInfExample();
		example.setOrderByClause(" menu_sort,menu_id");
		return menuInfMapper.selectByExample(example);
	}
	
}
