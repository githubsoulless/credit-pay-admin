package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.LevelOrder;

public interface LevelOrderService {

	Map<String, Object> getLevelOrderByPageCount(LevelOrder levelOrder);

	List<LevelOrder> getLevelOrderByPage(LevelOrder levelOrder);

}
