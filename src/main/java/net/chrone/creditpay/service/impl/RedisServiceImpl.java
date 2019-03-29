package net.chrone.creditpay.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.SeqMapper;
import net.chrone.creditpay.model.Seq;
import net.chrone.creditpay.service.RedisService;
import net.chrone.creditpay.util.ConfigReader;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.RedisClient;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private SeqMapper seqMapper;
	
	
	
	 @Override
	    public long genNextSeqNo(String id) {
	    	long num = 0;
	    	String key = RedisClient.CACHE_PREFIX_SEQ_NO_+id;
	    	if(!RedisClient.exisit(key)){
	    		int row = seqMapper.tickCountSeq(id);
	        	if (row==0) {
	        		Seq seq = new Seq();
	        		seq.setId(id);
	        		seq.setSeq(num);
	    		    seqMapper.insertSelective(seq);
	        		num = 0;
	        	} else {
	        		Seq seq = seqMapper.selectByPrimaryKey(id);
	        		num = seq.getSeq();
	        		if(num > 999999998){ //数据超长归零处理
	        			seq.setSeq(0L);
	        			seqMapper.updateByPrimaryKeySelective(seq);
	        		}
	        	}
	        	RedisClient.set(key, num+"");
	    	}else {//exist key
	    		num = RedisClient.inc(key);
	    	}
	    	
	    	return num;
	    }

}
