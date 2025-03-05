package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderWorkAssignments extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long offassId;

	private String agyLocId;

	private String calAgyLocId;

	private BigDecimal chargeSeq;

	private String component;

	private Date createDatetime;

	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private Date fromDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String offassComment;

	private BigDecimal offenderBookId;

	private BigDecimal offenderBookIdRequest;

	private String position;

	private BigDecimal requestSeq;

	private String role;

	private BigDecimal sacStaffId;

	private String sealFlag;

	private BigDecimal sentenceSeq;

	private String status;

	/**
	 * @return the offassId
	 */
	public Long getOffassId() {
		return offassId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @return the chargeSeq
	 */
	public BigDecimal getChargeSeq() {
		return chargeSeq;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the creationUser
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @return the offassComment
	 */
	public String getOffassComment() {
		return offassComment;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @return the offenderBookIdRequest
	 */
	public BigDecimal getOffenderBookIdRequest() {
		return offenderBookIdRequest;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the requestSeq
	 */
	public BigDecimal getRequestSeq() {
		return requestSeq;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return the sacStaffId
	 */
	public BigDecimal getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @return the sentenceSeq
	 */
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param offassId the offassId to set
	 */
	public void setOffassId(final Long offassId) {
		this.offassId = offassId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @param calAgyLocId the calAgyLocId to set
	 */
	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @param chargeSeq the chargeSeq to set
	 */
	public void setChargeSeq(final BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(final String component) {
		this.component = component;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param creationUser the creationUser to set
	 */
	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @param offassComment the offassComment to set
	 */
	public void setOffassComment(final String offassComment) {
		this.offassComment = offassComment;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @param offenderBookIdRequest the offenderBookIdRequest to set
	 */
	public void setOffenderBookIdRequest(final BigDecimal offenderBookIdRequest) {
		this.offenderBookIdRequest = offenderBookIdRequest;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @param requestSeq the requestSeq to set
	 */
	public void setRequestSeq(final BigDecimal requestSeq) {
		this.requestSeq = requestSeq;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(final String role) {
		this.role = role;
	}

	/**
	 * @param sacStaffId the sacStaffId to set
	 */
	public void setSacStaffId(final BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @param sentenceSeq the sentenceSeq to set
	 */
	public void setSentenceSeq(final BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

}
