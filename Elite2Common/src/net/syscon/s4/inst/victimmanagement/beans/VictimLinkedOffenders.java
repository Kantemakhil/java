package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimLinkedOffenders extends BaseModel implements Serializable {
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("victimId")
	private Integer victimId;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("deactivatedDatetime")
	private Date deactivatedDatetime;
	@JsonProperty("note")
	private String note;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("agyLoc")
	private String agyLoc;
	@JsonProperty("offenderName")
	private String offenderName;
	
	private  Integer offenderBookId;
	
	@JsonProperty("associatedLegalCase")
	private String associatedLegalCase;
	
	private Integer orderNo;
	
	private String displayNo;
	
	private String orderType;
	
	@JsonProperty("jsonData")
	private byte[] jsonData;
	
	@JsonProperty("jsonDataString")
	private String jsonDataString;

	
	public String getJsonDataString() {
		return jsonDataString;
	}

	public void setJsonDataString(String jsonDataString) {
		this.jsonDataString = jsonDataString;
	}

	public byte[] getJsonData() {
		return jsonData;
	}

	public void setJsonData(byte[] jsonData) {
		this.jsonData = jsonData;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(String displayNo) {
		this.displayNo = displayNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAssociatedLegalCase() {
		return associatedLegalCase;
	}

	public void setAssociatedLegalCase(String associatedLegalCase) {
		this.associatedLegalCase = associatedLegalCase;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getAgyLoc() {
		return agyLoc;
	}

	public void setAgyLoc(String agyLoc) {
		this.agyLoc = agyLoc;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public Integer getVictimId() {
		return victimId;
	}

	public void setVictimId(Integer victimId) {
		this.victimId = victimId;
	}

	

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getDeactivatedDatetime() {
		return deactivatedDatetime;
	}

	public void setDeactivatedDatetime(Date deactivatedDatetime) {
		this.deactivatedDatetime = deactivatedDatetime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	@Override
	public String toString() {
		return "VictimLinkedOffenders [offenderId=" + offenderId + ", victimId=" + victimId + ", associatedLegalCase="
				+ associatedLegalCase + ", activeFlag=" + activeFlag + ", deactivatedDatetime=" + deactivatedDatetime
				+ ", note=" + note + ", createDatetime=" + createDatetime + ", createUserId=" + createUserId
				+ ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag
				+ ", offenderIdDisplay=" + offenderIdDisplay + ", agyLoc=" + agyLoc + ", offenderName=" + offenderName
				+ "]";
	}

}
