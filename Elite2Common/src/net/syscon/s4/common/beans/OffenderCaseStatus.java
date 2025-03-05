package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCaseStatus extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderCaseStatusId")
	private long offenderCaseStatusId;

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

	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;

	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;

	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;

	@JsonProperty("statusUpdateStaffId")
	private BigDecimal statusUpdateStaffId;

	@JsonProperty("offenderCas")
	private OffenderCas offenderCas;

	/**
	 *
	 * @return
	 */
	public long getOffenderCaseStatusId() {
		return offenderCaseStatusId;
	}

	/**
	 *
	 * @param offenderCaseStatusId
	 */
	public void setOffenderCaseStatusId(long offenderCaseStatusId) {
		this.offenderCaseStatusId = offenderCaseStatusId;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusUpdateComment() {
		return statusUpdateComment;
	}

	/**
	 *
	 * @param statusUpdateComment
	 */
	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	/**
	 *
	 * @return
	 */
	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	/**
	 *
	 * @param statusUpdateDate
	 */
	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	/**
	 *
	 * @param statusUpdateReason
	 */
	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	/**
	 *
	 * @param statusUpdateStaffId
	 */
	public void setStatusUpdateStaffId(BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCas getOffenderCas() {
		return offenderCas;
	}

	/**
	 *
	 * @param offenderCas
	 */
	public void setOffenderCas(OffenderCas offenderCas) {
		this.offenderCas = offenderCas;
	}

}