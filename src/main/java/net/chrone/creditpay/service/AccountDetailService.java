package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.AccountDetail;

/**
 * 
 * Title: AccountDetailService 
 * Description: 账户明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:19:31
 *
 */
public interface AccountDetailService {
	
	Map<String,Object> countAccountDetail(AccountDetail accountDetail);

	List<AccountDetail> listAccountDetailPage(AccountDetail accountDetail);
	
}
