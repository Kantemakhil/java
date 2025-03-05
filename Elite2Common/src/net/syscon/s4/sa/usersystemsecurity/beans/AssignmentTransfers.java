package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class AssignmentTransfers extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long asstraId;

	private String calAgyLocId;

	private String calAgyLocIdFrom;

	private BigDecimal chargeSeq;

	private String component;

	private Object createDatetime;

	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private Date fromDate;

	private Date fromDateFrom;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offassId;

	private BigDecimal offenderBookId;

	private BigDecimal offenderBookIdRequest;

	private String position;

	private String positionFrom;

	private BigDecimal requestSeq;

	private String role;

	private String roleFrom;

	private BigDecimal sacStaffId;

	private BigDecimal sacStaffIdFrom;

	private String sealFlag;

	private BigDecimal sentenceSeq;

	private String statusFrom;

	private String statusTo;

	private Date transferDate;

	private Date transferDateTo;

	/**
	 * @return the asstraId
	 */
	public Long getAsstraId() {
		return asstraId;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @return the calAgyLocIdFrom
	 */
	public String getCalAgyLocIdFrom() {
		return calAgyLocIdFrom;
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
	public Object getCreateDatetime() {
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
	 * @return the fromDateFrom
	 */
	public Date getFromDateFrom() {
		return fromDateFrom;
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
	 * @return the offassId
	 */
	public BigDecimal getOffassId() {
		return offassId;
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
	 * @return the positionFrom
	 */
	public String getPositionFrom() {
		return positionFrom;
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
	 * @return the roleFrom
	 */
	public String getRoleFrom() {
		return roleFrom;
	}

	/**
	 * @return the sacStaffId
	 */
	public BigDecimal getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @return the sacStaffIdFrom
	 */
	public BigDecimal getSacStaffIdFrom() {
		return sacStaffIdFrom;
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
	 * @return the statusFrom
	 */
	public String getStatusFrom() {
		return statusFrom;
	}

	/**
	 * @return the statusTo
	 */
	public String getStatusTo() {
		return statusTo;
	}

	/**
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}

	/**
	 * @return the transferDateTo
	 */
	public Date getTransferDateTo() {
		return transferDateTo;
	}

	/**
	 * @param asstraId the asstraId to set
	 */
	public void setAsstraId(final Long asstraId) {
		this.asstraId = asstraId;
	}

	/**
	 * @param calAgyLocId the calAgyLocId to set
	 */
	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @param calAgyLocIdFrom the calAgyLocIdFrom to set
	 */
	public void setCalAgyLocIdFrom(final String calAgyLocIdFrom) {
		this.calAgyLocIdFrom = calAgyLocIdFrom;
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
	public void setCreateDatetime(final Object createDatetime) {
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
	 * @param fromDateFrom the fromDateFrom to set
	 */
	public void setFromDateFrom(final Date fromDateFrom) {
		this.fromDateFrom = fromDateFrom;
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
	 * @param offassId the offassId to set
	 */
	public void setOffassId(final BigDecimal offassId) {
		this.offassId = offassId;
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
	 * @param positionFrom the positionFrom to set
	 */
	public void setPositionFrom(final String positionFrom) {
		this.positionFrom = positionFrom;
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
	 * @param roleFrom the roleFrom to set
	 */
	public void setRoleFrom(final String roleFrom) {
		this.roleFrom = roleFrom;
	}

	/**
	 * @param sacStaffId the sacStaffId to set
	 */
	public void setSacStaffId(final BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @param sacStaffIdFrom the sacStaffIdFrom to set
	 */
	public void setSacStaffIdFrom(final BigDecimal sacStaffIdFrom) {
		this.sacStaffIdFrom = sacStaffIdFrom;
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
	 * @param statusFrom the statusFrom to set
	 */
	public void setStatusFrom(final String statusFrom) {
		this.statusFrom = statusFrom;
	}

	/**
	 * @param statusTo the statusTo to set
	 */
	public void setStatusTo(final String statusTo) {
		this.statusTo = statusTo;
	}

	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(final Date transferDate) {
		this.transferDate = transferDate;
	}

	/**
	 * @param transferDateTo the transferDateTo to set
	 */
	public void setTransferDateTo(final Date transferDateTo) {
		this.transferDateTo = transferDateTo;
	}

}
