package net.chrone.creditpay.taglibs;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

public class PageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name="";//page对象名字
	private String formId="";//表单id
	
	private HttpServletRequest request = null;
	private MyPage page=null;
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		request = (HttpServletRequest) pageContext.getRequest();
		page = (MyPage) request.getAttribute(name);// Page对象
		if (page == null){
			page = MyPage.EMPTY_PAGE;
		}
		//生成翻页html
		try {
			pageContext.getOut().print("<div id='pageGro' class='cb'>");
			if(page.getPageCount()>2){
				pageContext.getOut().print("<a href='#' onclick='submitFormPage(0)' style='float:left;height:29px;line-height:29px;'>首页</a>");
			}
			
			pageContext.getOut().print(printSubmitJS());
			pageContext.getOut().print(printViewPage());
			if(page.getPageCount()>2){ 
				pageContext.getOut().print("<a href='#' onclick='submitFormPage("+page.getStartOfPage(page.getPageCount())+")' style='height:29px;line-height:29px;'>尾页</a>");
			}
			pageContext.getOut().print("</div>");
		} catch (IOException e) {
			e.printStackTrace();
			LogWriter.error("PageTag标签输出出现异常：");
			throw new JspTagException(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}
	/**
	 * 生成翻译
	 * @param style
	 * @return
	 */
	private String printViewPage(){
		StringBuffer sb = new StringBuffer();
		if(page.getPageCount()>1){
			return sb.append(firstPageView()+centerPageView()+lastPageView()).toString();
		}
		return "";
	}
	
	//提交表单
	private String printSubmitJS() {
		StringBuffer js = new StringBuffer();
		js.append("<SCRIPT LANGUAGE=\"JavaScript\">																					").append("\n").append(		
				"function submitFormPage(start){																									").append("\n").append(		
				"document.getElementById('start').value=start;																				").append("\n").append(		
				"document.getElementById('"+formId+"').submit();																		").append("\n } \n").append(
				"function goToPage(){																				    					            ").append("\n").append(
				"		var pageVal=document.getElementById('tzym');														            ").append("\n").append(																	
				"		var countPageNo="+page.getPageCount()+";																		 ").append("\n").append(																	
				"		var txt=/^\\d*$/;				if(pageVal.value==''||pageVal.value=='0'){alert('页数不能为空,且大于0');pageVal.focus();return ;}																									").append("\n").append(																	
				"		if(!txt.test(pageVal.value)){																										").append("\n").append(			
				"			alert('页数应为正整数!');																										").append("\n").append(			
				"			pageVal.focus();																												").append("\n").append(			
				"			return ;																																").append("\n").append(			
				"		}																																			").append("\n").append(			
				"		if(pageVal.value<2){																												").append("\n").append(			
				"			submitFormPage(0);																											").append("\n").append(			
				"		}																																			").append("\n").append(			
				"		if(pageVal.value>=countPageNo){																						").append("\n").append(			
				"			submitFormPage("+page.getStartOfPage(page.getPageCount())+");									").append("\n").append(			
				"		}else{																																		").append("\n").append(			
				"			submitFormPage((pageVal.value-1)*"+Constants.SHOW_NUM+");												").append("\n").append(			
				"		}																																			").append("\n } \n").append(			
				"</SCRIPT>\n");
		return js.toString();
	}
	/**
	 * 上一页
	 * @return
	 */
	private String firstPageView(){
		StringBuffer sb = new StringBuffer();
		if(page.hasPreviousPage()){
			sb.append("<div class='pageUp' onclick='submitFormPage("+page.getStartOfPreviousPage()+")' ><img src='"+request.getContextPath()+"/static/img/prev.png' style='padding-top: 8px;'></div>");
		}
		return sb.toString();
	}
	/**
	 * 末页 下一页
	 * @return
	 */
	private String lastPageView(){
		StringBuffer sb = new StringBuffer();
		if(page.hasNextPage()){
			sb.append("<div class='pageDown' onclick='submitFormPage("+page.getStartOfNextPage()+")' ><img src='"+request.getContextPath()+"/static/img/next.png' style='padding-top: 8px;'></div>");
		}
		return sb.toString();
	}
	/**
	 * 页列表
	 * @return
	 */
	private String centerPageView(){
		StringBuffer sb = new StringBuffer();
		int pageNo=page.getCurPageNum();
		int pageTotal=page.getPageCount();
		int rowTotal = page.getRowTotal();
		sb.append("<div class='pageList'>");	
		sb.append("<ul style='margin:0px;'>");
		if((pageNo==pageTotal-1)&&pageNo>3){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo-3)+")'>"+(pageNo-3)+"</li>");
		}
		if(pageNo==pageTotal&&pageTotal>4){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo-4)+")'>"+(pageNo-4)+"</li>");
		}
		if(pageNo==pageTotal&&pageTotal>3){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo-3)+")'>"+(pageNo-3)+"</li>");
		}
		if(pageNo>2){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo-2)+")'>"+(pageNo-2)+"</li>");
		}
		if(pageNo>1){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo-1)+")'>"+(pageNo-1)+"</li>");
		}
		sb.append("<li class='on'>"+page.getCurPageNum()+"</a>");
		if(page.hasNextPage()){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo+1)+")'>"+(pageNo+1)+"</li>");
		}
		if(pageTotal>=(pageNo+2)){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo+2)+")'>"+(pageNo+2)+"</li>");
		}
		if(pageNo==1&&pageTotal>3){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo+3)+")'>"+(pageNo+3)+"</li>");
		}
		if(pageNo==1&&pageTotal>4){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo+4)+")'>"+(pageNo+4)+"</li>");
		}
		if(pageNo==2&&pageTotal>4){
			sb.append("<li onclick='submitFormPage("+page.getStartOfPage(pageNo+3)+")'>"+(pageNo+3)+"</li>");
		}
		sb.append("</ul>");
		sb.append("</div>");
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
}
