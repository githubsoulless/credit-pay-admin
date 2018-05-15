package net.chrone.creditpay.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import net.chrone.creditpay.util.LogWriter;


public class HiddenStr  extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String srcStr="";
	private int head=0;
	private int footer=0;
	
	@Override
	public int doEndTag() throws JspException {
		try {
			if(srcStr.length()<=(head+footer)){
				pageContext.getOut().print(srcStr);
			}else{
				StringBuffer sb = new StringBuffer();
				String headStr = srcStr.substring(0, Integer.valueOf(head));
				String footerStr = srcStr.substring(srcStr.length()-Integer.valueOf(footer),srcStr.length());
				for(int i=0;i<(srcStr.length()-(Integer.valueOf(head) + Integer.valueOf(footer)));i++){
					sb.append("*");
				}
				pageContext.getOut().print(headStr+sb.toString()+footerStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogWriter.error("标签输出出现异常：");
			throw new JspTagException(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}

	public String getSrcStr() {
		return srcStr;
	}

	public void setSrcStr(String srcStr) {
		this.srcStr = srcStr;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getFooter() {
		return footer;
	}

	public void setFooter(int footer) {
		this.footer = footer;
	}
	public static void main(String[] args) {
		String a="01234567";
		if(a.length()<=8){
			System.out.println(a);
			return;
		}
		StringBuffer sb = new StringBuffer();
		String headStr = a.substring(0, 4);
		String footerStr = a.substring(a.length()-4,a.length());
		for(int i=0;i<(a.length()-8);i++){
			sb.append("*");
		}
		System.out.println(headStr+sb.toString()+footerStr);
	}

}
