package net.chrone.creditpay.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



/**
 * 签名工具类
 * @author Jerry
 * @date 2015.08.13
 */
public class SignatureUtil {
	
	private static Logger logger = Logger.getLogger(SignatureUtil.class);
	
	public static String assmeblyPlainText(Object bean){
		if(null==bean)
			return "";
		Map<String,String> map = new HashMap<String, String>();
		for(Method method:bean.getClass().getMethods()){
			try {
				if(!method.getName().startsWith("get") || "getClass".equalsIgnoreCase(method.getName()))
					continue ; 
				Object o = method.invoke(bean, null);
				if(o!=null&&StringUtils.isNotEmpty(o.toString()) && !"getSignature".equalsIgnoreCase(method.getName().toLowerCase())){
					String feildName = method.getName().substring(3,4).toLowerCase()+method.getName().substring(4);
					if(o instanceof Date){
						map.put(feildName, DateUtils.formatDate((Date)o, "yyyyMMddHHmmss"));//所有日期类型的统一转换成yyyyMMddHHmmss
					}else{
						map.put(feildName, o.toString());
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return hex(map);
	}
	
	/**
	 * 排序并组装签名明文串
	 * @param map
	 * @return
	 */
	public static String hex(Map<String,String> map){
		String[] strs = new String[map.size()];
		map.keySet().toArray(strs);
		Arrays.sort(strs);
		StringBuffer source = new StringBuffer();
		for(String str:strs){
			if(StringUtils.isEmpty(map.get(str))){
				continue;
			}
			source.append(str+"="+map.get(str)+"&");
		}
		String bigstr = source.substring(0,source.length()-1);
		logger.info("sign bigstr="+bigstr);
		return bigstr;
	}
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("IMEI=869612020169662&busiNo=108&endDate=20171231&startDate=20171201&system=0&userId=18600300001"));
	}
}