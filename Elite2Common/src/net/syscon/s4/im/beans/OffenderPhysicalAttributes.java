package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderPhysicalAttributes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("heightCm")
	private BigDecimal heightCm;

	@JsonProperty("heightFt")
	private BigDecimal heightFt;

	@JsonProperty("heightIn")
	private BigDecimal heightIn;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("weightKg")
	private BigDecimal weightKg;

	@JsonProperty("weightLbs")
	private BigDecimal weightLbs;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("attributeSeq")
	@NotNull
	private Long attributeSeq;

	/**
	 * Creates new Images class Object
	 */
	public OffenderPhysicalAttributes() {
		// OffenderPhysicalAttributes
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
	public BigDecimal getHeightCm() {
		return heightCm;
	}

	/**
	 *
	 * @param heightCm
	 */
	public void setHeightCm(final BigDecimal heightCm) {
		this.heightCm = heightCm;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getHeightFt() {
		return heightFt;
	}

	/**
	 *
	 * @param heightFt
	 */
	public void setHeightFt(final BigDecimal heightFt) {
		this.heightFt = heightFt;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getHeightIn() {
		return heightIn;
	}

	/**
	 *
	 * @param heightIn
	 */
	public void setHeightIn(final BigDecimal heightIn) {
		this.heightIn = heightIn;
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
	public BigDecimal getWeightKg() {
		return weightKg;
	}

	/**
	 *
	 * @param weightKg
	 */
	public void setWeightKg(final BigDecimal weightKg) {
		this.weightKg = weightKg;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getWeightLbs() {
		return weightLbs;
	}

	/**
	 *
	 * @param weightLbs
	 */
	public void setWeightLbs(final BigDecimal weightLbs) {
		this.weightLbs = weightLbs;
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
	public Long getAttributeSeq() {
		return attributeSeq;
	}

	/**
	 *
	 * @param attributeSeq
	 */
	public void setAttributeSeq(final Long attributeSeq) {
		this.attributeSeq = attributeSeq;
	}

}