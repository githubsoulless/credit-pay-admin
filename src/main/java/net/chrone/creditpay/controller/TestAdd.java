package net.chrone.creditpay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.AgentMapper;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentExample;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.FileUtil;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-mq.xml" }) // 加载配置文件
public class TestAdd {

	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentMapper agentMapper;

	@Test
	@Transactional
	@Rollback(false)
	public void testAddAgent() throws Exception {
		List<List<String>> list = ExcelUtil.readExcelFile(
				new File("C:\\Users\\Administrator\\Desktop\\参数\\jzh\\个人版代理商名单_level.xlsx"), ".xlsx", 4);
		list.remove(0);
		for (List<String> l : list) {
			String levelName = l.get(1);
			String tel = l.get(3);
			if(StringUtils.isEmpty(tel)){
				continue;
			}
			int level = 3;
			if("股东".equals(levelName)) {
				level = 1;
			}else if("核心代理商".equals(levelName)) {
				level = 2;
			}else {
				level = 3;
			}
			System.out.println(tel+":"+levelName+":"+level);
			Agent agent = new Agent();
			agent.setLevel(level);
			AgentExample example = new AgentExample();
			AgentExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(tel);
			agentMapper.updateByExampleSelective(agent, example);
//			String id = new IdGen().nextId();
////			agent.setAgentId(StringUtil.getRandom8());
//			agent.setAgentId(id.substring(id.length() - 8, id.length()));
//			agent.setUserId(tel);
//			agent.setAgentName(userName);
//			agent.setLinkName(userName);
//			agent.setMobile(tel);
//			agent.setParentAgentId("");
//			agent.setAgentLoginId(tel);
//			agent.setPassword(tel.substring(tel.length() - 6, tel.length()));
//			agent.setAddress(tel);
//			agent.setEmail("123@qq.com");
//			agent.setLevel(3);
//			agent.setRowCrtTs(new Date());
//			agent.setRowCrtUsr("system");
//			agentService.add(agent);
		}
	}

	public static void main(String[] args) throws Exception {
		List<List<String>> list = ExcelUtil.readExcelFile(new File("C:\\Users\\Administrator\\Desktop\\参数\\jzh\\test.xlsx"), ".xlsx", 15);
		list.remove(0);
		List<String> sqlList = new ArrayList<>();
		for(List<String> l:list) {
//			System.out.println(l);
			if(StringUtils.isEmpty(l.get(2))||StringUtils.isEmpty(l.get(14))||l.get(2).equals(l.get(14))) {
				continue;
			}
			System.out.println(l);
			String sql = "update t_app_user set parent_user_id='"+l.get(14)+"' where user_id='"+l.get(2)+"';";
			System.out.println(sql);
			sqlList.add(sql);
		}
		FileUtil.writeBackFile(sqlList, "C:\\Users\\Administrator\\Desktop\\参数\\jzh\\20181015\\22017.sql");
	}

}
