package net.chrone.creditpay.util;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	
	
	/**
	 * 
	 * @param cardNo
	 * @param start 开始显示位数
	 * @param end 结束显示位数
	 * @return
	 */
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
	 * 只显示姓，隐藏名
	 * @param name
	 * @return
	 */
	public static String hiddenName(String name) {
		if(StringUtils.isEmpty(name))
			return "";
		String suffix = "";
		for(int i=0;i<name.length()-1;i++) {
			suffix +="X";
		}
		return name.substring(0, 1)+suffix;
		
	}
	
	/**
	 * 计算后扣手续费金额,最终返回手续费和消费金额总和
	 * @param planSumAmt
	 * @param fee
	 * @return 手续费+总金额
	 */
	public static int calcPlanAmt(int planSumAmt,double fee) {
		int planFee = Fen2YuanUtil.caclFee(planSumAmt,fee);//计划总手续费
		int sumPlanAmt = planSumAmt+Fen2YuanUtil.caclFee(planSumAmt+planFee,fee);//计划金额+计划手续费
		int sumPlanFee = Fen2YuanUtil.caclFee(sumPlanAmt,fee);//最终通道收取手续费
		return planSumAmt+sumPlanFee;
	}
	
	/**
	 * 计算后扣手续费,只返回手续费
	 * @param taskAmount
	 * @param fee
	 * @return
	 */
	public static int calcPlanTaskFee(int taskAmount,double fee) {
		int planFee = Fen2YuanUtil.caclFee(taskAmount,fee);//计划总手续费
		int sumPlanAmt = taskAmount+Fen2YuanUtil.caclFee(taskAmount+planFee,fee);//计划金额+计划手续费
		int sumPlanFee = Fen2YuanUtil.caclFee(sumPlanAmt,fee);//最终通道收取手续费
		return sumPlanFee;
	}
	
	
}
