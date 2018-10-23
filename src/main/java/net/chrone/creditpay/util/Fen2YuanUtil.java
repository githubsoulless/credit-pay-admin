package net.chrone.creditpay.util;

import java.math.BigDecimal;

public class Fen2YuanUtil {
	
	/**
	 * 计算手续费<br>
	 * 内部实现为 amount*feeRate然后使用BigDecimal.ROUND_UP进行保留一位小数进位处理<br>
	 * 本方法实际上是用来<b>元转分</b>
	 * @param amount
	 * @param feeRate
	 * @return
	 */
	public static int caclFee(int amount,double feeRate){
		double fee = amount*feeRate;
		BigDecimal bigDecimal = new BigDecimal(fee);
		int intFee = bigDecimal.setScale(0, BigDecimal.ROUND_UP).intValue();
		return intFee;
	}
	public static void main(String[] args) {
		System.out.println(caclFee(1, 0.01));
	}
	
	public static String formatAmt(String fen){
		if(fen==null){
			return  "0.00";
		}
		fen = delZeroFromLeft(fen);
		
		String yuan = "";
		boolean isSigned = false;
		
		if(fen.length()>=2&&"-".equals(fen.substring(0, 1))){
			fen = fen.substring(1);
			isSigned = true;
		}
		if("".equals(fen)){
			return  "0.00";
		}else
		if(fen.length()==1){
			yuan = "0.0"+fen;
		}else
		if(fen.length()==2){
			yuan = "0."+fen;
		}
		else{
			yuan = fen.substring(0,fen.length()-2)+"."+fen.substring(fen.length()-2);
		}
		if(isSigned == true){
			return "-"+yuan;
		}else{
			return yuan;
		}
	}

	public static String delZeroFromLeft(String src){
			
			StringBuffer sb = new StringBuffer(src);
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				if(sb.charAt(0)=='0')
					sb.deleteCharAt(0);
				else
					return sb.toString();
			}
			return sb.toString();
		}

}
