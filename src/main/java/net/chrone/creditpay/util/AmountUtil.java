package net.chrone.creditpay.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtil {
	/**
	 * 将元字符串转换为分
	 * 
	 * @param amountStr
	 * @return
	 */
	public static long parseAmountStr2Long(String amountStr) {
		if (amountStr == null || "".equals(amountStr)) {
			return 0L;
		}
		double amount = Double.parseDouble(amountStr);
		Double db = amount * 100;
		DecimalFormat df = new DecimalFormat("#");
		String s = df.format(db);
		return Long.parseLong(s);
	}

	/**
	 * 将分转换为元
	 * 
	 * @param amountStr
	 * @return
	 */
	public static String parseAmountLong2Str(Long amountLong) {
		if (amountLong == null) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double d = amountLong / 100d;
		String s = df.format(d);
		return s;
	}

	/**
	 * 将元转换为分
	 * 
	 * @param yuan
	 * @return
	 */
	public static Long yuan2Fen(Double yuan) {
		Double dFen = yuan * 100;
		Long lFen = dFen.longValue();
		return lFen;
	}

	/**
	 * 将分转换为元(四舍五入到分)
	 * 
	 * @param fen
	 * @return
	 */
	public static Double fen2Yuan(Long fen) {
		Double yuan = fen / 100.00;
		BigDecimal big = new BigDecimal(fen / 100.00);
		yuan = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return yuan;
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 除法运算
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 小数点后精确度位数
	 * @param roundingMode舍入模式 0:大于1即进位，1:任何值都舍去，4：>=5进一，5：>5进一
	 * 具体参见BigDecimal
	 * @return
	 */
	public static double div(double v1, double v2, int scale,int roundingMode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, roundingMode).doubleValue();
	}

	/**
	 * 去掉小数点
	 */
	public static String spitStr(String amountStr) {
		int index = amountStr.indexOf(".");
		if (index != -1) {
			amountStr = amountStr.substring(0, index);
		}
		return amountStr;
	}
	
	public static void main(String[] args) {
//		BigDecimal b1 = new BigDecimal(Double.toString(1.50));
//		BigDecimal b2 = new BigDecimal(Double.toString(1));
//		
//		System.out.println(b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).doubleValue());
//		System.out.println(b1.divide(b2, 0, BigDecimal.ROUND_HALF_DOWN).doubleValue());
//		System.out.println(b1.divide(b2, 0, BigDecimal.ROUND_UP).doubleValue());
//		System.out.println(b1.divide(b2, 0, BigDecimal.ROUND_DOWN).doubleValue());
		System.out.println(parseAmountLong2Str(220L));
	}
}
