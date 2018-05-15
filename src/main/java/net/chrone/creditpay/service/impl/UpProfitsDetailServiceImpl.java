package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.UpProfitsDetailMapper;
import net.chrone.creditpay.model.UpProfitsDetail;
import net.chrone.creditpay.service.UpProfitsDetailService;

/**
 * 
 * Title: UpProfitsDetailServiceImpl 
 * Description: 分润明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class UpProfitsDetailServiceImpl implements UpProfitsDetailService {
	
	@Autowired
	private UpProfitsDetailMapper upProfitsDetailMapper;

	@Override
	public List<UpProfitsDetail> listUpProfitsDetail(String profitsId) {
		return upProfitsDetailMapper.listUpProfitsDetail(profitsId);
	}


}
