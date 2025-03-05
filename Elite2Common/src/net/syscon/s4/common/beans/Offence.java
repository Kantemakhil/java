package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offence extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("checkBox1")
	private String checkBox1;

	@JsonProperty("checkBox2")
	private String checkBox2;

	@JsonProperty("checkBox3")
	private String checkBox3;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("defaultOffenceType")
	private String defaultOffenceType;

	@JsonProperty("description")
	private String description;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("hoCode")
	private String hoCode;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("maxGoodTimePerc")
	private BigDecimal maxGoodTimePerc;

	@JsonProperty("maxSentenceLength")
	private BigDecimal maxSentenceLength;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenceGroup")
	private String offenceGroup;

	@JsonProperty("offenseDegree")
	private String offenseDegree;

	@JsonProperty("oldStatuteCode")
	private String oldStatuteCode;

	@JsonProperty("repealedDate")
	private Date repealedDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceUnitCode")
	private String sentenceUnitCode;

	@JsonProperty("severityRanking")
	private String severityRanking;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("offenceCodeGroupings")
	private List<OffenceCodeGrouping> offenceCodeGroupings;

	@JsonProperty("offenceIndicators")
	private List<OffenceIndicator> offenceIndicators;

	@JsonProperty("offenderCharges")
	private List<OffenderCharge> offenderCharges;

	@JsonProperty("offenderChargesHties")
	private List<OffenderChargesHty> offenderChargesHties;

	@JsonProperty("offenceCode")
	private String offenceCode;

	@JsonProperty("statuteCode")
	private String statuteCode;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("shortDescription")
	private String shortDescription;
	
	@JsonProperty("offenceId")
	private Long offenceId;

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox1() {
		return checkBox1;
	}

	/**
	 *
	 * @param checkBox1
	 */
	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox2() {
		return checkBox2;
	}

	/**
	 *
	 * @param checkBox2
	 */
	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox3() {
		return checkBox3;
	}

	/**
	 *
	 * @param checkBox3
	 */
	public void setCheckBox3(String checkBox3) {
		this.checkBox3 = checkBox3;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getDefaultOffenceType() {
		return defaultOffenceType;
	}

	/**
	 *
	 * @param defaultOffenceType
	 */
	public void setDefaultOffenceType(String defaultOffenceType) {
		this.defaultOffenceType = defaultOffenceType;
	}

	/**
	 *
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	public String getHoCode() {
		return hoCode;
	}

	/**
	 *
	 * @param hoCode
	 */
	public void setHoCode(String hoCode) {
		this.hoCode = hoCode;
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
	public BigDecimal getMaxGoodTimePerc() {
		return maxGoodTimePerc;
	}

	/**
	 *
	 * @param maxGoodTimePerc
	 */
	public void setMaxGoodTimePerc(BigDecimal maxGoodTimePerc) {
		this.maxGoodTimePerc = maxGoodTimePerc;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getMaxSentenceLength() {
		return maxSentenceLength;
	}

	/**
	 *
	 * @param maxSentenceLength
	 */
	public void setMaxSentenceLength(BigDecimal maxSentenceLength) {
		this.maxSentenceLength = maxSentenceLength;
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
	public String getOffenceGroup() {
		return offenceGroup;
	}

	/**
	 *
	 * @param offenceGroup
	 */
	public void setOffenceGroup(String offenceGroup) {
		this.offenceGroup = offenceGroup;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenseDegree() {
		return offenseDegree;
	}

	/**
	 *
	 * @param offenseDegree
	 */
	public void setOffenseDegree(String offenseDegree) {
		this.offenseDegree = offenseDegree;
	}

	/**
	 *
	 * @return
	 */
	public String getOldStatuteCode() {
		return oldStatuteCode;
	}

	/**
	 *
	 * @param oldStatuteCode
	 */
	public void setOldStatuteCode(String oldStatuteCode) {
		this.oldStatuteCode = oldStatuteCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getRepealedDate() {
		return repealedDate;
	}

	/**
	 *
	 * @param repealedDate
	 */
	public void setRepealedDate(Date repealedDate) {
		this.repealedDate = repealedDate;
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
	public String getSentenceUnitCode() {
		return sentenceUnitCode;
	}

	/**
	 *
	 * @param sentenceUnitCode
	 */
	public void setSentenceUnitCode(String sentenceUnitCode) {
		this.sentenceUnitCode = sentenceUnitCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSeverityRanking() {
		return severityRanking;
	}

	/**
	 *
	 * @param severityRanking
	 */
	public void setSeverityRanking(String severityRanking) {
		this.severityRanking = severityRanking;
	}

	/**
	 *
	 * @return
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 *
	 * @param updateAllowedFlag
	 */
	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenceCodeGrouping> getOffenceCodeGroupings() {
		return offenceCodeGroupings;
	}

	/**
	 *
	 * @param offenceCodeGroupings
	 */
	public void setOffenceCodeGroupings(List<OffenceCodeGrouping> offenceCodeGroupings) {
		this.offenceCodeGroupings = offenceCodeGroupings;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenceIndicator> getOffenceIndicators() {
		return offenceIndicators;
	}

	/**
	 *
	 * @param offenceIndicators
	 */
	public void setOffenceIndicators(List<OffenceIndicator> offenceIndicators) {
		this.offenceIndicators = offenceIndicators;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCharge> getOffenderCharges() {
		return offenderCharges;
	}

	/**
	 *
	 * @param offenderCharges
	 */
	public void setOffenderCharges(List<OffenderCharge> offenderCharges) {
		this.offenderCharges = offenderCharges;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderChargesHty> getOffenderChargesHties() {
		return offenderChargesHties;
	}

	/**
	 *
	 * @param offenderChargesHties
	 */
	public void setOffenderChargesHties(List<OffenderChargesHty> offenderChargesHties) {
		this.offenderChargesHties = offenderChargesHties;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 *
	 * @param offenceCode
	 */
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 *
	 * @return
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 *
	 * @param statuteCode
	 */
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getOffenceId() {
		return offenceId;
	}

	public void setOffenceId(Long offenceId) {
		this.offenceId = offenceId;
	}

}