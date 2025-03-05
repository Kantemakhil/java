package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_FINE_DEFAULTS database table.
 * 
 */
public class OffenderFineDefaults extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object createDatetime;

	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private Date dateImposed;

	private String imposingAgyLocId;

	private Object modifyDatetime;

	private String modifyUserId;

	private String reissueOrderFlag;

	private Date reportByDate;

	private String sealFlag;

	private BigDecimal totalAmountOwing;

	private BigDecimal totalCourtAmount;

	private String totalCourtCode;

	private BigDecimal totalOtherAmount;

	private String totalOtherCode;

	private BigDecimal totalPenaltyAmount;

	private String totalPenaltyCode;

	private BigDecimal totalProfAmount;

	private String totalProfCode;

	private BigDecimal totalVclAmount;

	private String totalVclCode;

	private long offenderBookId;

	private long sentenceSeq;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	
	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getDateImposed() {
		return this.dateImposed;
	}

	public void setDateImposed(Date dateImposed) {
		this.dateImposed = dateImposed;
	}

	public String getImposingAgyLocId() {
		return this.imposingAgyLocId;
	}

	public void setImposingAgyLocId(String imposingAgyLocId) {
		this.imposingAgyLocId = imposingAgyLocId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getReissueOrderFlag() {
		return this.reissueOrderFlag;
	}

	public void setReissueOrderFlag(String reissueOrderFlag) {
		this.reissueOrderFlag = reissueOrderFlag;
	}

	public Date getReportByDate() {
		return this.reportByDate;
	}

	public void setReportByDate(Date reportByDate) {
		this.reportByDate = reportByDate;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTotalAmountOwing() {
		return this.totalAmountOwing;
	}

	public void setTotalAmountOwing(BigDecimal totalAmountOwing) {
		this.totalAmountOwing = totalAmountOwing;
	}

	public BigDecimal getTotalCourtAmount() {
		return this.totalCourtAmount;
	}

	public void setTotalCourtAmount(BigDecimal totalCourtAmount) {
		this.totalCourtAmount = totalCourtAmount;
	}

	public String getTotalCourtCode() {
		return this.totalCourtCode;
	}

	public void setTotalCourtCode(String totalCourtCode) {
		this.totalCourtCode = totalCourtCode;
	}

	public BigDecimal getTotalOtherAmount() {
		return this.totalOtherAmount;
	}

	public void setTotalOtherAmount(BigDecimal totalOtherAmount) {
		this.totalOtherAmount = totalOtherAmount;
	}

	public String getTotalOtherCode() {
		return this.totalOtherCode;
	}

	public void setTotalOtherCode(String totalOtherCode) {
		this.totalOtherCode = totalOtherCode;
	}

	public BigDecimal getTotalPenaltyAmount() {
		return this.totalPenaltyAmount;
	}

	public void setTotalPenaltyAmount(BigDecimal totalPenaltyAmount) {
		this.totalPenaltyAmount = totalPenaltyAmount;
	}

	public String getTotalPenaltyCode() {
		return this.totalPenaltyCode;
	}

	public void setTotalPenaltyCode(String totalPenaltyCode) {
		this.totalPenaltyCode = totalPenaltyCode;
	}

	public BigDecimal getTotalProfAmount() {
		return this.totalProfAmount;
	}

	public void setTotalProfAmount(BigDecimal totalProfAmount) {
		this.totalProfAmount = totalProfAmount;
	}

	public String getTotalProfCode() {
		return this.totalProfCode;
	}

	public void setTotalProfCode(String totalProfCode) {
		this.totalProfCode = totalProfCode;
	}

	public BigDecimal getTotalVclAmount() {
		return this.totalVclAmount;
	}

	public void setTotalVclAmount(BigDecimal totalVclAmount) {
		this.totalVclAmount = totalVclAmount;
	}

	public String getTotalVclCode() {
		return this.totalVclCode;
	}

	public void setTotalVclCode(String totalVclCode) {
		this.totalVclCode = totalVclCode;
	}

}
