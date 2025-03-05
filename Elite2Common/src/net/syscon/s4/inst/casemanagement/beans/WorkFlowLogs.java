package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
public class WorkFlowLogs  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("actionUserId")
	private BigDecimal actionUserId;
	@JsonProperty("createDate")
	private Date createDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("locateAgyLocId")
	private String locateAgyLocId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("workActionCode")
	private String workActionCode;
	@JsonProperty("workActionDate")
	private Date workActionDate;
	@JsonProperty("workFlowStatus")
	private String workFlowStatus;
	@JsonProperty("workFlowId")
	private Long workFlowId;
	@JsonProperty("workFlowSeq")
	private long workFlowSeq;
	@JsonProperty("nbtOffenderBookId")
	private BigDecimal nbtOffenderBookId;
	@JsonProperty("commentText")
	private String commentText;
	

	@JsonProperty("nbtAlertSeq")
	private long nbtAlertSeq; 
	
	@JsonProperty("createUser")
	private String createUser;
	
	private String status;
	private String comment;
	private String agyLocId;
	

	
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(final String createUser) {
		this.createUser = createUser;
	}

	public WorkFlowLogs() {
		// WorkFlowLog
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(final Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public long getWorkFlowSeq() {
		return workFlowSeq;
	}

	public void setWorkFlowSeq(final long workFlowSeq) {
		this.workFlowSeq = workFlowSeq;
	}

	public BigDecimal getActionUserId() {
		return this.actionUserId;
	}

	public void setActionUserId(final BigDecimal actionUserId) {
		this.actionUserId = actionUserId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getLocateAgyLocId() {
		return this.locateAgyLocId;
	}

	public void setLocateAgyLocId(final String locateAgyLocId) {
		this.locateAgyLocId = locateAgyLocId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getWorkActionCode() {
		return this.workActionCode;
	}

	public void setWorkActionCode(final String workActionCode) {
		this.workActionCode = workActionCode;
	}

	public Date getWorkActionDate() {
		return this.workActionDate;
	}

	public void setWorkActionDate(final Date workActionDate) {
		this.workActionDate = workActionDate;
	}

	public String getWorkFlowStatus() {
		return this.workFlowStatus;
	}

	public void setWorkFlowStatus(final String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * @return the nbtOffenderBookId
	 */
	public BigDecimal getNbtOffenderBookId() {
		return nbtOffenderBookId;
	}

	/**
	 * @param nbtOffenderBookId the nbtOffenderBookId to set
	 */
	public void setNbtOffenderBookId(final BigDecimal nbtOffenderBookId) {
		this.nbtOffenderBookId = nbtOffenderBookId;
	}

	/**
	 * @return the nbtAlertSeq
	 */
	public long getNbtAlertSeq() {
		return nbtAlertSeq;
	}

	/**
	 * @param nbtAlertSeq the nbtAlertSeq to set
	 */
	public void setNbtAlertSeq(final long nbtAlertSeq) {
		this.nbtAlertSeq = nbtAlertSeq;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

}
