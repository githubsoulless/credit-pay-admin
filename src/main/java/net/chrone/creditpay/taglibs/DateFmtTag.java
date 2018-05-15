package net.chrone.creditpay.taglibs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


import org.apache.commons.lang3.StringUtils;

import net.chrone.creditpay.util.LogWriter;

public class DateFmtTag extends TagSupport{

	private String val="";
	private String srcStringFmt="";
	private String descFmt="";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		try {
			String result="";
			if(StringUtils.isNotEmpty(val)&&StringUtils.isNotEmpty(srcStringFmt)&&StringUtils.isNotEmpty(descFmt)){
				DateFormat format = new SimpleDateFormat(srcStringFmt);
				Date date = format.parse(val);
				format = new SimpleDateFormat(descFmt);
				result = format.format(date);
			}
			pageContext.getOut().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			LogWriter.error("DateFmtTag标签输出出现异常：");
			throw new JspTagException(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getSrcStringFmt() {
		return srcStringFmt;
	}

	public void setSrcStringFmt(String srcStringFmt) {
		this.srcStringFmt = srcStringFmt;
	}

	public String getDescFmt() {
		return descFmt;
	}

	public void setDescFmt(String descFmt) {
		this.descFmt = descFmt;
	}

}
