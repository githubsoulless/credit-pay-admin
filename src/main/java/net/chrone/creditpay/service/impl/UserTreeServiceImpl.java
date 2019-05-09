package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
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
		List<UserTree> tempList = new ArrayList<>();
		for(int i=0;i<treeList.size();i++) {
			if((i+1)%500==0) {
				tempList.add(treeList.get(i));
				userTreeMapper.batchAdd(tempList);
				tempList = new ArrayList<>();
			}else {
				tempList.add(treeList.get(i));
			}
		}
		if(tempList.size()>0) {
			userTreeMapper.batchAdd(tempList);
		}
	}
}
