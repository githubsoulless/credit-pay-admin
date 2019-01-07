package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.UserTreeMapper;
import net.chrone.creditpay.model.UserTree;
import net.chrone.creditpay.service.UserTreeService;
@Service
public class UserTreeServiceImpl implements UserTreeService {
	@Autowired
	private UserTreeMapper userTreeMapper;
	
	@Override
	public void batchAdd(List<UserTree> treeList) {
		userTreeMapper.batchAdd(treeList);
	}
}
