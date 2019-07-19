package net.chrone.creditpay.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.Guide;
import net.chrone.creditpay.model.Information;
import net.chrone.creditpay.model.ShareImg;
import net.chrone.creditpay.service.GuideService;
import net.chrone.creditpay.service.InformationService;
import net.chrone.creditpay.service.ShareImgService;

@Controller
@RequestMapping("download")
public class DownloadShareImgController {
	
	@Autowired
	private ShareImgService shareImgService;
	
	@Autowired
	private GuideService guideService;
	
	@Autowired
	private InformationService informationService;
	
	@RequestMapping("shareImg/{imgName}.{suffix}")
	public void download(@PathVariable("imgName") String imgName, @PathVariable("suffix") String suffix,Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
        	
        	ShareImg shareImg = shareImgService.getShareImg(imgName+"."+suffix);
        	if(shareImg != null) {
        		
        		 File file = new File(shareImg.getImgPath()+shareImg.getImgName());
                 // 以流的形式下载文件。
                 InputStream fis = new BufferedInputStream(new FileInputStream(file));
                 byte[] buffer = new byte[fis.available()];
                 fis.read(buffer);
                 fis.close();
                 response.reset();
                // response.addHeader("Content-Disposition", "attachment;filename=" + new String(shareImg.getImgName()));//下载文件使用
                 //response.setContentType("application/octet-stream"); //下载文件使用
                 if(suffix !=null && suffix.equals("jpg"))
                	 suffix = "jpeg";
                 response.setContentType("image/"+suffix+"");
                 response.setContentLength((int)file.length());
                 OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                 toClient.write(buffer);
                 toClient.flush();
                 toClient.close();
                 
        	}else {
        		response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
        	}
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	@RequestMapping("guideImg/{guideId}")
	public void guideImg(@PathVariable("guideId")String guideId,Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			Guide guide=guideService.getGuideImg(guideId);
			if(guide!=null){
				File file = new File(guide.getTitleName()+guide.getTitleImg());
		        // 以流的形式下载文件。
		        InputStream fis = new BufferedInputStream(new FileInputStream(file));
		        byte[] buffer = new byte[fis.available()];
		        fis.read(buffer);
		        fis.close();
		        response.reset();
				String su = guide.getTitleImg().substring(guide.getTitleImg().lastIndexOf("."));
				String suffix = su.substring(1);
		        if(suffix !=null && suffix.equals("jpg"))
		       	 suffix = "jpeg";
		        response.setContentType("image/"+suffix+"");
		        response.setContentLength((int)file.length());
		        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		        toClient.write(buffer);
		        toClient.flush();
		        toClient.close();
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}
		} catch (IOException ex) {
	        ex.printStackTrace();
	    }
    }
	
	@RequestMapping("infoImg/{infoId}")
	public void infoImg(@PathVariable("infoId")String infoId,Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			Information information=informationService.getInformationImg(infoId);
			if(information!=null){
				File file = new File(information.getTitleName()+information.getTitleImg());
				// 以流的形式下载文件。
				InputStream fis = new BufferedInputStream(new FileInputStream(file));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				response.reset();
				String su = information.getTitleImg().substring(information.getTitleImg().lastIndexOf("."));
				String suffix = su.substring(1);
				if(suffix !=null && suffix.equals("jpg"))
					suffix = "jpeg";
				response.setContentType("image/"+suffix+"");
				response.setContentLength((int)file.length());
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
