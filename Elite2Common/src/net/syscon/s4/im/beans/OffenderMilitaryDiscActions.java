package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderMilitaryDiscActions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("mltyDiscpCode")
	private String mltyDiscpCode;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("militarySeq")
	@NotNull
	private Long militarySeq;

	@JsonProperty("mltyDiscpSeq")
	@NotNull
	private Long mltyDiscpSeq;

	/**
	 * Creates new OffenderMilitaryDiscActions class Object
	 */
	public OffenderMilitaryDiscActions() {
		// OffenderMilitaryDiscActions
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the mltyDiscpCode
	 */
	public String getMltyDiscpCode() {
		return mltyDiscpCode;
	}

	/**
	 * @param mltyDiscpCode
	 *            the mltyDiscpCode to set
	 */
	public void setMltyDiscpCode(String mltyDiscpCode) {
		this.mltyDiscpCode = mltyDiscpCode;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
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
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

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
	 * @return the militarySeq
	 */
	public Long getMilitarySeq() {
		return militarySeq;
	}

	/**
	 * @param militarySeq
	 *            the militarySeq to set
	 */
	public void setMilitarySeq(Long militarySeq) {
		this.militarySeq = militarySeq;
	}

	/**
	 * @return the mltyDiscpSeq
	 */
	public Long getMltyDiscpSeq() {
		return mltyDiscpSeq;
	}

	/**
	 * @param mltyDiscpSeq
	 *            the mltyDiscpSeq to set
	 */
	public void setMltyDiscpSeq(Long mltyDiscpSeq) {
		this.mltyDiscpSeq = mltyDiscpSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
}
