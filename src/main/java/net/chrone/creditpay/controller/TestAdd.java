package net.chrone.creditpay.controller;


import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-mq.xml"}) //加载配置文件
public class TestAdd {

	@Autowired
	private AgentService agentService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testAddAgent() throws Exception {
		List<List<String>> list = ExcelUtil.readExcelFile(new File("C:\\Users\\Administrator\\Desktop\\参数\\wlf\\new\\代理商.xls"), ".xls", 5);
		list.remove(0);
		for(List<String> l:list) {
			String tel=l.get(3);
			String userName=l.get(2);
			Agent agent = new Agent();
			String id=new IdGen().nextId();
//			agent.setAgentId(StringUtil.getRandom8());
			agent.setAgentId(id.substring(id.length()-8, id.length()));
			agent.setUserId(tel);
			agent.setAgentName(userName);
			agent.setLinkName(userName);
			agent.setMobile(tel);
			agent.setParentAgentId("");
			agent.setAgentLoginId(tel);
			agent.setPassword(tel.substring(tel.length()-6,tel.length()));
			agent.setAddress(tel);
			agent.setEmail("123@qq.com");
			agent.setLevel(3);
			agent.setRowCrtTs(new Date());
			agent.setRowCrtUsr("system");
			agentService.add(agent);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String id=new IdGen().nextId();
		System.out.println(id.substring(id.length()-8, id.length()));
	}
	
}
