package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.UserTree;

public interface UserTreeService {

	void batchAdd(List<UserTree> treeList);

}
