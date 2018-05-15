package net.chrone.creditpay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.VerifyCodeUtils;


@Controller
@RequestMapping("public")
public class RandomImageController {
	
	@RequestMapping("randomImage")
	@ResponseBody
	public void randomImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogWriter.info("RandomImage ===Start hua tu");
		response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        request.getSession().setAttribute("Urand", verifyCode.toLowerCase());
        // 生成图片
        int w = 310, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		LogWriter.info("end RandomImage");

	}

	
}
