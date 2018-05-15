package net.chrone.creditpay.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


import org.apache.commons.lang3.StringUtils;

import net.chrone.creditpay.util.LogWriter;


public class Fen2YuanTag  extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String amt="";
	
	@Override
	public int doEndTag() throws JspException {
		try {
			if(StringUtils.isEmpty(amt)){
				pageContext.getOut().print("0.00");
			}else{
				pageContext.getOut().print(formatAmt(amt));
			}
		} catch (IOException e) {
			e.printStackTrace();
			LogWriter.error("PageTag标签输出出现异常：");
			throw new JspTagException(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}
	
	public static String formatAmt(String fen){
		if(fen==null){
			return  "0.00";
		}
		fen = delZeroFromLeft(fen);
		
		String yuan = "";
		boolean isSigned = false;
		
		if(fen.length()>=2&&"-".equals(fen.substring(0, 1))){
			fen = fen.substring(1);
			isSigned = true;
		}
		if("".equals(fen)){
			return  "0.00";
		}else
		if(fen.length()==1){
			yuan = "0.0"+fen;
		}else
		if(fen.length()==2){
			yuan = "0."+fen;
		}
		else{
			yuan = fen.substring(0,fen.length()-2)+"."+fen.substring(fen.length()-2);
		}
		if(isSigned == true){
			return "-"+yuan;
		}else{
			return yuan;
		}
	}

	public static String delZeroFromLeft(String src){
			
			StringBuffer sb = new StringBuffer(src);
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				if(sb.charAt(0)=='0')
					sb.deleteCharAt(0);
				else
					return sb.toString();
			}
			return sb.toString();
		}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	};

}
