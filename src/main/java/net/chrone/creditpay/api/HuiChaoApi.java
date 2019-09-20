package net.chrone.creditpay.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.chrone.creditpay.http.HttpClientHelper;
import net.chrone.creditpay.http.HttpResponse;
import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.util.ConfigReader;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyRSAUtils;
import net.chrone.creditpay.util.SignatureUtil;

public class HuiChaoApi {

	private static final Logger logger = Logger.getLogger(HuiChaoApi.class);

	/**
	 * 汇潮快捷代付
	 * 
	 * @param order
	 * @return
	 */
	public static Map<String, String> agentPay(FastOrder order, String orgId, String privateKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgId", orgId);
		map.put("reserved1", ConfigReader.getConfig("chronePayOrgId"));//代付orgid
		map.put("reserved2", order.getFee()+"");//代付手续费
		map.put("orgPayforSsn", order.getOrderNo());
		map.put("accountName", order.getCardName());
		map.put("destAmount", order.getAmount() + "");
		map.put("cardNo", order.getCardNo());
		map.put("certNo", order.getUserCertNo());
		map.put("pmsBankCd", order.getBankNo());
		map.put("payChannelId", order.getChannel());
		map.put("retryFlag", "1");
		String plainText = SignatureUtil.hex(map);
		map.put("signature", MyRSAUtils.sign(privateKey, plainText, MyRSAUtils.MD5_SIGN_ALGORITHM));
		try {
			List<String[]> headers = new ArrayList<>();
			headers.add(new String[]{"Content-Type", "application/json"});
			HttpResponse httpRes = HttpClientHelper.doHttp(ConfigReader.getConfig("chroneAgentPayUrl"),
					HttpClientHelper.POST,headers, "UTF-8", JSON.toJSONString(map), "60000");
			logger.info(httpRes.getRspStr());
			if (StringUtils.isNotEmpty(httpRes.getRspStr())) {
				return JSON.parseObject(httpRes.getRspStr(), new TypeReference<HashMap<String, String>>() {
				});
			}
		} catch (Exception e) {
			LogWriter.error("请求乾恩代付接口失败");
			e.printStackTrace();
		}
		return null;
	}

}
