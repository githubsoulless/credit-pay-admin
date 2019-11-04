package net.chrone.creditpay.service.impl;

import net.chrone.creditpay.mapper.AppBannerMapper;
import net.chrone.creditpay.mapper.AppContentMapper;
import net.chrone.creditpay.model.*;
import net.chrone.creditpay.service.AppContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppContentServiceImpl implements AppContentService {

    @Autowired
    private AppContentMapper appContentMapper;
    @Autowired
    private AppBannerMapper appBannerMapper;

    @Override
    public AppContent selectAppContentByContentId(int i) {
        return appContentMapper.selectByPrimaryKey(1);
    }

    @Override
    public int updateByAppContent(AppContent appContent) {
        AppContentExample appContentExample = new AppContentExample();
        appContentExample.createCriteria().andContentIdEqualTo(1);
        return appContentMapper.updateByExampleSelective(appContent, appContentExample);
    }

    @Override
    public void addAppBanner(AppBanner appBanner1) {
        appBannerMapper.insert(appBanner1);
    }

    @Override
    public int getBannerByCount(AppBanner appBanner) {
        return appBannerMapper.countByExample(null);
    }

    @Override
    public List<AppBanner> getBannerByPage(AppBanner appBanner) {
        return appBannerMapper.selectBannerByPage(appBanner);
    }

    @Override
    public AppBanner getAppBannerByBannerName(String bannerName) {
        return appBannerMapper.selectBannerByBannerName(bannerName);
    }

    @Override
    public void delAppBanner(String bannerId) {
        appBannerMapper.deleteByPrimaryKey(bannerId);
    }

    @Override
    public int updateByAppBanner(AppBanner appBanner) {
        return appBannerMapper.updateByPrimaryKeySelective(appBanner);
    }

    @Override
    public AppBanner selectByAppBanner(AppBanner appBanner) {
        return appBannerMapper.selectByPrimaryKey(appBanner.getBannerId());

    }

    @Override
    public AppBanner selectByAppBannerId(String bannerId) {
        return appBannerMapper.selectByPrimaryKey(bannerId);
    }


}
