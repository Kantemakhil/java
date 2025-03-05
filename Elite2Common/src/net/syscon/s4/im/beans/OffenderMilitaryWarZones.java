package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderMilitaryWarZones extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("warZoneCode")
	private String warZoneCode;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("militarySeq")
	private Long militarySeq;

	@JsonProperty("warSeq")
	private Long warSeq;

	/**
	 * Creates new OffenderMilitaryWarZones class Object
	 */
	public OffenderMilitaryWarZones() {
		// OffenderMilitaryWarZones
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
	 * @return the warZoneCode
	 */
	public String getWarZoneCode() {
		return warZoneCode;
	}

	/**
	 * @param warZoneCode
	 *            the warZoneCode to set
	 */
	public void setWarZoneCode(String warZoneCode) {
		this.warZoneCode = warZoneCode;
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
	 * @return the warSeq
	 */
	public Long getWarSeq() {
		return warSeq;
	}

	/**
	 * @param warSeq
	 *            the warSeq to set
	 */
	public void setWarSeq(Long warSeq) {
		this.warSeq = warSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
