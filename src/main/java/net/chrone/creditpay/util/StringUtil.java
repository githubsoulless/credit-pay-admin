package net.chrone.creditpay.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class StringUtil {

	private static Logger logger = Logger.getLogger(StringUtil.class);
	/**
	 * 字符串右侧填充指定字符
	 * @param value
	 * @param totalLen填充后长度
	 * @param fillChar
	 * @return
	 */
	public static String rightFill(String value, int totalLen, char fillChar) {
		int fillLen = totalLen-value.length();
		if (fillLen<=0) return value;
		StringBuffer sb = new StringBuffer();
		sb.append(value);
		for (int i=0;i<fillLen;i++) {
			sb.append(fillChar);
		}
		return sb.toString();
	}
	
	public static String rightFillChina(String value, int totalLen, char fillChar) {
		int fillLen = totalLen-string_length(value);
		if (fillLen<=0) return value;
		StringBuffer sb = new StringBuffer();
		sb.append(value);
		for (int i=0;i<fillLen;i++) {
			sb.append(fillChar);
		}
		return sb.toString();
	}
	
	public static int string_length(String value) {
		  int valueLength = 0;
		  String chinese = "[\u4e00-\u9fa5]";
		  for (int i = 0; i < value.length(); i++) {
		   String temp = value.substring(i, i + 1);
		   if (temp.matches(chinese)) {
		    valueLength += 2;
		   } else {
		    valueLength += 1;
		   }
		  }
		  return valueLength;
	}
	/**
	 * 字符串左侧填充指定字符
	 * @param value
	 * @param totalLen
	 * @param Char
	 * @return
	 */
	public static String leftFill(String value,int totalLen,char fillChar){
		int fillLen = totalLen-value.length();
		if (fillLen<=0) return value;
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<fillLen;i++) {
			sb.append(fillChar);
		}
		sb.append(value);
		return sb.toString();
	}

	/**
	 * 格式化日期字符串
	 */
	@SuppressWarnings("deprecation")
	public static String getDateToFormat(String format,String dateStr){
		String formatStr = new SimpleDateFormat(format).format(new Date(dateStr));
		return formatStr;
	}
	/**
	 * 2012-5-29下午4:59:39 
	 * @Description: 判断字符串等于空 
	 * @param str
	 * @return boolean    返回类型 
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	public static boolean strIsEmpty(String str) {
		return (null == str || "".equals(str.trim()) );
	}
	public static boolean isNotEmpty( String str ) {
		return (null != str &&  str.trim().length() > 0  ) || !strIsEmpty(str);
	}
	/**
	 * 2012-6-29上午10:27:07 
	 * @Title: MapRemoveKey 
	 * @Description: 屏蔽敏感信息
	 * @param map
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public  static  String RemoveSensitiveInfo(Map<String,String> map){
		JSONObject obj = JSONObject.fromObject(map);
//		logger.info("obj: "+ obj.toString());
		String retStr = "";
		if(obj.containsKey("msgType") && !"".equals(obj.getString("msgType")) 
					&& ((String)obj.get("msgType")).equals("2006")){    //信用卡还款消息类型
			String s  =  formatCard((String) obj.get("billNo"));     //信用卡还款时，billNo为信用卡卡号
			obj.put("billNo", s);
//			obj.remove("msgType");
		}
		if(obj.containsKey("transferAccount") &&  !"".equals(obj.get("transferAccount"))){
			String s  =  formatCard((String)obj.get("transferAccount"));
			obj.put("transferAccount", s);
		}
		if(obj.containsKey("pAccount") && !"".equals(obj.get("pAccount"))){
			logger.info("[主账号已处理]");
			String s  =  formatCard((String)obj.get("pAccount"));
			obj.put("pAccount", s);
		}
		if(obj.containsKey("personIdData") &&  !"".equals(obj.get("personIdData"))){
			obj.remove("personIdData");
			logger.info("[个人标识信息已屏蔽]");
		}
		if(obj.containsKey("cvn2") &&  !"".equals(obj.get("cvn2"))){
			obj.remove("cvn2");
			logger.info("[cvn2已屏蔽]");
		}
		if(obj.containsKey("expiDate") &&  !"".equals(obj.get("expiDate"))){
			obj.remove("expiDate");
			logger.info("[expiDate已屏蔽]");
		}
		if(obj.containsKey("track2")  && !"".equals(obj.get("track2"))){
			obj.remove("track2");
			logger.info("[2磁道已屏蔽]");
		}
		if(obj.containsKey("track3") &&  !"".equals( obj.get("track3"))){
			obj.remove("track3");
			logger.info("[3磁道已屏蔽]");
		}
		retStr += obj;
		return retStr;
	}
	private  static String formatCard(String card){
		StringBuffer s = new StringBuffer();
		String a = card.substring(0,6);
		String b = card.substring(a.length(),card.length()-4);
		for(char c: b.toCharArray()){
			b = b.replace(c, '*');
		}
		String c = card.substring(card.length()-4,card.length());
		s.append(a).append(b).append(c);
		return s.toString();
	}
	
	public static String hiddenCard(String cardNo,int start,int end){
		if(StringUtils.isEmpty(cardNo)){
			return "";	
		}
		StringBuffer sb = new StringBuffer();
		String headStr = cardNo.substring(0, start);
		String footerStr = cardNo.substring(cardNo.length()-end,cardNo.length());
		for(int i=0;i<(cardNo.length()-start-end);i++){
			sb.append("*");
		}
		return headStr+sb.toString()+footerStr;
	}
	
	/**
	 * 获取6位的随机数
	 * 
	 * @return
	 */
	public static String getRandom6() {
		StringBuffer str = new StringBuffer();
		int[] intRet = new int[6];
		int intRd = 0; // 存放随机数
		int count = 0; // 记录生成的随机数个数
		int flag = 0; // 是否已经生成过标志
		while (count < 6) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 9 + 1;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				str.append(intRd);
				count++;
			}
		}
		return str.toString();
	}
	
	public static String getRandom8() {
		StringBuffer str = new StringBuffer();
		int[] intRet = new int[8];
		int intRd = 0; // 存放随机数
		int count = 0; // 记录生成的随机数个数
		int flag = 0; // 是否已经生成过标志
		while (count < 8) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 9 + 1;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				str.append(intRd);
				count++;
			}
		}
		return str.toString();
	}
	
	/**
	  * 判断是否是手机号
	  * @param mobleNumber
	  * @return
	  */
	 public static boolean isMobleNumber(String mobleNumber) {
		Pattern moble = Pattern.compile("^(1[3,4,5,8,9][0-9])\\d{8}$");
		if (mobleNumber == null || mobleNumber.trim().length() == 0)
          return false;
      return moble.matcher(mobleNumber).matches();
  }
	 /**
	  * 判断是否是邮箱
	  * @param email
	  * @return
	  */
	 public static boolean isEmail(String email) {
			Pattern moble = Pattern.compile("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$");
			if (email == null || email.trim().length() == 0)
	            return false;
	        return moble.matcher(email).matches();
	    }
	 public static boolean validateNum(String number){
			String regex = "^[0-9?]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(number);
			return matcher.matches();
		}
	 /*得到现在时间*/
		public static Date getNow() {
			return new Date();
		}
		public static Date getAnyDayByNo(Date date, int anyDay) {
			if (date == null) {
				return null;
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_WEEK, anyDay);
			return c.getTime();
		}
		public static String dateToStrLong(Date strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formatter.format(strDate);
		}
		public static String dateToStr(Date strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			return formatter.format(strDate);
		}
		public static boolean isMatchUmpayRefundNo(String str){
			 if(str.length()!=16){
				 return false;
			 }else{
				 String date = str.substring(0,str.length()-4);
				 SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				 try {
					Date refundno = sdf.parse(date);
					String strDate=sdf.format(refundno);
					if(!str.equals(strDate)){
						return false;
					}
					return true;
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
			 }
		 }
		/**
		 * 获取全球唯一字符串
		 * @return String
		 * @author zhangLei
		 */
		public static String getRandomUUID() {
			return UUID.randomUUID().toString().replaceAll("-", "");
		}
		/**
		 * 将date时间类型转成年月日字符串
		 * @param dateDate
		 * @return
		 */
		public static String getDateToStringNYR(Date dateDate){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(dateDate);
		}
		public static String getDateToStringShort(Date dateDate){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			return formatter.format(dateDate);
		}
		public static String format(Date date,String format){
			String formatStr = new SimpleDateFormat(format).format(date);
			return formatStr;
		}
		
		public static boolean isSameDate(Date d1,Date d2){
			if(getDateToStringShort(d1).equals(getDateToStringShort(d2))){
				return true;
			}
			return false;
		} 
		
		// 随机生成字符串(自定义长度)
		public static String getRandomString(int length) {
			String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuffer generateRandStr = new StringBuffer();
			Random rand = new Random();
			for (int i = 0; i < length; i++) {
				int randNum = rand.nextInt(36);
				generateRandStr.append(radStr.substring(randNum, randNum + 1));
			}
			return generateRandStr + "";
		}
	public static void main(String[] args) {
		System.out.println(getRandom8());
//		String a="026210676802116170599         孙煌                                                        102290017581中国工商银行豫园支行                                                 ";
//		System.out.println(subStr(a, 2, 30));
//		System.out.println(subStr(a, 30, 90));
//		System.out.println(subStr(a, 90, 102));
//		System.out.println(subStr(a, 102, 162));
//		System.out.println(subStr(a, 162, 162+9));
		
	}
    //截取带中文的字符串
    public static String subStr(String str, int beginLen, int endLen) {
         String value = "";
         try {
              value = new String(str.getBytes("gbk"), "iso-8859-1");
         } catch (UnsupportedEncodingException ex) {
        	  logger.error("字符长度处理|不支持的编码");
              return null;
         }
         value = value.substring(beginLen, endLen);
         try {
              return new String(value.getBytes("iso-8859-1"), "gbk");
         } catch (UnsupportedEncodingException ex) {
        	  logger.error("字符长度处理|不支持的编码");
              return null;
         }
    }
}
