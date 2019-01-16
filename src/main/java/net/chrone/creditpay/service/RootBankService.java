package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.RootBank;

public interface RootBankService {
	RootBank getRootBankByBankNo(String bankNo);
	
	List<RootBank> queryRootBankList();
}
