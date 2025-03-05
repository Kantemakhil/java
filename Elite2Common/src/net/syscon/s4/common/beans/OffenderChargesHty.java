package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderChargesHty extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("attempedFlag")
	private String attempedFlag;

	@JsonProperty("attemptedFlag")
	private String attemptedFlag;

	@JsonProperty("bailAllowedFlag")
	private String bailAllowedFlag;

	@JsonProperty("bailType")
	private String bailType;

	@JsonProperty("chargeBailAmount")
	private BigDecimal chargeBailAmount;

	@JsonProperty("chargeStatus")
	private String chargeStatus;

	@JsonProperty("clearedCounts")
	private BigDecimal clearedCounts;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("commitDate")
	private Date commitDate;

	@JsonProperty("convictionDate")
	private Date convictionDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
	private String creationUser;

	@JsonProperty("description")
	private String description;

	@JsonProperty("drugsInvolvedFlag")
	private String drugsInvolvedFlag;

	@JsonProperty("firearmsInvolvedFlag")
	private String firearmsInvolvedFlag;

	@JsonProperty("goodTimeAllowedFlag")
	private String goodTimeAllowedFlag;

	@JsonProperty("initialCounts")
	private BigDecimal initialCounts;

	@JsonProperty("intoximeter")
	private BigDecimal intoximeter;

	@JsonProperty("investigationId")
	private BigDecimal investigationId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenceDateRange")
	private Date offenceDateRange;

	@JsonProperty("offenceType")
	private String offenceType;

	@JsonProperty("offenseDate")
	private Date offenseDate;

	@JsonProperty("orderId")
	private BigDecimal orderId;

	@JsonProperty("originalBailAmount")
	private BigDecimal originalBailAmount;

	@JsonProperty("otherCharges")
	private String otherCharges;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sexInvolvedFlag")
	private String sexInvolvedFlag;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("vclAmount")
	private BigDecimal vclAmount;

	@JsonProperty("vclCommentText")
	private String vclCommentText;

	@JsonProperty("offence")
	private Offence offence;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("chargeSeq")
	private long chargeSeq;

	@JsonProperty("sentenceEventId")
	private long sentenceEventId;

	/**
	 *
	 * @return
	 */
	public String getAttempedFlag() {
		return attempedFlag;
	}

	/**
	 *
	 * @param attempedFlag
	 */
	public void setAttempedFlag(String attempedFlag) {
		this.attempedFlag = attempedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getAttemptedFlag() {
		return attemptedFlag;
	}

	/**
	 *
	 * @param attemptedFlag
	 */
	public void setAttemptedFlag(String attemptedFlag) {
		this.attemptedFlag = attemptedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getBailAllowedFlag() {
		return bailAllowedFlag;
	}

	/**
	 *
	 * @param bailAllowedFlag
	 */
	public void setBailAllowedFlag(String bailAllowedFlag) {
		this.bailAllowedFlag = bailAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getBailType() {
		return bailType;
	}

	/**
	 *
	 * @param bailType
	 */
	public void setBailType(String bailType) {
		this.bailType = bailType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getChargeBailAmount() {
		return chargeBailAmount;
	}

	/**
	 *
	 * @param chargeBailAmount
	 */
	public void setChargeBailAmount(BigDecimal chargeBailAmount) {
		this.chargeBailAmount = chargeBailAmount;
	}

	/**
	 *
	 * @return
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 *
	 * @param chargeStatus
	 */
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getClearedCounts() {
		return clearedCounts;
	}

	/**
	 *
	 * @param clearedCounts
	 */
	public void setClearedCounts(BigDecimal clearedCounts) {
		this.clearedCounts = clearedCounts;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCommitDate() {
		return commitDate;
	}

	/**
	 *
	 * @param commitDate
	 */
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getConvictionDate() {
		return convictionDate;
	}

	/**
	 *
	 * @param convictionDate
	 */
	public void setConvictionDate(Date convictionDate) {
		this.convictionDate = convictionDate;
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
	public String getDrugsInvolvedFlag() {
		return drugsInvolvedFlag;
	}

	/**
	 *
	 * @param drugsInvolvedFlag
	 */
	public void setDrugsInvolvedFlag(String drugsInvolvedFlag) {
		this.drugsInvolvedFlag = drugsInvolvedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getFirearmsInvolvedFlag() {
		return firearmsInvolvedFlag;
	}

	/**
	 *
	 * @param firearmsInvolvedFlag
	 */
	public void setFirearmsInvolvedFlag(String firearmsInvolvedFlag) {
		this.firearmsInvolvedFlag = firearmsInvolvedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getGoodTimeAllowedFlag() {
		return goodTimeAllowedFlag;
	}

	/**
	 *
	 * @param goodTimeAllowedFlag
	 */
	public void setGoodTimeAllowedFlag(String goodTimeAllowedFlag) {
		this.goodTimeAllowedFlag = goodTimeAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getInitialCounts() {
		return initialCounts;
	}

	/**
	 *
	 * @param initialCounts
	 */
	public void setInitialCounts(BigDecimal initialCounts) {
		this.initialCounts = initialCounts;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getIntoximeter() {
		return intoximeter;
	}

	/**
	 *
	 * @param intoximeter
	 */
	public void setIntoximeter(BigDecimal intoximeter) {
		this.intoximeter = intoximeter;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getInvestigationId() {
		return investigationId;
	}

	/**
	 *
	 * @param investigationId
	 */
	public void setInvestigationId(BigDecimal investigationId) {
		this.investigationId = investigationId;
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
	public Date getOffenceDateRange() {
		return offenceDateRange;
	}

	/**
	 *
	 * @param offenceDateRange
	 */
	public void setOffenceDateRange(Date offenceDateRange) {
		this.offenceDateRange = offenceDateRange;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 *
	 * @param offenceType
	 */
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 *
	 * @return
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}

	/**
	 *
	 * @param offenseDate
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOrderId() {
		return orderId;
	}

	/**
	 *
	 * @param orderId
	 */
	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOriginalBailAmount() {
		return originalBailAmount;
	}

	/**
	 *
	 * @param originalBailAmount
	 */
	public void setOriginalBailAmount(BigDecimal originalBailAmount) {
		this.originalBailAmount = originalBailAmount;
	}

	/**
	 *
	 * @return
	 */
	public String getOtherCharges() {
		return otherCharges;
	}

	/**
	 *
	 * @param otherCharges
	 */
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
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
	public String getSexInvolvedFlag() {
		return sexInvolvedFlag;
	}

	/**
	 *
	 * @param sexInvolvedFlag
	 */
	public void setSexInvolvedFlag(String sexInvolvedFlag) {
		this.sexInvolvedFlag = sexInvolvedFlag;
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
	public BigDecimal getVclAmount() {
		return vclAmount;
	}

	/**
	 *
	 * @param vclAmount
	 */
	public void setVclAmount(BigDecimal vclAmount) {
		this.vclAmount = vclAmount;
	}

	/**
	 *
	 * @return
	 */
	public String getVclCommentText() {
		return vclCommentText;
	}

	/**
	 *
	 * @param vclCommentText
	 */
	public void setVclCommentText(String vclCommentText) {
		this.vclCommentText = vclCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Offence getOffence() {
		return offence;
	}

	/**
	 *
	 * @param offence
	 */
	public void setOffence(Offence offence) {
		this.offence = offence;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getChargeSeq() {
		return chargeSeq;
	}

	/**
	 *
	 * @param chargeSeq
	 */
	public void setChargeSeq(long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 *
	 * @return
	 */
	public long getSentenceEventId() {
		return sentenceEventId;
	}

	/**
	 *
	 * @param sentenceEventId
	 */
	public void setSentenceEventId(long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}

}