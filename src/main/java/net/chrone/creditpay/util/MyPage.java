package net.chrone.creditpay.util;

import java.util.Collections;
import java.util.List;


public class MyPage {
	private int showNum = Constants.SHOW_NUM;//每页显示数目
	private List<?> resultList=null;//页面结果集列表
	private int start=0;//页面记录起始编号
	private int rowTotal=0;//总记录
	public final static MyPage EMPTY_PAGE=new MyPage(Collections.EMPTY_LIST,0,0,0);
	
	public MyPage(List<?> resultList, int start, int showNum, int rowTotal) {
		this.resultList = resultList;
		this.start = start;
		this.showNum = showNum;
		this.rowTotal = rowTotal;
	}
	/**
	 * 当前页面是否存在下一个页面.
	 * 
	 * @return boolean

	 */
	public boolean hasNextPage() {
		return (start + resultList.size()) < rowTotal;
	}

	/**
	 * 当前页面是否存在前一个页面.
	 * 
	 * @return boolean 

	 */
	public boolean hasPreviousPage() {
		return start > 0;
	}

	/**
	 * 当前页面的下一个页面的起始编号.
	 * 
	 * @return int 
	 */
	public int getStartOfNextPage() {
		return start + resultList.size();
	}

	/**
	 * 取得当前页面的前一个页面的起始编号.
	 * 
	 * @return int 

	 */
	public int getStartOfPreviousPage() {
		return Math.max(start - showNum, 0);
	}

	/**
	 * 取得总页数.
	 * 
	 * @return int

	 */
	public int getPageCount() {
		int ret = 0;
		if (showNum > 0) {
			if (rowTotal % showNum == 0) {
				ret = rowTotal / showNum;
			} else {
				ret = rowTotal / showNum + 1;
			}

		}
		return ret;
	}

	/**
	 * 取得当前页数.
	 * 
	 * @return int 
	 */
	public int getCurPageNum() {
		int ret = 0;
		if (showNum > 0)
			ret = start / showNum + 1;
		return ret;
	}
	/**
	 * 获取指定页数的起始编号
	 * @param pageNo
	 * @return
	 */
	public int getStartOfPage(int pageNo){
		int ret=0;
		if(pageNo<2){
			return ret;
		}else{
			return (pageNo-1)*showNum;
		}
	}
	

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	public int getRowTotal() {
		return rowTotal;
	}
	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}


}
