package net.chrone.creditpay.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.classfile.Constant;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.util.SignatureUtil;

public class BaseController {
    
    public void response(Map<String, String> respMap,HttpServletResponse response) {
       
        String json = JSON.toJSONString(respMap);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
