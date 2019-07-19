package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Guide;

public interface GuideService {

	int getGuideByPageCount(Guide guide);

	List<Guide> listGuidePage(Guide guide);

	Guide getGuideByTitle(String title);

	void insert(Guide guide);

	int deleteByPrimaryKey(String guideId);

	int updateGuide(Guide guide);

	Guide getGuideByPrimaryKey(String guideId);

	Guide getGuideImg(String guideId);
}
