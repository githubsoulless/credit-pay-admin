package net.chrone.creditpay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static Date string2date(String timeStr,String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     * 取得当前日期的String�?
     * @return String 返回值为日期,格式自定义，�?��符合标准，参考Java Doc “SimpleDateFormat�?
     */
    public static String getCurrentDate(String aFormat) {
        String tDate = new SimpleDateFormat(aFormat).format(new java.util.Date(System.currentTimeMillis()));
        return tDate;
    }
    
    public static String getCurrentDate() {
        return DateUtils.getCurrentDate("yyyyMMdd");
    }
    public static String getCurrentTime() {
        return DateUtils.getCurrentDate("HHmmss");
    }
    public static String getCurrentDateTime() {
        return DateUtils.getCurrentDate("yyyyMMddHHmmss");
    }
    
    public static String getNewRandomCode(int codeLen) {
		//首先定义随机数据�?
		//根据�?��得到的数据码的长度返回随机字符串
		java.util.Random randomCode = new java.util.Random();
		String strCode = "";
		while (codeLen > 0) {
			int charCode=randomCode.nextInt(9);
			strCode += charCode;
			codeLen--;
		}
		return strCode;
	}
    
    /**
	 * 取得两个时间段的时间间隔
	 * 
	 * @author color
	 * @param t1
	 *            时间1
	 * @param t2
	 *            时间2
	 * @return t2 与t1的间隔天�?
	 * @throws ParseException
	 *             如果输入的日期格式不�?000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		int betweenDays = 0;
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(t1);
			d2 = format.parse(t2);
		} catch (ParseException e) {
			return -1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第�?��时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
	
	/**
	 *  获取跨度时间
	 * @param date 当前时间
	 * @param addArray 时间偏移量数组 field,amount
	 * @return
	 */
	public static Date addTime(Date date,int field,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	public static String formatDate(Date date,String aFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(aFormat);
		return sdf.format(date);
	}
	
	public static Date parseDate(String dateStr,String aFormat) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(aFormat);
		return sdf.parse(dateStr);
	}
	
	/**
	 * 获取距今日前N天的日期
	 */
	public static Date getLastDays(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, Integer.parseInt("-" + days));
		return c.getTime();
	}
	
	/**
	 * 获取日期月份差
	 * @param start yyyyMMdd
	 * @param end yyyyMMdd
	 * @return 相差月份数
	 */
	public static int getDateMonthBetween(String start, String end) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		Date d_start = null;
		Date d_end = null;
		try {
			d_start = formater.parse(start);
			d_end = formater.parse(end);
		} catch (ParseException e) {
			throw new IllegalArgumentException("参数格式有误：期望-yyyyMMdd，实际-" + start + "," + end);
		}
		Calendar c_start = Calendar.getInstance();
		Calendar c_end = (Calendar) c_start.clone();
		c_start.setTime(d_start);
		c_end.setTime(d_end);
		int y_start = c_start.get(Calendar.YEAR);
		int m_start = c_start.get(Calendar.MONTH);
		
		int y_end = c_end.get(Calendar.YEAR);
		int m_end = c_end.get(Calendar.MONTH);
		
		int t_month_s = y_start * 12 + m_start;
		int t_month_e = y_end * 12 + m_end;
		int temp = t_month_e - t_month_s;
		int result = temp;
		int day_start = c_start.get(Calendar.DAY_OF_MONTH);
		int day_end = c_end.get(Calendar.DAY_OF_MONTH);
		
		if(day_start > day_end){
			result = temp -1;
		}
		return result;
	}
	
	public static String wrapMonth(int m) {
		if(m >= 10){
			return m + "";
		}else{
			return "0" + m;
		}
	}
	
	/**
	 * 获取日期间有几个月份(最大为5,最小为2)
	 * @param start
	 * @param end
	 * @return
	 */
	public static String[] getMonthBetween(String start, String end) {
		String[] months = new String[5];
		int between = 3;//最多相差三月
		if(getDateMonthBetween(start, end) > between){
			return months;
		}
		String year_start = start.substring(0, 4);
		String year_end = end.substring(0, 4);
		String month_start = start.substring(4, 6);
		String month_end = end.substring(4, 6);
		int y_start = Integer.parseInt(year_start);
		int y_end = Integer.parseInt(year_end);
		int m_start = Integer.parseInt(month_start);
		int m_end = Integer.parseInt(month_end);
		//先获取上个月份
		int m_last = m_start - 1;
		m_last = m_last == 0 ? 12 : m_last;
		months[0] = wrapMonth(m_last);
		int temp = m_start;
		for (int i = 1; i < months.length; i++) {
			
			months[i] = wrapMonth(temp);
			//开始月份增加至等于结束月份
			temp++;
			if(temp > 12){
				temp = temp > 12 ? 1 : temp;
				y_start++;
			}
			if((y_start == y_end && temp > m_end) || (y_start > y_end && temp < m_end))
				break;
		}
		
		
		
//		for (int i = 0; i < months.length; i++) {

//			if (i == 0) {
//				int temp = m_start - 1;
//				temp = temp == 0 ? 12 : temp;
//				months[i] = wrapMonth(temp);
//				continue;
//			} else {
//				
//				for (int j = i-1; j <= between; j++) {
//					int temp = m_start + j;
//					if (temp > 12) {
//						temp = temp - 12;
//						if(y_start < y_end) y_start++;
//						if(m_end == 12){
//							months[i] = wrapMonth(temp);
//							return months;
//						}
//					}
//					if (temp <= m_end) {
//						
//						if(months[i] != null) break;
//						months[i] = wrapMonth(temp);
//					} else {
//						if(y_start == y_end)
//						return months;
//						months[i] = wrapMonth(temp);
//					}
//				}
//			}
			
			
//		}
		return months;
	}
	
	/**
	 * 获取配置时间类型
	 * @param timeout
	 * @return
	 */
	public static int getFieldByConfig(String timeout){
		if(timeout.endsWith("s")||timeout.endsWith("S")){//秒
			return Calendar.SECOND;
		}else if(timeout.endsWith("m")||timeout.endsWith("M")){//分
			return Calendar.MINUTE;
		}else if(timeout.endsWith("h")||timeout.endsWith("H")){//小时
			return Calendar.HOUR_OF_DAY;
		}else if(timeout.endsWith("d")||timeout.endsWith("D")){//天
			return Calendar.DAY_OF_MONTH;
		}
		return -1;
	}
	
	/**
	 * 根据传入的日期，获取相隔天数日期
	 * 
	 * @param date
	 * @param anyDay
	 *            可正可负
	 * @return
	 */
	public static Date getAnyDayByNo(Date date, int anyDay) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_WEEK, anyDay);
		return c.getTime();
	}
	
	/**
	 * 日期比较
	 * 
	 * @param date1
	 * @param date2
	 * @return -1:date1<date2 0:date1=date2 1:date1>date2
	 */
	public static int compareDate(Date date1, Date date2) {
		if (date1 == null) {
			return -2;
		}
		if (date2 == null) {
			return -2;
		}
		return date1.compareTo(date2);
	}
	
	/**
	 * 比较两个时间,若两个时间相等则返回 0 若 date1小于date2则返回-1  若 date1大于date2则返回1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int compareDate1(Date date1,Date date2) {
		
		String date1Str = formatDate(date1, "yyyy-MM-dd HH:mm:ss");
		String date2Str = formatDate(date2, "yyyy-MM-dd HH:mm:ss");
		
		if(date1Str.compareTo(date2Str) < 0) {//注册时间小于当前时间
			return -1;
		}else if(date1Str.compareTo(date2Str) > 0) {
			return 1;
		}else {
			return 0;
		}
	}
    
    public static void main(String[] args) {
    	try {
//			System.out.println(DateUtils.parseDate("00:00:00", "HH:mm:ss"));
//			System.out.println(formatDate(new Date(), "HH:mm:ss"));
//			System.err.println(DateUtils.string2date(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), 30), "yyyy-MM-dd") + " 23:59:59", DateUtils.PATTERN));
			System.out.println(DateUtils.parseDate("2017-12-31" + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(string2date("2012-04-12 00:00:00", PATTERN));
//    	LinkedHashMap<String, String> map = new LinkedHashMap<>();
//    	map.put("20140101", "20140130");
//    	map.put("20140102", "20140230");
//    	map.put("20140103", "20140330");
//    	map.put("20140104", "20140401");
//    	map.put("20141101", "20141130");
//    	map.put("20141102", "20141230");
//    	map.put("20141103", "20150130");
//    	map.put("20141104", "20150230");
//    	map.put("20141201", "20141211");
//    	map.put("20141202", "20150101");
//    	map.put("20141203", "20150201");
//    	map.put("20141204", "20150301");
//    	for (Entry<String, String> e : map.entrySet()) {
//			
//    		System.out.println("前：" + e.getKey() + " -- " + e.getValue());
//    		String[] strs = getMonthBetween(e.getKey(), e.getValue());
//    		System.out.println("后：");
//    		for (String string : strs) {
//    			System.out.println(string);
//			}
//		}
	}
}
