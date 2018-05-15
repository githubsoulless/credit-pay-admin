package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.RoleInfMapper;
import net.chrone.creditpay.mapper.RoleMenuMapper;
import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.model.RoleInfExample;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.model.RoleMenuExample;
import net.chrone.creditpay.model.RoleMenuExample.Criteria;
import net.chrone.creditpay.service.RoleInfService;


/**
 * 用户角色
 * @author aojiong
 *
 */
@Service
public class RoleInfServiceImpl implements RoleInfService {
	@Autowired
	private RoleInfMapper roleInfMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Override
	public List<RoleInf> getRoleInfAll(){
		
		return roleInfMapper.selectByExample(null);
	}
	@Override
	public int getRoleInfByPageCount(Map<String, Object> map) {
		return roleInfMapper.getRoleInfByPageCount(map);
	}
	@Override
	public List<RoleInf> getRoleInfByPage(Map<String, Object> map) {
		return roleInfMapper.getRoleInfByPage(map);
	}
	@Override
	@Transactional
	public void add(RoleInf roleInf,List<RoleMenu> roleMenus){
		roleInfMapper.insertSelective(roleInf);
		for(RoleMenu rm:roleMenus){
			roleMenuMapper.insertSelective(rm);
		}
		
	}
	
	/**
	 * 修改角色菜单
	 * @param roleId
	 * @param roleMenus
	 */
	@Override
	@Transactional
	public void updateRoleMenu(RoleInf roleInf,List<RoleMenu> roleMenus){
		RoleMenuExample example = new RoleMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleInf.getRoleId());
		roleMenuMapper.deleteByExample(example);
		roleInfMapper.updateByPrimaryKeySelective(roleInf);
		roleMenuMapper.batchAdd(roleMenus);
	}
	
	/**
	 * 根据roleId查询菜单权限
	 * @param roleId
	 * @return
	 */
	@Override
	public List<RoleMenu> getRoleMenuByRoleId(String roleId){
		RoleMenuExample example = new RoleMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return roleMenuMapper.selectByExample(example);
	}
	
	/**
	 * 根据菜单ID和角色ID查询
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	@Override
	public RoleMenu getRoleMenuByKey(String menuId,String roleId){
		return roleMenuMapper.selectByPrimaryKey(menuId, roleId);
	}

	/**
	 * 根据roleId查询角色信息
	 * @param roleId
	 * @return
	 */
	@Override
	public RoleInf getRoleInfByRoleId(String roleId){
		return roleInfMapper.selectByPrimaryKey(roleId);
	}
	
	/**
	 * 根据角色名字查询角色信息
	 * @param roleNm
	 * @return
	 */
	@Override
	public RoleInf getRoleInfByRoleNm(String roleNm){
		RoleInfExample example = new RoleInfExample();
		RoleInfExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNmEqualTo(roleNm);
		List<RoleInf> list = roleInfMapper.selectByExample(example);
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
}
