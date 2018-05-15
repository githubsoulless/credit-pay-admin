package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.OperationLog;
import net.chrone.creditpay.service.OperationLogService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: OperationLogController Description: 日志管理
 * 
 * @author huoliang
 * @data 2017年11月23日 上午10:35:01
 *
 */
@Controller
@RequestMapping("operationLog")
public class OperationLogController {

	@Autowired
	private OperationLogService operationLogService;

	@RequestMapping("list")
	public String list(OperationLog operationLog, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		if (StringUtils.isEmpty(operationLog.getStartDate())) {
			operationLog.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(operationLog.getEndDate())) {
			operationLog.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}

		operationLog.setStartRow(starIndex);
		int rowTotal = operationLogService.countOperationLog(operationLog);
		List<OperationLog> list = new ArrayList<OperationLog>();
		if (rowTotal > 0) {
			list = operationLogService.listOperationLog(operationLog);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("types", LogConstant.logTypeMap);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("operationLog", operationLog);
		return "operationLog/list";
	}
	
	@RequestMapping("detail")
	public String detail(String id, Model model){
		OperationLog operationLog = operationLogService.getOperationLog(id);
		model.addAttribute("operationLog", operationLog);
		return "operationLog/detail";
	}

}
