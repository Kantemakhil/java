package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.Date;

public class OffStatusesPkgSpec {

	BigDecimal lvBookId;
    Date lvRequesDate;
    String lvRequestType;
    String lvRequestReason;
    String lvRequestStatus;
    String lvNewRequestStatus;
    String lvDurationCode;
    String lvInternalStatusSeq;
    String lvPartyType;
    BigDecimal lvAgyLocId;
    String lvNextReview;
    String lvRevBox;
    
    
    
	public String getLvNextReview() {
		return lvNextReview;
	}
	public void setLvNextReview(String lvNextReview) {
		this.lvNextReview = lvNextReview;
	}
	public String getLvRevBox() {
		return lvRevBox;
	}
	public void setLvRevBox(String lvRevBox) {
		this.lvRevBox = lvRevBox;
	}
	public BigDecimal getLvBookId() {
		return lvBookId;
	}
	public void setLvBookId(BigDecimal lvBookId) {
		this.lvBookId = lvBookId;
	}
	public Date getLvRequesDate() {
		return lvRequesDate;
	}
	public void setLvRequesDate(Date lvRequesDate) {
		this.lvRequesDate = lvRequesDate;
	}
	public String getLvRequestType() {
		return lvRequestType;
	}
	public void setLvRequestType(String lvRequestType) {
		this.lvRequestType = lvRequestType;
	}
	public String getLvRequestReason() {
		return lvRequestReason;
	}
	public void setLvRequestReason(String lvRequestReason) {
		this.lvRequestReason = lvRequestReason;
	}
	public String getLvRequestStatus() {
		return lvRequestStatus;
	}
	public void setLvRequestStatus(String lvRequestStatus) {
		this.lvRequestStatus = lvRequestStatus;
	}
	public String getLvNewRequestStatus() {
		return lvNewRequestStatus;
	}
	public void setLvNewRequestStatus(String lvNewRequestStatus) {
		this.lvNewRequestStatus = lvNewRequestStatus;
	}
	public String getLvDurationCode() {
		return lvDurationCode;
	}
	public void setLvDurationCode(String lvDurationCode) {
		this.lvDurationCode = lvDurationCode;
	}
	public String getLvInternalStatusSeq() {
		return lvInternalStatusSeq;
	}
	public void setLvInternalStatusSeq(String lvInternalStatusSeq) {
		this.lvInternalStatusSeq = lvInternalStatusSeq;
	}
	public String getLvPartyType() {
		return lvPartyType;
	}
	public void setLvPartyType(String lvPartyType) {
		this.lvPartyType = lvPartyType;
	}
	public BigDecimal getLvAgyLocId() {
		return lvAgyLocId;
	}
	public void setLvAgyLocId(BigDecimal lvAgyLocId) {
		this.lvAgyLocId = lvAgyLocId;
	}
    
    
    
    
}
