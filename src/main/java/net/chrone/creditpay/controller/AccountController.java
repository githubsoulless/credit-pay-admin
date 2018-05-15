package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.Account;
import net.chrone.creditpay.service.AccountService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;

@Controller
@RequestMapping("accountMgr")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 钱包实时统计
	 * @return
	 */
	@RequestMapping("accountRealTimeStatistics")
	public String accountRealTimeStatistics(Account account, String start, Model model){
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		account.setStartRow(starIndex);
		if(null == account.getOrderBy()){
			account.setOrderBy(1);
		}
		List<Account> list = new ArrayList<>();
		Map<String, Object> countMap = accountService.countAccount(account);
		int rowTotal = Integer.valueOf(countMap.get("count")+"");
		if(rowTotal > 0){
			list = accountService.listAccount(account);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("account", account);
		model.addAttribute("countMap", countMap);
		
		return "accountMgr/accountRealTimeStatistics";
	}

}
