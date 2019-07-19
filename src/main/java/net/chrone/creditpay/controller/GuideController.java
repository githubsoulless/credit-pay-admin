package net.chrone.creditpay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.chrone.creditpay.model.Guide;
import net.chrone.creditpay.service.GuideService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.service.impl.SeqServiceImpl;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.PropertiseUtil;

@Controller
@RequestMapping("guide")
public class GuideController {
	
	@Autowired
	private GuideService guideService;
	@Autowired
	private SeqService seqService;
	
	private String saveFilePath = PropertiseUtil.getDataFromPropertiseFile("pay", "guidePath");
	private static Logger logger = Logger.getLogger(GuideController.class);
	
	@RequestMapping("list")
	public String list(Guide guide, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		guide.setStartRow(starIndex);
		int rowTotal = guideService.getGuideByPageCount(guide);
		
		List<Guide> list = new ArrayList<Guide>();
		if (rowTotal > 0) {
			list = guideService.listGuidePage(guide);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("guide", guide);
		model.addAttribute("rowTotal", rowTotal);
		return "guide/list";
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		return "guide/add";
	}
	
	@RequestMapping("add")
	public String add(@RequestParam("imgs") MultipartFile[] files,Model model, HttpServletRequest request,HttpServletResponse response,Guide guide) {
		String message = "";
		try {
			Guide gUide = guideService.getGuideByTitle(guide.getTitle());
			if(null != gUide) {
				message = "标题已存在";
				model.addAttribute("message", message);
				return "guide/add";
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
			            guide.setTitleName(saveFilePath);
			            guide.setTitleImg(fileName);
			            
			            String profitsId = seqService.updateAndGetSequence(SeqServiceImpl.T_FAST_PROFITS, 9);
						guide.setGuideId(profitsId);
						guideService.insert(guide);
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
		return "guide/add";
	}
	
	@RequestMapping("delete")
	public @ResponseBody String delete(String guideId, HttpServletRequest request) {
		int i=0;
		try {
			Guide guide = guideService.getGuideByPrimaryKey(guideId);
			if(guide!=null){
				i=guideService.deleteByPrimaryKey(guideId);
				if(i>0){
					File file = new File(guide.getTitleName()+guide.getTitleImg());
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
	public String toUpdate(String guideId, Model model) {
		Guide detail = guideService.getGuideByPrimaryKey(guideId);
		model.addAttribute("guide", detail);
		return "guide/update";
	}
	
	@RequestMapping("update")
	public String update(@RequestParam("imgs") MultipartFile[] files, Model model, HttpServletRequest request,Guide guide) {
		String message = "";
		try {
			Guide detail = guideService.getGuideByPrimaryKey(guide.getGuideId());
			if(!detail.getTitle().equals(detail.getTitle())) {
				Guide gUide = guideService.getGuideByTitle(guide.getTitle());
				if(null != gUide) {
					message = "标题已存在";
					model.addAttribute("message", message);
					return "guide/update";
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
				            guide.setTitleName(saveFilePath);
				            guide.setTitleImg(fileName);
						}else {
							message = "上传文件列表中包含不支持的文件格式,部分文件上传失败";
						}
					}
				}	
			}else {
				message = "请选择文件";
			}
			guideService.updateGuide(guide);
			message = "success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "guide/update";
	}

	@RequestMapping("find")
	public String find(Guide guide, Model model, HttpServletRequest request) {
		String message = "";
		try {
			Guide detail = guideService.getGuideByPrimaryKey(guide.getGuideId());
			model.addAttribute("guide", detail);
			message="success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "guide/find";
	}
}
