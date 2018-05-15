package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AccountDetailMapper;
import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.service.AccountDetailService;

/**
 * 
 * Title: AccountDetailServiceImpl 
 * Description: 账户明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class AccountDetailServiceImpl implements AccountDetailService {
	
	@Autowired
	private AccountDetailMapper accountDetailMapper;

	@Override
	public Map<String,Object> countAccountDetail(AccountDetail accountDetail) {
		return accountDetailMapper.countAccountDetail(accountDetail);
	}

	@Override
	public List<AccountDetail> listAccountDetailPage(AccountDetail accountDetail) {
		return accountDetailMapper.listAccountDetailPage(accountDetail);
	}

}
