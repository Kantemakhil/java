/**
 * 
 */
package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OcioffncBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("caseId")
	private Long caseId;
	@JsonProperty("vclFlag")
	private String vclFlag;
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;
	@JsonProperty("offenceDescription")
	private String offenceDescription;
	@JsonProperty("complicityTypeDesc")
	private String complicityTypeDesc;
	@JsonProperty("applyFlag")
	private String applyFlag;
	@JsonProperty("offenceCode")
	private String offenceCode;
	@JsonProperty("statuteCode")
	private String statuteCode;
	@JsonProperty("chargeInfoNumber")
	private String chargeInfoNumber;
	@JsonProperty("offenceDate")
	private Date offenceDate;
	@JsonProperty("dispositionCode")
	private String dispositionCode;
	@JsonProperty("convictionFlag")
	private String convictionFlag;
	@JsonProperty("offenceType")
	private String offenceType;
	@JsonProperty("orderId")
	private Long orderId;
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the caseId
	 */
	public Long getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the vclFlag
	 */
	public String getVclFlag() {
		return vclFlag;
	}

	/**
	 * @param vclFlag
	 *            the vclFlag to set
	 */
	public void setVclFlag(String vclFlag) {
		this.vclFlag = vclFlag;
	}

	/**
	 * @return the offenderChargeId
	 */
	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	/**
	 * @param offenderChargeId
	 *            the offenderChargeId to set
	 */
	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	/**
	 * @return the offenceDescription
	 */
	public String getOffenceDescription() {
		return offenceDescription;
	}

	/**
	 * @param offenceDescription
	 *            the offenceDescription to set
	 */
	public void setOffenceDescription(String offenceDescription) {
		this.offenceDescription = offenceDescription;
	}

	/**
	 * @return the complicityTypeDesc
	 */
	public String getComplicityTypeDesc() {
		return complicityTypeDesc;
	}

	/**
	 * @param complicityTypeDesc
	 *            the complicityTypeDesc to set
	 */
	public void setComplicityTypeDesc(String complicityTypeDesc) {
		this.complicityTypeDesc = complicityTypeDesc;
	}

	/**
	 * @return the applyFlag
	 */
	public String getApplyFlag() {
		return applyFlag;
	}

	/**
	 * @param applyFlag
	 *            the applyFlag to set
	 */
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	/**
	 * @return the offenceCode
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 * @param offenceCode
	 *            the offenceCode to set
	 */
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 * @return the statuteCode
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 * @param statuteCode
	 *            the statuteCode to set
	 */
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	/**
	 * @return the chargeInfoNumber
	 */
	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}

	/**
	 * @param chargeInfoNumber
	 *            the chargeInfoNumber to set
	 */
	public void setChargeInfoNumber(String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}

	/**
	 * @return the offenceDate
	 */
	public Date getOffenceDate() {
		return offenceDate;
	}

	/**
	 * @param offenceDate
	 *            the offenceDate to set
	 */
	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	/**
	 * @return the dispositionCode
	 */
	public String getDispositionCode() {
		return dispositionCode;
	}

	/**
	 * @param dispositionCode
	 *            the dispositionCode to set
	 */
	public void setDispositionCode(String dispositionCode) {
		this.dispositionCode = dispositionCode;
	}

	/**
	 * @return the convictionFlag
	 */
	public String getConvictionFlag() {
		return convictionFlag;
	}

	/**
	 * @param convictionFlag
	 *            the convictionFlag to set
	 */
	public void setConvictionFlag(String convictionFlag) {
		this.convictionFlag = convictionFlag;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @param offenceType
	 *            the offenceType to set
	 */
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq the sentenceSeq to set
	 */
	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

}
