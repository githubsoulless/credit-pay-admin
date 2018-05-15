package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.SmsWarning;
import net.chrone.creditpay.model.SmsWarningDTO;

public interface SmsWarningService {

	List<SmsWarning> getSmsWarningList();

	void update(SmsWarningDTO smsWarningDTO);

}
