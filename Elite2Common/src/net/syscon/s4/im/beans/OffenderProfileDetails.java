package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderProfileDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("listSeq")
	@NotNull
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("profileCode")
	private String profileCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("profileSeq")
	@NotNull
	private Long profileSeq;

	@JsonProperty("profileType")
	@NotNull
	@Size(max = 12)
	private String profileType;

	@JsonProperty("nbtCharacteristic")
	private String nbtCharacteristic;

	@JsonProperty("nbtDetailDesc")
	private String nbtDetailDesc;
	
	@JsonProperty("profileTypeDesc")
	private String profileTypeDesc;
	
	@JsonProperty("codeValueType")
	private String codeValueType;
	
	private String updatedAllowedFlag;
	
	private String mandatoryFlag;
	
	@JsonProperty("prevProfileCode")
	private String prevProfileCode;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getUpdatedAllowedFlag() {
		return updatedAllowedFlag;
	}

	public void setUpdatedAllowedFlag(String updatedAllowedFlag) {
		this.updatedAllowedFlag = updatedAllowedFlag;
	}

	public String getMandatoryFlag() {
		return mandatoryFlag;
	}

	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
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
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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
	public void setListSeq(final BigDecimal listSeq) {
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 *
	 * @param profileCode
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public Long getProfileSeq() {
		return profileSeq;
	}

	/**
	 *
	 * @param profileSeq
	 */
	public void setProfileSeq(final Long profileSeq) {
		this.profileSeq = profileSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 *
	 * @param profileType
	 */
	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	public String getNbtCharacteristic() {
		return nbtCharacteristic;
	}

	public void setNbtCharacteristic(final String nbtCharacteristic) {
		this.nbtCharacteristic = nbtCharacteristic;
	}

	public String getNbtDetailDesc() {
		return nbtDetailDesc;
	}

	public void setNbtDetailDesc(final String nbtDetailDesc) {
		this.nbtDetailDesc = nbtDetailDesc;
	}

	/**
	 * @return the profileTypeDesc
	 */
	public String getProfileTypeDesc() {
		return profileTypeDesc;
	}

	/**
	 * @param profileTypeDesc the profileTypeDesc to set
	 */
	public void setProfileTypeDesc(final String profileTypeDesc) {
		this.profileTypeDesc = profileTypeDesc;
	}

	public String getCodeValueType() {
		return codeValueType;
	}

	public void setCodeValueType(String codeValueType) {
		this.codeValueType = codeValueType;
	}

	public String getPrevProfileCode() {
		return prevProfileCode;
	}

	public void setPrevProfileCode(String prevProfileCode) {
		this.prevProfileCode = prevProfileCode;
	}
	
}