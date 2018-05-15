package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.UpProfitsMapper;
import net.chrone.creditpay.model.UpProfits;
import net.chrone.creditpay.service.UpProfitsService;

/**
 * 
 * Title: UpProfitsServiceImpl 
 * Description: 升级分润明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class UpProfitsServiceImpl implements UpProfitsService {
	
	@Autowired
	private UpProfitsMapper upProfitsMapper;

	@Override
	public Map<String,Object> countUpProfits(UpProfits UpProfits) {
		return upProfitsMapper.countUpProfits(UpProfits);
	}

	@Override
	public List<UpProfits> listUpProfitsPage(UpProfits UpProfits) {
		return upProfitsMapper.listUpProfitsPage(UpProfits);
	}

	@Override
	public UpProfits getUpProfitsByProfitsId(String profitsId) {
		return upProfitsMapper.selectByPrimaryKey(profitsId);
	}

}
