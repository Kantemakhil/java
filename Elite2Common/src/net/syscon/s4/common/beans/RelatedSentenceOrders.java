package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedSentenceOrders extends BaseModel implements Serializable {
	private String sentenceCategory;

	private String sentenceCalcType;

	private String rSentenceCategory;

	private String rSentenceCalcType;

	private String activeFlag;
	private Date expiryDate;
	private int returnValue;
	@JsonProperty("rowId")
	private String rowId;
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public int getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}
	public String getSentenceCategory() {
		return sentenceCategory;
	}
	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}
	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}
	public String getrSentenceCategory() {
		return rSentenceCategory;
	}
	public void setrSentenceCategory(String rSentenceCategory) {
		this.rSentenceCategory = rSentenceCategory;
	}
	public String getrSentenceCalcType() {
		return rSentenceCalcType;
	}
	public void setrSentenceCalcType(String rSentenceCalcType) {
		this.rSentenceCalcType = rSentenceCalcType;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	

}
