package net.chrone.creditpay.service;

import net.chrone.creditpay.model.AppBanner;
import net.chrone.creditpay.model.AppContent;

import java.util.List;

public interface AppContentService {
    AppContent selectAppContentByContentId(int i);

    int updateByAppContent(AppContent appContent);

    void addAppBanner(AppBanner appBanner1);

    int getBannerByCount(AppBanner appBanner);


    List<AppBanner> getBannerByPage(AppBanner appBanner);

    AppBanner getAppBannerByBannerName(String bannerName);

    void delAppBanner(String bannerId);


    int updateByAppBanner(AppBanner appBanner);

    AppBanner selectByAppBanner(AppBanner appBanner);


    AppBanner selectByAppBannerId(String bannerId);
}
