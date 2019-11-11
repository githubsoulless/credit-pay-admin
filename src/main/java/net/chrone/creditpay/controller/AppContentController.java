package net.chrone.creditpay.controller;

import net.chrone.creditpay.model.*;
import net.chrone.creditpay.service.AppContentService;
import net.chrone.creditpay.service.MobileVersionService;
import net.chrone.creditpay.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.management.resources.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("appContent")
public class AppContentController extends BaseController{


    @Autowired
    private AppContentService appContentService;

    private String saveFilePath = PropertiseUtil.getDataFromPropertiseFile("pay", "bannerPath");

    @RequestMapping("detail")
    public String detail(Model model, HttpServletRequest request,String start,AppBanner appBanner) {
        AppContent appContent = appContentService.selectAppContentByContentId(1);
        int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
        appBanner.setStartRow(starIndex);
        appBanner.setPageSize(4);
        int rowTotal = appContentService.getBannerByCount(appBanner);
        List<AppBanner> list = new ArrayList<>();
        if (rowTotal > 0) {
            list = appContentService.getBannerByPage(appBanner);
        }
        MyPage page = new MyPage(list, starIndex, 4, rowTotal);
        model.addAttribute("appContent", appContent);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", starIndex);
        model.addAttribute("rowTotal", rowTotal);
        return "appContent/detail";
    }

    @RequestMapping("update")
    public String update(AppContent appContent,Model model, HttpServletRequest request) {
        int count = 0;
        count = appContentService.updateByAppContent(appContent);
        AppBanner appBanner = new AppBanner();
        int starIndex = 0;
        appBanner.setStartRow(starIndex);
        appBanner.setPageSize(4);
        int rowTotal = appContentService.getBannerByCount(appBanner);
        List<AppBanner> list = new ArrayList<>();
        if (rowTotal > 0) {
            list = appContentService.getBannerByPage(appBanner);
        }
        
        if(count > 0){
            AppContent updateAppContent = appContentService.selectAppContentByContentId(1);
            model.addAttribute("appContent", appContent);
            model.addAttribute("list", list);
        }
        return "appContent/detail";
    }

    @RequestMapping("toUpload")
    public String toUpload() {
        return "appContent/upload";
    }


    @RequestMapping("upload")
    public String upload(Model model, HttpServletRequest request, HttpServletResponse response, AppBanner appBanner, @RequestParam("banner") MultipartFile file) {
        Map<String,String> respMap = new HashMap<>();
        System.out.println(appBanner.getBannerTitle());
        System.out.println(appBanner.getBannerUrl());

        MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
        String message = "success";

        try {
            if(file != null ) {
                AppBanner appBanner1 = new AppBanner();
                long fileSize = file.getSize();
                String oFileName = file.getOriginalFilename();
                String suffxi = oFileName.substring(oFileName.lastIndexOf("."));
                String fileName = new IdGen().nextId() + suffxi;
                if(fileName.endsWith("jpg") ||fileName.endsWith("jpeg") || fileName.endsWith("png")) {
                    File newFile = new File(saveFilePath + fileName);
                    if (!newFile.exists()) { // 文件夹
                        newFile.getParentFile().mkdirs();
                        newFile.createNewFile();
                    }
                    // 将内存中的数据写入磁盘
                    file.transferTo(newFile);
                    appBanner1.setBannerId(new IdGen().nextId());
                    appBanner1.setBannerName(fileName);
                    appBanner1.setBannerPath(saveFilePath);
                    appBanner1.setBannerTitle(appBanner.getBannerTitle());
                    appBanner1.setBannerUrl(appBanner.getBannerUrl());
                    appBanner1.setCreateTime(new Date());
                    appContentService.addAppBanner(appBanner1);
                    }else {
                        message = "上传文件列表中包含不支持的文件格式,部分文件上传失败";
                    }
                }else{
                    message = "请选择文件";
                }
        } catch (IOException e) {
            e.printStackTrace();
            message = "文件上传失败";
        }

        model.addAttribute("message",message);
        return "appContent/upload";
    }

    @RequestMapping("delete")
    public void delete(String bannerId, HttpServletRequest request,HttpServletResponse response) {
        Map<String,String> respMap = new HashMap<>();
        if(!StringUtil.isEmpty(bannerId)) {
            appContentService.delAppBanner(bannerId);
            respMap.put("code","200");
            respMap.put("msg","操作成功");
        }else {
            respMap.put("code","500");
            respMap.put("msg","参数有误");
        }

        response(respMap, response);


    }

    @RequestMapping("toUpdateAppBanner")
    public String toUpdateAppBanner(String bannerId, HttpServletRequest request,HttpServletResponse response,Model model) {
        AppBanner appBanner = appContentService.selectByAppBannerId(bannerId);
        model.addAttribute("appBanner", appBanner);
        return "appContent/toUpdateAppBanner";
    }

    @RequestMapping("updateAppBanner")
    public String updateAppBanner(AppBanner appBanner, HttpServletRequest request,HttpServletResponse response,Model model) {
        String message = "";
        int count = 0;
        count = appContentService.updateByAppBanner(appBanner);
        if(count > 0){
            message = "success";
        }
        else{
            message = "系统异常";
        }
        model.addAttribute("message", message);
        return "appContent/toUpdateAppBanner";

    }
}


