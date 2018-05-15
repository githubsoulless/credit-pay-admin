package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AccountMapper;
import net.chrone.creditpay.model.Account;
import net.chrone.creditpay.service.AccountService;

/**
 * 
 * Title: AccountServiceImpl 
 * Description: 钱包管理 
 * @author huoliang
 * @data 2017年12月1日 下午2:36:23
 *
 */
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Map<String, Object> countAccount(Account account) {
		return accountMapper.countAccount(account);
	}

	@Override
	public List<Account> listAccount(Account account) {
		return accountMapper.listAccount(account);
	}

}
