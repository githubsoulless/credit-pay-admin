package net.chrone.creditpay.service;

import java.util.Map;

import net.chrone.creditpay.model.SysParam;
import net.chrone.creditpay.model.SysParamDTO;

/**
 * 
 * Title: SysParamService Description: 平台参数设置
 * 
 * @author huoliang
 * @data 2017年11月22日 上午11:55:28
 *
 */
public interface SysParamService {

	Map<String, String> listSysParam();

	void updateSysParam(SysParamDTO sysParamDTO);

	SysParam getSysParam(String name);
}
