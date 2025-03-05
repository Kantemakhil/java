package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderImprisonStatuses extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("effectiveTime")
	private Date effectiveTime;

	@JsonProperty("errorFlag")
	private String errorFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus;
	
	@JsonProperty("oldImprisonmentStatus")
	private String oldImprisonmentStatus;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("state")
	private String state;

//	@JsonProperty("offenderBookings")
//	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("imprisonStatusSeq")
	private Long imprisonStatusSeq;
	
	@JsonProperty("globalCaseloadId")
	private String globalCaseloadId;

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the effectiveTime
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * @param effectiveTime the effectiveTime to set
	 */
	public void setEffectiveTime(final Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * @return the errorFlag
	 */
	public String getErrorFlag() {
		return errorFlag;
	}

	/**
	 * @param errorFlag the errorFlag to set
	 */
	public void setErrorFlag(final String errorFlag) {
		this.errorFlag = errorFlag;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the imprisonmentStatus
	 */
	public String getImprisonmentStatus() {
		return imprisonmentStatus;
	}

	/**
	 * @param imprisonmentStatus the imprisonmentStatus to set
	 */
	public void setImprisonmentStatus(final String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the imprisonStatusSeq
	 */
	public Long getImprisonStatusSeq() {
		return imprisonStatusSeq;
	}

	/**
	 * @param imprisonStatusSeq the imprisonStatusSeq to set
	 */
	public void setImprisonStatusSeq(final Long imprisonStatusSeq) {
		this.imprisonStatusSeq = imprisonStatusSeq;
	}

	/**
	 * @return the globalCaseloadId
	 */
	public String getGlobalCaseloadId() {
		return globalCaseloadId;
	}

	/**
	 * @param globalCaseloadId the globalCaseloadId to set
	 */
	public void setGlobalCaseloadId(final String globalCaseloadId) {
		this.globalCaseloadId = globalCaseloadId;
	}
	
	public String getOldImprisonmentStatus() {
		return oldImprisonmentStatus;
	}

	public void setOldImprisonmentStatus(String oldImprisonmentStatus) {
		this.oldImprisonmentStatus = oldImprisonmentStatus;
	}

	@Override
	public String toString() {
		return "OffenderImprisonStatuses [agyLocId=" + agyLocId + ", commentText=" + commentText + ", createDate="
				+ createDate + ", createDatetime=" + createDatetime + ", createUserId=" + createUserId
				+ ", effectiveDate=" + effectiveDate + ", effectiveTime=" + effectiveTime + ", errorFlag=" + errorFlag
				+ ", expiryDate=" + expiryDate + ", imprisonmentStatus=" + imprisonmentStatus + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", state=" + state
				+ ", offenderBookId=" + offenderBookId + ", imprisonStatusSeq=" + imprisonStatusSeq
				+ ", globalCaseloadId=" + globalCaseloadId + "]";
	}

}
