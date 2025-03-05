package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the OFFENDER_SENT_OBLIGATIONS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderSentObligations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("obligationId")
	private long obligationId;
	@JsonProperty("amount")
	private BigDecimal amount;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("countNo")
	private BigDecimal countNo;
	@JsonProperty("courtInfoId")
	private String courtInfoId;
	@JsonProperty("createDatetime")
	private Object createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("informationNumber")
	private String informationNumber;
	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("orderId")
	private BigDecimal orderId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("sentenceSeq")
	private BigDecimal sentenceSeq;

	public OffenderSentObligations() {
	}

	public long getObligationId() {
		return this.obligationId;
	}

	public void setObligationId(long obligationId) {
		this.obligationId = obligationId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public BigDecimal getCountNo() {
		return this.countNo;
	}

	public void setCountNo(BigDecimal countNo) {
		this.countNo = countNo;
	}

	public String getCourtInfoId() {
		return this.courtInfoId;
	}

	public void setCourtInfoId(String courtInfoId) {
		this.courtInfoId = courtInfoId;
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

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public String getInformationNumber() {
		return this.informationNumber;
	}

	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
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

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getSentenceSeq() {
		return this.sentenceSeq;
	}

	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

}
