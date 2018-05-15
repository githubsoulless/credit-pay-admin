package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AgentMenuMapper;
import net.chrone.creditpay.model.AgentMenu;
import net.chrone.creditpay.service.AgentMenuService;

/**
 * 
 * Title: AgentMenuServiceImpl 
 * Description: 代理系统菜单表 
 * @author huoliang
 * @data 2017年11月23日 下午5:08:50
 *
 */
@Service
public class AgentMenuServiceImpl implements AgentMenuService {

	@Autowired
	private AgentMenuMapper agentMenuMapper;
	
	@Override
	public List<AgentMenu> listAgentMenu() {
		return agentMenuMapper.selectByExample(null);
	}

}
