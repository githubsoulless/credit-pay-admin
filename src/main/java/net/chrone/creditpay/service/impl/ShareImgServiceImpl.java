package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.ShareImgMapper;
import net.chrone.creditpay.model.ShareImg;
import net.chrone.creditpay.model.ShareImgExample;
import net.chrone.creditpay.service.ShareImgService;
@Service
public class ShareImgServiceImpl implements ShareImgService {

	@Autowired
	private ShareImgMapper shareImgMapper;
	
	@Override
	public void addShareImg(ShareImg shareImg) {
		shareImgMapper.insert(shareImg);

	}

	@Override
	public void delShareImg(String fileName) {
		shareImgMapper.deleteByPrimaryKey(fileName);
	}

	@Override
	public void updateShareImg(ShareImg shareImg) {
	
		shareImgMapper.updateByPrimaryKey(shareImg);
	}
	
	@Override
	public List<ShareImg> getAllShareImg() {
	
		ShareImgExample shareImgExample = new ShareImgExample();
		shareImgExample.setOrderByClause("img_use desc,img_sort desc,create_time desc");
		return shareImgMapper.selectByExample(shareImgExample);
		
	}
	
	@Override
	public ShareImg getShareImg(String imgName) {
		
		return shareImgMapper.selectByPrimaryKey(imgName);
	}

}
