package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderPtr extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ptrId;
	private Long offenderBookId;
	private Date ptrDate;
	private String fromAgyLocId;
	private String toAgyLocId;
	private Date reportInDate;
	private String ptrComment;
	private String ptrCancelFlag;
	private Integer ptrCancelStaffId;
	private Date ptrCancelDate;
	private Integer assignReceivStaffId;
	private Date assignReceivStaffDate;
	private String decisionCode;
	private Date decisionDate;
	private String decisionComment;
	private Integer decisionStaffId;
	private String transferFlag;
	private Integer transferStaffId;
	private Date transferDatetime;
	private String transferCancelFlag;
	private Integer transferCancelStaffId;
	private Date transferCancelDatetime;
	private String sealFlag;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;

	public Long getPtrId() {
		return ptrId;
	}

	public void setPtrId(final Long ptrId) {
		this.ptrId = ptrId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getPtrDate() {
		return ptrDate;
	}

	public void setPtrDate(final Date ptrDate) {
		this.ptrDate = ptrDate;
	}

	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	public void setFromAgyLocId(final String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public Date getReportInDate() {
		return reportInDate;
	}

	public void setReportInDate(final Date reportInDate) {
		this.reportInDate = reportInDate;
	}

	public String getPtrComment() {
		return ptrComment;
	}

	public void setPtrComment(final String ptrComment) {
		this.ptrComment = ptrComment;
	}

	public String getPtrCancelFlag() {
		return ptrCancelFlag;
	}

	public void setPtrCancelFlag(final String ptrCancelFlag) {
		this.ptrCancelFlag = ptrCancelFlag;
	}

	public Integer getPtrCancelStaffId() {
		return ptrCancelStaffId;
	}

	public void setPtrCancelStaffId(final Integer ptrCancelStaffId) {
		this.ptrCancelStaffId = ptrCancelStaffId;
	}

	public Date getPtrCancelDate() {
		return ptrCancelDate;
	}

	public void setPtrCancelDate(final Date ptrCancelDate) {
		this.ptrCancelDate = ptrCancelDate;
	}

	public Integer getAssignReceivStaffId() {
		return assignReceivStaffId;
	}

	public void setAssignReceivStaffId(final Integer assignReceivStaffId) {
		this.assignReceivStaffId = assignReceivStaffId;
	}

	public Date getAssignReceivStaffDate() {
		return assignReceivStaffDate;
	}

	public void setAssignReceivStaffDate(final Date assignReceivStaffDate) {
		this.assignReceivStaffDate = assignReceivStaffDate;
	}

	public String getDecisionCode() {
		return decisionCode;
	}

	public void setDecisionCode(final String decisionCode) {
		this.decisionCode = decisionCode;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getDecisionComment() {
		return decisionComment;
	}

	public void setDecisionComment(final String decisionComment) {
		this.decisionComment = decisionComment;
	}

	public Integer getDecisionStaffId() {
		return decisionStaffId;
	}

	public void setDecisionStaffId(final Integer decisionStaffId) {
		this.decisionStaffId = decisionStaffId;
	}

	public String getTransferFlag() {
		return transferFlag;
	}

	public void setTransferFlag(final String transferFlag) {
		this.transferFlag = transferFlag;
	}

	public Integer getTransferStaffId() {
		return transferStaffId;
	}

	public void setTransferStaffId(final Integer transferStaffId) {
		this.transferStaffId = transferStaffId;
	}

	public Date getTransferDatetime() {
		return transferDatetime;
	}

	public void setTransferDatetime(final Date transferDatetime) {
		this.transferDatetime = transferDatetime;
	}

	public String getTransferCancelFlag() {
		return transferCancelFlag;
	}

	public void setTransferCancelFlag(final String transferCancelFlag) {
		this.transferCancelFlag = transferCancelFlag;
	}

	public Integer getTransferCancelStaffId() {
		return transferCancelStaffId;
	}

	public void setTransferCancelStaffId(final Integer transferCancelStaffId) {
		this.transferCancelStaffId = transferCancelStaffId;
	}

	public Date getTransferCancelDatetime() {
		return transferCancelDatetime;
	}

	public void setTransferCancelDatetime(final Date transferCancelDatetime) {
		this.transferCancelDatetime = transferCancelDatetime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
