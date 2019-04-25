package net.chrone.creditpay.util;

public class Utils {
	
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
