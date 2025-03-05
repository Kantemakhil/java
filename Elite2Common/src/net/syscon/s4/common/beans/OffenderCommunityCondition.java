package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCommunityCondition extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offCcId")
	private long offCcId;

	@JsonProperty("categoryType")
	private String categoryType;

	@JsonProperty("commConditionCode")
	private String commConditionCode;

	@JsonProperty("commConditionType")
	private String commConditionType;

	@JsonProperty("conditionAmount")
	private BigDecimal conditionAmount;

	@JsonProperty("conditionStatus")
	private String conditionStatus;

	@JsonProperty("conditionText")
	private Serializable conditionText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
	private String creationUser;

	@JsonProperty("csoFlag")
	private String csoFlag;

	@JsonProperty("csoHours")
	private String csoHours;

	@JsonProperty("detailsText")
	private String detailsText;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("financialFlag")
	private String financialFlag;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("programFlag")
	private String programFlag;

	@JsonProperty("programUnits")
	private BigDecimal programUnits;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceSeq")
	private BigDecimal sentenceSeq;

	@JsonProperty("statusDate")
	private Date statusDate;

	@JsonProperty("substanceFlag")
	private String substanceFlag;

	@JsonProperty("unitsCredited")
	private BigDecimal unitsCredited;

	@JsonProperty("unitsRemaining")
	private BigDecimal unitsRemaining;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	/**
	 *
	 * @return
	 */
	public long getOffCcId() {
		return offCcId;
	}

	/**
	 *
	 * @param offCcId
	 */
	public void setOffCcId(long offCcId) {
		this.offCcId = offCcId;
	}

	/**
	 *
	 * @return
	 */
	public String getCategoryType() {
		return categoryType;
	}

	/**
	 *
	 * @param categoryType
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommConditionCode() {
		return commConditionCode;
	}

	/**
	 *
	 * @param commConditionCode
	 */
	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}

	/**
	 *
	 * @return
	 */
	public String getCommConditionType() {
		return commConditionType;
	}

	/**
	 *
	 * @param commConditionType
	 */
	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getConditionAmount() {
		return conditionAmount;
	}

	/**
	 *
	 * @param conditionAmount
	 */
	public void setConditionAmount(BigDecimal conditionAmount) {
		this.conditionAmount = conditionAmount;
	}

	/**
	 *
	 * @return
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}

	/**
	 *
	 * @param conditionStatus
	 */
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	/**
	 *
	 * @return
	 */
	public Serializable getConditionText() {
		return conditionText;
	}

	/**
	 *
	 * @param conditionText
	 */
	public void setConditionText(Serializable conditionText) {
		this.conditionText = conditionText;
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 *
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 *
	 * @param creationUser
	 */
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 *
	 * @return
	 */
	public String getCsoFlag() {
		return csoFlag;
	}

	/**
	 *
	 * @param csoFlag
	 */
	public void setCsoFlag(String csoFlag) {
		this.csoFlag = csoFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCsoHours() {
		return csoHours;
	}

	/**
	 *
	 * @param csoHours
	 */
	public void setCsoHours(String csoHours) {
		this.csoHours = csoHours;
	}

	/**
	 *
	 * @return
	 */
	public String getDetailsText() {
		return detailsText;
	}

	/**
	 *
	 * @param detailsText
	 */
	public void setDetailsText(String detailsText) {
		this.detailsText = detailsText;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 *
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public String getFinancialFlag() {
		return financialFlag;
	}

	/**
	 *
	 * @param financialFlag
	 */
	public void setFinancialFlag(String financialFlag) {
		this.financialFlag = financialFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 *
	 * @param listSeq
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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
	public String getProgramFlag() {
		return programFlag;
	}

	/**
	 *
	 * @param programFlag
	 */
	public void setProgramFlag(String programFlag) {
		this.programFlag = programFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getProgramUnits() {
		return programUnits;
	}

	/**
	 *
	 * @param programUnits
	 */
	public void setProgramUnits(BigDecimal programUnits) {
		this.programUnits = programUnits;
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
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 *
	 * @param sentenceSeq
	 */
	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getStatusDate() {
		return statusDate;
	}

	/**
	 *
	 * @param statusDate
	 */
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 *
	 * @return
	 */
	public String getSubstanceFlag() {
		return substanceFlag;
	}

	/**
	 *
	 * @param substanceFlag
	 */
	public void setSubstanceFlag(String substanceFlag) {
		this.substanceFlag = substanceFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getUnitsCredited() {
		return unitsCredited;
	}

	/**
	 *
	 * @param unitsCredited
	 */
	public void setUnitsCredited(BigDecimal unitsCredited) {
		this.unitsCredited = unitsCredited;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getUnitsRemaining() {
		return unitsRemaining;
	}

	/**
	 *
	 * @param unitsRemaining
	 */
	public void setUnitsRemaining(BigDecimal unitsRemaining) {
		this.unitsRemaining = unitsRemaining;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

}