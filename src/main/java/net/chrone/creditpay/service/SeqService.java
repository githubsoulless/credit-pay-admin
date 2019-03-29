package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Seq;

public interface SeqService {
	String updateAndGetSequence(String id,int length);

	public List<Seq> getSeqList();
	public int update(Seq seq);
	
	
}
