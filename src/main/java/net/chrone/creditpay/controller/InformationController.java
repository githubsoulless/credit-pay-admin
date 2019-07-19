package net.chrone.creditpay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.chrone.creditpay.model.Information;
import net.chrone.creditpay.service.InformationService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.service.impl.SeqServiceImpl;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.PropertiseUtil;

@Controller
@RequestMapping("info")
public class InformationController {
	@Autowired
	private InformationService informationService;
	@Autowired
	private SeqService seqService;
	
	private String saveFilePath = PropertiseUtil.getDataFromPropertiseFile("pay", "infoPath");
	private static Logger logger = Logger.getLogger(GuideController.class);
	
	@RequestMapping("list")
	public String list(Information information, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		information.setStartRow(starIndex);
		int rowTotal = informationService.getInformationByPageCount(information);
		
		List<Information> list = new ArrayList<Information>();
		if (rowTotal > 0) {
			list = informationService.listInformationPage(information);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("information", information);
		model.addAttribute("rowTotal", rowTotal);
		return "info/list";
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		return "info/add";
	}
	
	@RequestMapping("add")
	public String add(@RequestParam("imgs") MultipartFile[] files, Model model, HttpServletRequest request,Information information) {
		String message = "";
		try {
			Information iNformation = informationService.getInformationByTitle(information.getTitle());
			if(null != iNformation) {
				message = "标题已存在";
				model.addAttribute("message", message);
				return "info/add";
			}
			if(files != null && files.length>0) {
				for(int i=0;i<files.length;i++) {
					MultipartFile file = files[i];
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
			            information.setTitleName(saveFilePath);
			            information.setTitleImg(fileName);
			            
			            String profitsId = seqService.updateAndGetSequence(SeqServiceImpl.T_FAST_PROFITS, 9);
			            information.setInfoId(profitsId);
			            informationService.insert(information);
						message="success";
					}else {
						message = "上传文件列表中包含不支持的文件格式,部分文件上传失败";
					}
				}	
			}else {
				message = "请选择文件";
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "info/add";
	}
	
	@RequestMapping("find")
	public String find(Information information, Model model, HttpServletRequest request) {
		String message = "";
		try {
			Information detail = informationService.getInformationByPrimaryKey(information.getInfoId());
			model.addAttribute("information", detail);
			message="success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "info/find";
	}

	@RequestMapping("delete")
	public @ResponseBody String delete(String infoId, HttpServletRequest request) {
		int i=0;
		try {
			Information information = informationService.getInformationByPrimaryKey(infoId);
			if(information!=null){
				i=informationService.deleteByPrimaryKey(infoId);
				if(i>0){
					File file = new File(information.getTitleImg());
					if(file.exists()){
						file.delete();
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return String.valueOf(i);
	}
	

	@RequestMapping("toUpdate")
	public String toUpdate(String infoId, Model model) {
		Information detail = informationService.getInformationByPrimaryKey(infoId);
		model.addAttribute("information", detail);
		return "info/update";
	}
	
	@RequestMapping("update")
	public String update(@RequestParam("imgs") MultipartFile[] files, Model model, HttpServletRequest request,Information information) {
		String message = "";
		try {
			Information detail = informationService.getInformationByPrimaryKey(information.getInfoId());
			if(!detail.getTitle().equals(detail.getTitle())) {
				Information iNformation = informationService.getInformationByTitle(information.getTitle());
				if(null != iNformation) {
					message = "标题已存在";
					model.addAttribute("message", message);
					return "info/update";
				}
			}
			if(files != null && files.length>0) {
				for(int i=0;i<files.length;i++) {
					MultipartFile file = files[i];
					String oFileName = file.getOriginalFilename();
					if(!oFileName.isEmpty()){
						//删除之前的照片
						File fileOld = new File(detail.getTitleImg());
						if(fileOld.exists()){
							fileOld.delete();
						}
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
				            information.setTitleName(saveFilePath);
				            information.setTitleImg(fileName);
						}else {
							message = "上传文件列表中包含不支持的文件格式,部分文件上传失败";
						}
					}
				}	
			}else {
				message = "请选择文件";
			}
			informationService.updateInformation(information);
			message = "success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "info/update";
	}
}
