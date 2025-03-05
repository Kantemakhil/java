package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderFingerprints extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("dateCreated")
	private Date dateCreated;

	@JsonProperty("fingerCode")
	@Size(max = 4000)
	private String fingerCode;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("userCreated")
	@Size(max = 50)
	private String userCreated;

	@JsonProperty("rootOffenderId")
	@NotNull
	private Long rootOffenderId;

	@JsonProperty("handId")
	@NotNull
	@Size(max = 15)
	private String handId;

	@JsonProperty("fingerId")
	@NotNull
	@Size(max = 15)
	private String fingerId;

	/**
	 * Creates new Offenders class Object
	 */
	public OffenderFingerprints() {
		// OffenderFingerprints
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
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 *
	 * @param dateCreated
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 *
	 * @return
	 */
	public String getFingerCode() {
		return fingerCode;
	}

	/**
	 *
	 * @param fingerCode
	 */
	public void setFingerCode(final String fingerCode) {
		this.fingerCode = fingerCode;
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
	public String getUserCreated() {
		return userCreated;
	}

	/**
	 *
	 * @param userCreated
	 */
	public void setUserCreated(final String userCreated) {
		this.userCreated = userCreated;
	}

	/**
	 *
	 * @return
	 */
	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(final Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getHandId() {
		return handId;
	}

	/**
	 *
	 * @param handId
	 */
	public void setHandId(final String handId) {
		this.handId = handId;
	}

	/**
	 *
	 * @return
	 */
	public String getFingerId() {
		return fingerId;
	}

	/**
	 *
	 * @param fingerId
	 */
	public void setFingerId(final String fingerId) {
		this.fingerId = fingerId;
	}

}