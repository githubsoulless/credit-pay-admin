package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.LevelOrderMapper;
import net.chrone.creditpay.model.LevelOrder;
import net.chrone.creditpay.service.LevelOrderService;

@Service
public class LevelOrderServiceImpl implements LevelOrderService {
	
	@Autowired
	private LevelOrderMapper levelOrderMapper;

	@Override
	public Map<String, Object> getLevelOrderByPageCount(LevelOrder levelOrder) {
		return levelOrderMapper.getLevelOrderByPageCount(levelOrder);
	}

	@Override
	public List<LevelOrder> getLevelOrderByPage(LevelOrder levelOrder) {
		return levelOrderMapper.getLevelOrderByPage(levelOrder);
	}

}
