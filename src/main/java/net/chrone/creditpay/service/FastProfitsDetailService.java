package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.FastProfitsDetail;

/**
 * 快捷分润明细
 * @author huoliang
 *
 */
public interface FastProfitsDetailService {
	

	List<FastProfitsDetail> listFastProfitsDetail(String profitsId);
	
}
