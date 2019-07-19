package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.InformationMapper;
import net.chrone.creditpay.model.Information;
import net.chrone.creditpay.model.InformationExample;
import net.chrone.creditpay.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService{

	@Autowired
	private InformationMapper informationMapper;

	@Override
	public int getInformationByPageCount(Information information) {
		// TODO Auto-generated method stub
		return informationMapper.getInformationByPageCount(information);
	}

	@Override
	public List<Information> listInformationPage(Information information) {
		// TODO Auto-generated method stub
		return informationMapper.listInformationPage(information);
	}

	@Override
	public Information getInformationByTitle(String title) {
		// TODO Auto-generated method stub
		InformationExample example = new InformationExample();
		example.createCriteria().andTitleEqualTo(title);
		List<Information> list = informationMapper.selectByExample(example);
		if(list.size() == 0){
	    	return null;
	    }
		return list.get(0);
	}

	@Override
	public void insert(Information information) {
		// TODO Auto-generated method stub
		informationMapper.insert(information);
	}

	@Override
	public int deleteByPrimaryKey(String infoId) {
		// TODO Auto-generated method stub
		return informationMapper.deleteByPrimaryKey(infoId);
	}

	@Override
	public Information getInformationByPrimaryKey(String infoId) {
		// TODO Auto-generated method stub
		return informationMapper.selectByPrimaryKey(infoId);
	}

	@Override
	public int updateInformation(Information information) {
		// TODO Auto-generated method stub
		return informationMapper.updateByPrimaryKeySelective(information);
	}

	@Override
	public Information getInformationImg(String infoId) {
		// TODO Auto-generated method stub
		return informationMapper.selectByPrimaryKey(infoId);
	}
}
