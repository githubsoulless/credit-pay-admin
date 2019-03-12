package net.chrone.creditpay.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.chrone.creditpay.mapper.UserAwardStatusMapper;
import net.chrone.creditpay.model.UserAwardStatus;
import net.chrone.creditpay.model.UserAwardStatusExample;
import net.chrone.creditpay.service.UserAwardStatusService;

@Service
public class UserAwardStatusServiceImpl implements UserAwardStatusService {

	@Autowired
	private UserAwardStatusMapper userAwardStatusMapper;
	
	@Override
	public void resetUserStatus(int type) {
		
		userAwardStatusMapper.resetUserStatus(type);
		
	}

}
