package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.service.MenuInfService;
import net.chrone.creditpay.service.RoleInfService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.service.impl.SeqServiceImpl;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

/**
 * 管理员角色
 * @author aojiong
 *
 */

@Controller
@RequestMapping("mgrRole")
public class RoleController {
	
	@Autowired 
	private RoleInfService roleInfService;
	@Autowired
	private MenuInfService menuInfService;
	
	@Autowired
	private SeqService seqService;

	/**
	 * 管理员角色列表
	 * @param roleName
	 * @param startDate
	 * @param endDate
	 * @param start
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(String roleName,String startDate,String endDate,String start,Model model) throws Exception{
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(roleName)){
			map.put("roleName", roleName);
		}
		if(StringUtils.isNotEmpty(startDate)){
			map.put("startTime",DateUtils.parseDate(startDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotEmpty(endDate)){
			map.put("endTime",DateUtils.parseDate(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		map.put("startRow", starIndex);
		map.put("pageSize", Constants.SHOW_NUM);
		int rowTotal = roleInfService.getRoleInfByPageCount(map);
		List<RoleInf>  list =new ArrayList<RoleInf>();
		if(rowTotal>0){
			list = roleInfService.getRoleInfByPage(map);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("roleName",roleName);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "systemRole/mgrRole";
	}
	
	/**
	 * 添加角色
	 * @param request
	 * @param type
	 * @param roleNm
	 * @param menuIds
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request,String type,String roleNm,String menuIds,Model model){
		String message="";
		try {
			if("toAdd".equals(type)){
				List<MenuInf> menuList = menuInfService.getMenuAll();
				model.addAttribute("menuList", menuList);
			}else{
				if(StringUtils.isEmpty(type)
					||StringUtils.isEmpty(roleNm)
					||StringUtils.isEmpty(menuIds)
				 ){
					throw new CHException("参数为空");
				}
				MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
				RoleInf roleInf = new RoleInf();
				String roleId = seqService.updateAndGetSequence(SeqServiceImpl.T_ROLE_INF, 9);
				roleInf.setRoleNm(roleNm);
				roleInf.setRoleId(roleId);
				roleInf.setRowCrtUsr(userInfSeesion.getLoginId());
				roleInf.setRecUpdUsr(userInfSeesion.getLoginId());
				roleInf.setRowCrtTs(new Date());
				
				List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
				String[] menuId = menuIds.split("\\|");
				for(int i=0;i<menuId.length;i++){
					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setMenuId(menuId[i]);
					roleMenu.setRoleId(roleId);
					roleMenu.setRowCrtUsr(userInfSeesion.getLoginId());
					roleMenu.setRowCrtTs(new Date());
					roleMenus.add(roleMenu);
				}
				roleInfService.add(roleInf, roleMenus);
				message="success";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		
		return "systemRole/mgrRole_add";
	}
	
	/**
	 * 修改角色
	 * @param request
	 * @param type
	 * @param roleId
	 * @param menuIds
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(HttpServletRequest request,String type,String roleId,String menuIds,Model model){
		String message="系统异常";
		try {
			if(StringUtils.isEmpty(type)||StringUtils.isEmpty(roleId)){
				throw new CHException("参数异常");
			}
			if("toUpdate".equals(type)){
				RoleInf roleInf = roleInfService.getRoleInfByRoleId(roleId);
				if(roleInf==null){
					throw new CHException("角色信息不存在");
				}
				List<MenuInf> menuList = menuInfService.getMenuAll();
				List<RoleMenu> roleMenuList = roleInfService.getRoleMenuByRoleId(roleId);
				model.addAttribute("roleInf", roleInf);
				model.addAttribute("menuList", menuList);
				model.addAttribute("roleMenuList", roleMenuList);
				message="success";
			}else{
				if(StringUtils.isEmpty(menuIds)
					 ){
						throw new CHException("参数为空");
					}
				List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
				MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
				RoleInf roleInf = new RoleInf();
				roleInf.setRoleId(roleId);
				roleInf.setRecUpdUsr(userInfSeesion.getLoginId());
				roleInf.setRecUpdTs(new Date());
				
				String[] menuId = menuIds.split("\\|");
				for(int i=0;i<menuId.length;i++){
					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setMenuId(menuId[i]);
					roleMenu.setRoleId(roleId);
					roleMenu.setRowCrtUsr(userInfSeesion.getLoginId());
					roleMenu.setRowCrtTs(new Date());
					roleMenus.add(roleMenu);
				}
				roleInfService.updateRoleMenu(roleInf, roleMenus);
				message="success";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		
		return "systemRole/mgrRole_update";
	}
	
	/**
	 * 角色详情
	 * @param request
	 * @param type
	 * @param roleId
	 * @param menuIds
	 * @param model
	 * @return
	 */
	@RequestMapping("detail") 
	public String detail(HttpServletRequest request,String type,String roleId,String menuIds,Model model){
		String message="系统异常";
		try {
			if(StringUtils.isEmpty(roleId)){
				throw new CHException("参数异常");
			}
			List<MenuInf> menuList = menuInfService.getMenuAll();
			List<RoleMenu> roleMenuList = roleInfService.getRoleMenuByRoleId(roleId);
			RoleInf roleInf = roleInfService.getRoleInfByRoleId(roleId);
			if(roleInf==null){
				throw new CHException("角色信息不存在");
			}
			model.addAttribute("menuList", menuList);
			model.addAttribute("roleMenuList", roleMenuList);
			model.addAttribute("roleInf", roleInf);
			message="success";
		} catch (CHException e) {
			message=e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		
		return "systemRole/mgrRole_detail";
	}
	
	@RequestMapping("check_roleNm")
	public void checkLoginId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String message="false";
		try {
			String roleNm = request.getParameter("roleNm");
			if(StringUtils.isEmpty(roleNm)){
				throw new CHException("参数为空");
			}
			RoleInf roleInf = roleInfService.getRoleInfByRoleNm(roleNm);
			if(roleInf!=null&&roleNm.equals(roleInf.getRoleNm())){
				throw new CHException("角色名称已存在,请重新输入");
			}
			message="true";
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error(e.getErrInfo());
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
		
	}
	
}
