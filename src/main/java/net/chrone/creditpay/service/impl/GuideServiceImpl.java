package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.GuideMapper;
import net.chrone.creditpay.model.Guide;
import net.chrone.creditpay.model.GuideExample;
import net.chrone.creditpay.service.GuideService;

@Service
public class GuideServiceImpl implements GuideService {

	@Autowired
	private GuideMapper guideMapper;

	@Override
	public int getGuideByPageCount(Guide guide) {
		// TODO Auto-generated method stub
		return guideMapper.getGuideByPageCount(guide);
	}

	@Override
	public List<Guide> listGuidePage(Guide guide) {
		// TODO Auto-generated method stub
		return guideMapper.listGuidePage(guide);
	}

	@Override
	public Guide getGuideByTitle(String title) {
		// TODO Auto-generated method stub
		GuideExample example = new GuideExample();
		example.createCriteria().andTitleEqualTo(title);
		List<Guide> list = guideMapper.selectByExample(example);
		if(list.size() == 0){
	    	return null;
	    }
		return list.get(0);
	}

	@Override
	public void insert(Guide guide) {
		// TODO Auto-generated method stub
		guideMapper.insert(guide);
	}

	@Override
	public int deleteByPrimaryKey(String guideId) {
		// TODO Auto-generated method stub
		return guideMapper.deleteByPrimaryKey(guideId);
	}

	@Override
	public int updateGuide(Guide guide) {
		// TODO Auto-generated method stub
		return guideMapper.updateByPrimaryKeySelective(guide);
	}

	@Override
	public Guide getGuideByPrimaryKey(String guideId) {
		// TODO Auto-generated method stub
		return guideMapper.selectByPrimaryKey(guideId);
	}

	@Override
	public Guide getGuideImg(String guideId) {
		// TODO Auto-generated method stub
		return guideMapper.selectByPrimaryKey(guideId);
	}
}
