package net.chrone.creditpay.service;

public interface UserAwardStatusService {
	
	/**
	 * 重置用户奖励状态 0注册  1快捷 2完美
	 * @param type
	 */
	public void resetUserStatus(int type);
}
