package net.chrone.creditpay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.MobileVersion;
import net.chrone.creditpay.service.MobileVersionService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.PropertiseUtil;

/**
 * 
 * Title: MobileVersionController Description: 手机客户端版本管理
 * 
 * @author huoliang
 * @data 2017年11月30日 上午10:22:55
 *
 */

@Controller
@RequestMapping("mobileVersion")
public class MobileVersionController {

	@Autowired
	private MobileVersionService mobileVersionService;
	@Autowired
	private LogConstant logConstant;
	private String saveFilePath = PropertiseUtil.getDataFromPropertiseFile("pay", "APK_FILE_PATH");

	@RequestMapping("list")
	public String list(MobileVersion mobileVersion, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		mobileVersion.setStartRow(starIndex);
		List<MobileVersion> list = new ArrayList<>();

		int rowTotal = mobileVersionService.countMobileVersion(mobileVersion);
		if (rowTotal > 0) {
			list = mobileVersionService.listMobileVersion(mobileVersion);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("mobileVersion", mobileVersion);

		return "mobileVersion/list";
	}

	@RequestMapping("toAdd")
	public String toAdd() {
		return "mobileVersion/add";
	}

	@RequestMapping("add")
	public String add(MobileVersion mobileVersion, Model model, HttpServletRequest request,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			MobileVersion oMobileVersion = mobileVersionService.getMobileVersion(mobileVersion);
			if (null != oMobileVersion) {
				message = "客户端版本已存在";
				model.addAttribute("message", message);
				return "mobileVersion/add";
			}
			long fileSize = file.getSize();
			String oFileName = file.getOriginalFilename();
			String fileName = new IdGen().nextId() + oFileName.substring(oFileName.lastIndexOf("."));
			File newFile = new File(saveFilePath + fileName);
            if (!newFile.exists()) { // 文件夹
            	newFile.getParentFile().mkdirs();
            	newFile.createNewFile();
            }
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            mobileVersion.setFileSize(fileSize);
            mobileVersion.setOldFileName(oFileName);
            mobileVersion.setFileName(fileName);
            mobileVersion.setFilePath(saveFilePath);
			mobileVersion.setRegTime(new Date());
			mobileVersion.setOperator(userInfSeesion.getLoginId());
			mobileVersionService.saveMobileVersion(mobileVersion);
			logConstant.createTweblog(userInfSeesion.getLoginId(), "版本管理，创建版本，版本号=" + mobileVersion.getAppVersion(),8, request);
			message = "success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "mobileVersion/add";
	}

	@RequestMapping("detail")
	public String detail(MobileVersion mobileVersion, String type, Model model, HttpServletRequest request) {
		MobileVersion oMobileVersion = mobileVersionService.getMobileVersion(mobileVersion);
		model.addAttribute("mobileVersion", oMobileVersion);
		return "mobileVersion/detail";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(MobileVersion mobileVersion, Model model){
		MobileVersion oMobileVersion = mobileVersionService.getMobileVersion(mobileVersion);
		model.addAttribute("mobileVersion", oMobileVersion);
		return "mobileVersion/update";
	}

	@RequestMapping("update")
	public String update(MobileVersion mobileVersion, Model model, HttpServletRequest request, MultipartFile file) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if(!file.isEmpty()){
				long fileSize = file.getSize();
				String oFileName = file.getOriginalFilename();
				String fileName = new IdGen().nextId() + oFileName.substring(oFileName.lastIndexOf("."));
				File newFile = new File(saveFilePath + fileName);
	            if (!newFile.exists()) { // 文件夹
	            	newFile.getParentFile().mkdirs();
	            	newFile.createNewFile();
	            }
	            // 将内存中的数据写入磁盘
	            file.transferTo(newFile);
	            mobileVersion.setFileSize(fileSize);
	            mobileVersion.setOldFileName(oFileName);
	            mobileVersion.setFileName(fileName);
	            mobileVersion.setFilePath(saveFilePath);
			}
			mobileVersion.setModTime(new Date());
			mobileVersion.setOperator(userInfSeesion.getLoginId());
			mobileVersionService.updateMobileVersion(mobileVersion);
			logConstant.createTweblog(userInfSeesion.getLoginId(),
					"版本管理，修改版本信息，版本号=" + mobileVersion.getAppVersion(), 8, request);
			message = "success";
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "mobileVersion/update";
	}

}
