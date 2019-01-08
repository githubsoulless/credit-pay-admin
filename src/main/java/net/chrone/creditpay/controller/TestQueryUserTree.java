package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.UserTree;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.UserTreeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-mq.xml" }) // 加载配置文件
public class TestQueryUserTree {

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private UserTreeService userTreeService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testQueryUserTree() {
		List<AppUser> list = appUserService.getAppUserAll();
		for(AppUser user:list) {
			List<UserTree> treeList = new ArrayList<>();
			List<String> zjuserList = getZJUserIdsBypUserId(user.getUserId());
			for(String zuser:zjuserList) {
				UserTree tree = new UserTree();
				tree.setUserId(user.getUserId());
				tree.setLowUserId(zuser);
				tree.setType(0);//直接
				treeList.add(tree);
			}
			if(!treeList.isEmpty()) {
				userTreeService.batchAdd(treeList);
				treeList = new ArrayList<>();
			}
			List<String> jjuserList = getJJUserIdsBypUserId(user.getUserId());
			for(String zuser:jjuserList) {
				UserTree tree = new UserTree();
				tree.setUserId(user.getUserId());
				tree.setLowUserId(zuser);
				tree.setType(1);//间接
				treeList.add(tree);
			}
			if(!treeList.isEmpty()) {
				userTreeService.batchAdd(treeList);
				treeList = new ArrayList<>();
			}
		}
	}
	
	/**
	 * 直推
	 * @param userId
	 * @return
	 */
	private List<String> getZJUserIdsBypUserId(String userId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(userId);
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
		allIdList.addAll(userIdList);
		return allIdList;
	}
	
	/**
	 * 间接
	 * @param userId
	 * @return
	 */
	private List<String> getJJUserIdsBypUserId(String userId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(userId);
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
        while (!userIdList.isEmpty()) {
            idMap.clear();
            idMap.put("ids", userIdList);
            userIdList = appUserMapper.getAppUserByParentIdList(idMap);
            allIdList.addAll(userIdList);
        }
        return allIdList;
	}
}
