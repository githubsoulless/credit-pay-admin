package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.CreditRootBank;

public interface CreditRootBankService {
	
	public List<CreditRootBank> geCreditRootBankAll();

	CreditRootBank getCreditRootBankByBankNo(String bankNo);
	
	public void updateCreditRootBank(CreditRootBank creditRootBank);


}
