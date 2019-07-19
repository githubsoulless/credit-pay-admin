package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Information;

public interface InformationService {

	int getInformationByPageCount(Information information);

	List<Information> listInformationPage(Information information);

	Information getInformationByTitle(String title);

	void insert(Information information);

	int deleteByPrimaryKey(String infoId);

	Information getInformationByPrimaryKey(String infoId);

	int updateInformation(Information information);

	Information getInformationImg(String infoId);

}
