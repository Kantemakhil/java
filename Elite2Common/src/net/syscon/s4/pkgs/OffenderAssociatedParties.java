package net.syscon.s4.pkgs;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderAssociatedParties extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long associatedPartyId;
	private Long offenderBookId;
	private String partyType;
	private String partyId;
	private String relationshipCode;
	private String victimEmailAddr;
	Double age;
	private String ethnicity;
	private String createUserId;
	private Date createDatetime;
	private Long caseId;
	private String sealFlag;
	private Date modifyDatetime;
	private String modifyUserId;

	public Long getAssociatedPartyId() {
		return associatedPartyId;
	}

	public void setAssociatedPartyId(Long associatedPartyId) {
		this.associatedPartyId = associatedPartyId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getRelationshipCode() {
		return relationshipCode;
	}

	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}

	public String getVictimEmailAddr() {
		return victimEmailAddr;
	}

	public void setVictimEmailAddr(String victimEmailAddr) {
		this.victimEmailAddr = victimEmailAddr;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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

}
