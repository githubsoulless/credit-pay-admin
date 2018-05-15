package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.Coupon;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.CouponService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: CouponController Description: 优惠券管理
 * 
 * @author huoliang
 * @data 2017年11月28日 上午9:43:29
 *
 */

@Controller
@RequestMapping("couponMgr")
public class CouponController {

	private final static Logger logger = Logger.getLogger(CouponController.class);

	@Autowired
	private CouponService couponService;
	@Autowired
	private LogConstant logConstant;
	@Autowired
	private LevelService levelService;
	@Autowired
	private AgentService agentService;

	@RequestMapping("list")
	public String list(Coupon coupon, Model model, String start, HttpServletRequest request) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		coupon.setStartRow(starIndex);
		int rowTotal = couponService.countCoupons(coupon);
		List<Coupon> list = new ArrayList<>();
		if(rowTotal > 0){
			list = couponService.listcoupons(coupon);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		List<Level> levels = levelService.getLevelAll();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("coupon", coupon);
		model.addAttribute("levels", levels);
		model.addAttribute("rowTotal", rowTotal);
		return "coupon/list";
	}

	@RequestMapping("add")
	public String add(Coupon coupon, String type, Model model, HttpServletRequest request) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("add".equals(type)) {
				coupon.setRowCrtTs(new Date());
				coupon.setRowCrtUsr(userInfSeesion.getLoginId());
				coupon.setRecUpdUsr(userInfSeesion.getLoginId());
				couponService.saveCoupon(coupon);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "优惠券管理，新增优惠券，优惠券名称="+coupon.getName(), 7, request);
				message = "success";
			}else{
				List<Level> levels = levelService.getLevelAll();
				model.addAttribute("levels", levels);
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "coupon/add";
	}

	@RequestMapping("update")
	public String update(Coupon coupon, String type, Model model, HttpServletRequest request) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("update".equals(type)) {
				coupon.setRecUpdUsr(userInfSeesion.getLoginId());
				coupon.setRecUpdTs(new Date());
				couponService.updateCoupon(coupon);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "优惠券管理，修改优惠券，优惠券ID="+coupon.getId(), 7, request);
				message = "success";
			}else{
				List<Level> levels = levelService.getLevelAll();
				model.addAttribute("levels", levels);
				Coupon oCoupon = couponService.getCouponByPrimaayKey(coupon.getId());
				model.addAttribute("coupon", oCoupon);
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "coupon/update";
	}

	@RequestMapping("detail")
	public String detail(String id, Model model, HttpServletRequest request) {
		Coupon coupon = couponService.getCouponByPrimaayKey(id);
		model.addAttribute("coupon", coupon);
		return "coupon/detail";
	}
	
	
	@RequestMapping("sendCoupon")
	public String sendCoupon(Coupon coupon, String type, Model model, HttpServletRequest request) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		coupon.setRecUpdUsr(userInfSeesion.getLoginId());
		try {
			if ("send".equals(type)) {
				int result = couponService.sendCoupons(coupon);
				if(0 == result){
					StringBuffer memo = new StringBuffer("发放目标用户为：");
					if(1 == coupon.getSendType()){
						memo.append("按等级，等级编号=");
						for(Integer s : coupon.getLevelIds()){
							memo.append(s).append(",");
						}
					}
					if(2 == coupon.getSendType()){
						memo.append("指定用户");
					}
					if(3 == coupon.getSendType()){
						memo.append("按代理，代理ID="+coupon.getAgentId());
						
					}
					logConstant.createTweblog(userInfSeesion.getLoginId(), "优惠券管理，手动发放优惠券，" + memo.toString(), 7, request);
					message = "success";
				}
				if(1 == result){
					message = "优惠券剩余数量不足";
				}
				if(2 == result){
					message = "手工发放失败，请稍后重试";
				}
				
			}else{
				List<Level> levels = levelService.getLevelAll();
				model.addAttribute("levels", levels);
				Coupon oCoupon = couponService.getCouponByPrimaayKey(coupon.getId());
				model.addAttribute("coupon", oCoupon);
				List<Agent> agentList = agentService.getAgentAll();
				model.addAttribute("agentList", agentList);
				model.addAttribute("agentListJson", JSON.toJSONString(agentList));
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "coupon/send";
	}

}
