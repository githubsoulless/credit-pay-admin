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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.http.HttpResponse;
import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.MenuInfService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.RoleInfService;
import net.chrone.creditpay.service.RootBankService;
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
@RequestMapping("rootbank")
public class RootBankController {
	
	@Autowired 
	private RootBankService rootBankService;
	@Autowired
	private PayChannelService payChannelService;
	

	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		
		List<RootBank> list = rootBankService.queryRootBankList();
		PayChannel payChannel = new PayChannel();
		payChannel.setStatus(1);
		payChannel.setChnlType(4);
		List<PayChannel> payChannels = payChannelService.listPayChannel(payChannel);
		List<PayChannel> retPayChannels = new ArrayList<>();
		for(PayChannel tmPayChannel: payChannels) {
			PayChannel retPayChannel = new PayChannel();
			retPayChannel.setCode(tmPayChannel.getCode());
			retPayChannel.setName(tmPayChannel.getName());
			retPayChannels.add(retPayChannel);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("chnls", JSON.toJSONString(retPayChannels));
		return "rootbank/list";
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
	/*@RequestMapping("add")
	public String add(HttpServletRequest request,String type,String roleNm,String menuIds,Model model){
		String message="";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		
		try {
			if("toAdd".equals(type)){
				List<MenuInf> menuList = menuInfService.getMenuAll();
				
				List<MenuInf> finalMenuList = new ArrayList<>();
				List<RoleMenu> curRoleMenuList = roleInfService.getRoleMenuByRoleId(userInfSeesion.getRoleId());
				for(MenuInf menuInf:menuList) {
					for(RoleMenu roleMenu : curRoleMenuList ) {
						if(menuInf.getMenuId().equals(roleMenu.getMenuId())) {
							finalMenuList.add(menuInf);
							break;
						}
					}
				}
				
				model.addAttribute("menuList", finalMenuList);
			}else{
				if(StringUtils.isEmpty(type)
					||StringUtils.isEmpty(roleNm)
					||StringUtils.isEmpty(menuIds)
				 ){
					throw new CHException("参数为空");
				}
				
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
	}*/
	
	
	
}
