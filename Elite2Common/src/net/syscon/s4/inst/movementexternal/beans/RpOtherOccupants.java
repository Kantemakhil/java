package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class RpOtherOccupants implements Serializable {
	private static final long serialVersionUID = 1L;

	private long rpOtherOccupantId;

	private String contactPhone;

	private String contactedFlag;

	private Date createDatetime;

	private String createUserId;

	private String extNo;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offenderContactPersonId;

	private String primaryFlag;

	private String rpComment;

	private String sealFlag;
	
	private long releasePlanId;
	
	private long personId;
	
	private String contactType;
	
	private String relationshipType;
	
	private String format;
	
	public RpOtherOccupants() {
	}

	public long getRpOtherOccupantId() {
		return this.rpOtherOccupantId;
	}

	public void setRpOtherOccupantId(long rpOtherOccupantId) {
		this.rpOtherOccupantId = rpOtherOccupantId;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactedFlag() {
		return this.contactedFlag;
	}

	public void setContactedFlag(String contactedFlag) {
		this.contactedFlag = contactedFlag;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getExtNo() {
		return this.extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderContactPersonId() {
		return this.offenderContactPersonId;
	}

	public void setOffenderContactPersonId(BigDecimal offenderContactPersonId) {
		this.offenderContactPersonId = offenderContactPersonId;
	}

	public String getPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public String getRpComment() {
		return this.rpComment;
	}

	public void setRpComment(String rpComment) {
		this.rpComment = rpComment;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @return the releasePlanId
	 */
	public long getReleasePlanId() {
		return releasePlanId;
	}

	/**
	 * @return the personId
	 */
	public long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	/**
	 * @param releasePlanId the releasePlanId to set
	 */
	public void setReleasePlanId(long releasePlanId) {
		this.releasePlanId = releasePlanId;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType the contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the relationshipType
	 */
	public String getRelationshipType() {
		return relationshipType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @param relationshipType the relationshipType to set
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	
}
