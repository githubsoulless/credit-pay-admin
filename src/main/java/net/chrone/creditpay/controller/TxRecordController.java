package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.TxRecord;
import net.chrone.creditpay.service.TxRecordService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: TxRecordController 
 * Description: 提现查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:42:33
 *
 */
@Controller
@RequestMapping("txRecord")
public class TxRecordController {
	
	@Autowired
	private TxRecordService txRecordService;
	
	@RequestMapping("list")
	public String list(TxRecord txRecord,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		txRecord.setStartRow(starIndex);
		if (StringUtils.isEmpty(txRecord.getStartDate())) {
			txRecord.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(txRecord.getEndDate())) {
			txRecord.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String,Object> countMap = txRecordService.countTxRecord(txRecord);
		int rowTotal = Integer.valueOf(countMap.get("count")+"");
		List<TxRecord>  list =new ArrayList<TxRecord>();
		if(rowTotal>0){
			list = txRecordService.listTxRecordPage(txRecord);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("txRecord", txRecord);
		model.addAttribute("countMap", countMap);
		return "txRecord/list"; 
	}
}
