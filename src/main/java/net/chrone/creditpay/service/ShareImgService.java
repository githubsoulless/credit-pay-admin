package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.ShareImg;

public interface ShareImgService {

	public void addShareImg(ShareImg shareImg);
	
	public void delShareImg(String fileName);
	
	public void updateShareImg(ShareImg shareImg);
	
	public List<ShareImg> getAllShareImg();
	
	public ShareImg getShareImg(String imgName);
	
}
