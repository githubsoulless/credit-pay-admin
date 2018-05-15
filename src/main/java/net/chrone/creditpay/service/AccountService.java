package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.Account;

/**
 * 
 * Title: AccountService 
 * Description: 钱包管理 
 * @author huoliang
 * @data 2017年12月1日 上午11:44:09
 *
 */
public interface AccountService {
	
	Map<String, Object> countAccount(Account account);
	//钱包实时统计
	List<Account> listAccount(Account account);

}
