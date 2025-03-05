package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OffenderUnpaidWorkAdj  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal offenderUnpaidWorkAdjId	;
	private BigDecimal offenderBookId	;
	private BigDecimal sentenceSeq	;
	private Date adjustmentDate	;
	private BigDecimal adjustmentMinutes	;
	private String  adjustmentType	;
	private String  reasonCode;
	private String  commentText;
	private Date createDatetime;
	private String  createUserId;
	private Date modifyDatetime	;
	private String  modifyUserId;
	private String sealFlag;
	private String orderType;
	private BigDecimal offenderSentConditionId;
	
	public BigDecimal getOffenderUnpaidWorkAdjId() {
		return offenderUnpaidWorkAdjId;
	}
	public void setOffenderUnpaidWorkAdjId(BigDecimal offenderUnpaidWorkAdjId) {
		this.offenderUnpaidWorkAdjId = offenderUnpaidWorkAdjId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public Date getAdjustmentDate() {
		return adjustmentDate;
	}
	public void setAdjustmentDate(Date adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}
	public BigDecimal getAdjustmentMinutes() {
		return adjustmentMinutes;
	}
	public void setAdjustmentMinutes(BigDecimal adjustmentMinutes) {
		this.adjustmentMinutes = adjustmentMinutes;
	}
	public String getAdjustmentType() {
		return adjustmentType;
	}
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}
	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	
	

}
