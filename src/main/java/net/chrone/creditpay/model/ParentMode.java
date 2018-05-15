package net.chrone.creditpay.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;

public class ParentMode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startRow;
	private int pageSize=Constants.SHOW_NUM;
	private String startDate;
	private String endDate;
	private Date startTime;
	private Date endTime;
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getStartTime() {
		if(StringUtils.isNotEmpty(startDate)){
			try {
				if(startDate.length()==8){
						return DateUtils.parseDate(startDate+" 00:00:00", "yyyyMMdd Hh:mm:ss");
				}else if(startDate.length()==10){
					return DateUtils.parseDate(startDate+" 00:00:00", "yyyy-MM-dd Hh:mm:ss");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return startTime;
	}
	public Date getEndTime() {
		if(StringUtils.isNotEmpty(endDate)){
			try {
				if(endDate.length()==8){
						return DateUtils.parseDate(endDate+" 23:59:59", "yyyyMMdd HH:mm:ss");
				}else if(endDate.length()==10){
					return DateUtils.parseDate(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return endTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	
}
