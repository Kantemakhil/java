package net.syscon.s4.inst.movements.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveBedLocations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("removeReason")
	private String removeReason;

	@JsonProperty("reserveBedId")
	private BigDecimal reserveBedId;

	@JsonProperty("reserveUntilDate")
	private Date reserveUntilDate;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("livingUnitDesc")
	private String livingUnitDesc;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("warningFlag")
	private Boolean warningFlag;
	
	@JsonProperty("warningMsg")
	private String warningMsg;
	
	@JsonProperty("warningPrompt")
	private String warningPrompt;

	@JsonProperty("livingUnitCode")
	private String livingUnitCode;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("cbFlag")
	private Boolean cbFlag;
	
	@JsonProperty("ocFlag")
	private Boolean ocFlag;
	
	public ReserveBedLocations() {
		// ReserveBedLocations
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
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
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the livingUnitId
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId the livingUnitId to set
	 */
	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the removeReason
	 */
	public String getRemoveReason() {
		return removeReason;
	}

	/**
	 * @param removeReason the removeReason to set
	 */
	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	/**
	 * @return the reserveBedId
	 */
	public BigDecimal getReserveBedId() {
		return reserveBedId;
	}

	/**
	 * @param reserveBedId the reserveBedId to set
	 */
	public void setReserveBedId(BigDecimal reserveBedId) {
		this.reserveBedId = reserveBedId;
	}

	/**
	 * @return the reserveUntilDate
	 */
	public Date getReserveUntilDate() {
		return reserveUntilDate;
	}

	/**
	 * @param reserveUntilDate the reserveUntilDate to set
	 */
	public void setReserveUntilDate(Date reserveUntilDate) {
		this.reserveUntilDate = reserveUntilDate;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Boolean getWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	public String getLivingUnitCode() {
		return livingUnitCode;
	}

	public void setLivingUnitCode(String livingUnitCode) {
		this.livingUnitCode = livingUnitCode;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Boolean getCbFlag() {
		return cbFlag;
	}

	public void setCbFlag(Boolean cbFlag) {
		this.cbFlag = cbFlag;
	}

	public Boolean getOcFlag() {
		return ocFlag;
	}

	public void setOcFlag(Boolean ocFlag) {
		this.ocFlag = ocFlag;
	}

	

}
