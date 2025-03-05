package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class WeeklyActivityPlansCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WeeklyActivityPlans> insertList;
	private List<WeeklyActivityPlans> deleteList;
	private List<WeeklyActivityPlans> updateList;
	private String finalizedWap;
	private BigDecimal htyVersionNo;
	private Date wapStartDate;
	private Date wapEndDate;	
	public List<WeeklyActivityPlans> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<WeeklyActivityPlans> insertList) {
		this.insertList = insertList;
	}
	public List<WeeklyActivityPlans> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<WeeklyActivityPlans> deleteList) {
		this.deleteList = deleteList;
	}
	public List<WeeklyActivityPlans> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<WeeklyActivityPlans> updateList) {
		this.updateList = updateList;
	}
	public String getFinalizedWap() {
		return finalizedWap;
	}
	
	public void setFinalizedWap(String finalizedWap) {
		this.finalizedWap = finalizedWap;
	}
	public BigDecimal getHtyVersionNo() {
		return htyVersionNo;
	}
	public void setHtyVersionNo(BigDecimal htyVersionNo) {
		this.htyVersionNo = htyVersionNo;
	}
	public Date getWapStartDate() {
		return wapStartDate;
	}
	public Date getWapEndDate() {
		return wapEndDate;
	}
	public void setWapStartDate(Date wapStartDate) {
		this.wapStartDate = wapStartDate;
	}
	public void setWapEndDate(Date wapEndDate) {
		this.wapEndDate = wapEndDate;
	}
	
	
}
