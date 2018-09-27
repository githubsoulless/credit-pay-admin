package net.chrone.creditpay.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.model.ShareImg;
import net.chrone.creditpay.service.ShareImgService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.PropertiseUtil;
import net.chrone.creditpay.util.StringUtil;
/**
 * 
 * @author yasin
 *
 */
@Controller
@RequestMapping("shareImg")
public class ShareImgController extends BaseController{
	
	@Autowired
	private ShareImgService shareImgService;
	private String saveFilePath = PropertiseUtil.getDataFromPropertiseFile("pay", "imgPath");

	@RequestMapping("list")
	public String list(Model model) {
		
		List<ShareImg> list = shareImgService.getAllShareImg();
		model.addAttribute("list", list);
		return "shareImg/list";
	}
	
	@RequestMapping("toUpload")
	public String toUpload() {
		return "shareImg/upload";
	}
	
	
	@RequestMapping("upload")
	public String upload(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam("imgs") MultipartFile[] files) {
		Map<String,String> respMap = new HashMap<>();
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		String message = "success";
		try {
			if(files != null && files.length>0) {
				for(int i=0;i<files.length;i++) {

					ShareImg shareImg = new ShareImg();
					MultipartFile file = files[i];
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
			            shareImg.setImgName(fileName);
			            shareImg.setImgPath(saveFilePath);
			            shareImg.setFileSize(Integer.parseInt(fileSize+""));
			            shareImg.setOperAdmin(userInfSeesion.getUserName());
			            shareImg.setImgSort(100);
			            shareImg.setImgUse(true);
			            shareImg.setCreateTime(new Date());
			            shareImgService.addShareImg(shareImg);
					}else {
						message = "上传文件列表中包含不支持的文件格式,部分文件上传失败";
					}
				}	
			}else {
				message = "请选择文件";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			message = "文件上传失败";
		}
		
		model.addAttribute("message",message);
		return "shareImg/upload";
	}
	/**
	 * type=1 禁用
	 * type=2 恢复
	 * type=3 上移
	 * type=4 下移
	 */
	@RequestMapping("update")
	public void update(String imgName,String type,Model model, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> respMap = new HashMap<>();
		
		if(StringUtils.isNotEmpty(imgName) &&StringUtils.isNotEmpty(type)) {
			ShareImg shareImg = shareImgService.getShareImg(imgName);
			if(shareImg !=null) {
				shareImg.setImgName(imgName);
				if(type.equals("1")) {
					shareImg.setImgUse(false);
				}else if(type.equals("2")) {
					shareImg.setImgUse(true);
				}else if(type.equals("3")) {
					shareImg.setImgSort(shareImg.getImgSort()+1);
				}
				else if(type.equals("4")) {
					shareImg.setImgSort(shareImg.getImgSort()-1);
				}
				shareImgService.updateShareImg(shareImg);
				respMap.put("code", "200");
				respMap.put("msg", "success");
			}else {
				respMap.put("code", "500");
				respMap.put("msg", "文件名有误");
			}
		}else {
			respMap.put("code", "500");
			respMap.put("msg", "参数有误");
		}
		
		
		
		
		
		
		response(respMap, response);;
	}
	@RequestMapping("delete")
	public void delete(String imgName, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> respMap = new HashMap<>();
		if(!StringUtil.isEmpty(imgName)) {
			shareImgService.delShareImg(imgName);;
			respMap.put("code","200");
			respMap.put("msg","操作成功");
		}else {
			respMap.put("code","500");
			respMap.put("msg","参数有误");
		}
		
		response(respMap, response);;
	}
	
}
