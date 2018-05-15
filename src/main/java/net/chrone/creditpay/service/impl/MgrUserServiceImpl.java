package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.MenuInfMapper;
import net.chrone.creditpay.mapper.MgrUserMapper;
import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.MgrUserService;
import net.chrone.creditpay.util.CHException;

@Service
public class MgrUserServiceImpl implements MgrUserService {

	@Autowired
	private MgrUserMapper mgrUserMapper;
	@Autowired
	private MenuInfMapper menuInfMapper;
	
	/**
	 * 根据登录ID查询用户
	 * @param loginId
	 * @return
	 */
	@Override
	public MgrUser getMgrUserByLoginId(String loginId){
		if(StringUtils.isEmpty(loginId)){
			throw new CHException("参数为空");
		}
		return mgrUserMapper.selectByPrimaryKey(loginId);
	}
	
	/**
	 * 根据登录id修改用户
	 * @param mgrMgrUser
	 * @return
	 */
	@Override
	public int updateMgrUser(MgrUser mgrMgrUser){
		return mgrUserMapper.updateByPrimaryKeySelective(mgrMgrUser);
	}
	
	@Override
	public int addMgrUser(MgrUser mgrMgrUser){
		return mgrUserMapper.insertSelective(mgrMgrUser);
	}
	
	/**
	 * 根据角色获取菜单
	 * @param roleId
	 * @return
	 */
	@Override
	public List<MenuInf> getMenuByUserRole(String roleId){
		if(StringUtils.isEmpty(roleId)){
			return null;
		}
		return menuInfMapper.getMenuByUserRole(roleId);
	}
	
	/**
	 * 根据条件分页查询
	 * @param map
	 * @return
	 */
	@Override
	public int getMgrUserByPageCount(Map<String, Object> map) {
		
		return mgrUserMapper.getMgrUserByPageCount(map);
	}

	/**
	 * 根据条件分页查询
	 * @param map
	 * @return
	 */
	@Override
	public List<MgrUser> getMgrUserByPage(Map<String, Object> map) {

		return mgrUserMapper.getMgrUserByPage(map);
	}

}
