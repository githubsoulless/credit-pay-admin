package net.chrone.creditpay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.chrone.creditpay.mapper.AccountDetailMapper;
import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;
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
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	@Autowired
	private SeqService seqService;

	@Override
	public Map<String, Object> countAccount(Account account) {
		return accountMapper.countAccount(account);
	}

	@Override
	public List<Account> listAccount(Account account) {
		return accountMapper.listAccount(account);
	}
	@Override
	public int updateAdjustByHand(int srcAmt, int transType, String memo, String srcUserId) {
		if(srcAmt==0){
			LogWriter.info("金额为0,不记账");
			return 0;
		}
		AppUser appUser = appUserMapper.selectByPrimaryKey(srcUserId);
		if(null == appUser){
			LogWriter.error("["+srcUserId+"]用户不存在");
		}
		Account account = accountMapper.selectByPrimaryKeyforUpdate(appUser.getOwnerId());
		LogWriter.debug("用户["+account.getOwnerId()+"],可用余额["+account.getAvailable()+"]");;
		if((account.getAvailable()+srcAmt)<0){
			throw new CHException("220","余额不足");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", appUser.getOwnerId());
		map.put("amt", srcAmt);
		map.put("rowUpdUsr", "system");
		int row = accountMapper.updateAdjust(map);
		if(row !=1){
			throw new CHException("500","系统异常");
		}
		//插入日志
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDtlId(seqService.updateAndGetSequence(SeqServiceImpl.T_ACCOUNT_DETAIL, 9));
		accountDetail.setBookDt(DateUtils.getCurrentDate());
		accountDetail.setOwnerId(account.getOwnerId());
		accountDetail.setTransType(transType);
		accountDetail.setAccountLogId(seqService.updateAndGetSequence(SeqServiceImpl.T_ACCOUNT_DETAIL, 9));//流水
		accountDetail.setSrcUserId(srcUserId);
		accountDetail.setMemo(memo);
		accountDetail.setRowCrtUsr("system");
		if(srcAmt>0){
			accountDetail.setTtlDebit(srcAmt);
			accountDetail.setAvalDebit(srcAmt);
			accountDetail.setDcFlag(0);
		}else{
			accountDetail.setDcFlag(1);
			accountDetail.setTtlCredit(Math.abs(srcAmt));
			accountDetail.setAvalCredit(Math.abs(srcAmt));
		}
		accountDetail.setTotal(account.getTotal()+srcAmt);
		accountDetail.setAvailable(account.getAvailable()+srcAmt);
		accountDetail.setFrozen(account.getFrozen());
		accountDetail.setUntransfer(account.getUntransfer());
		accountDetail.setRealBookInd(0);//实帐
		accountDetail.setBalanceType(0);
		accountDetail.setMemo(memo);
		int count = accountDetailMapper.insertSelective(accountDetail);
		return count;
	}

}
