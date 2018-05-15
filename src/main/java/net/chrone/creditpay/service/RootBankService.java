package net.chrone.creditpay.service;

import net.chrone.creditpay.model.RootBank;

public interface RootBankService {
	RootBank getRootBankByBankNo(String bankNo);
}
