package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;


/**
 * The persistent class for the CORPORATE_TYPES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CorporateTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("corporateId")
	private BigDecimal corporateId;
	@JsonProperty("corporateType")
	private String corporateType;
	@JsonProperty("nbtCorporateType")
	private String nbtCorporateType;
	
	
	
	public CorporateTypes() {
		//		CorporateTypes
	}


	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}


	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}


	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}


	/**
	 * @return the corporateId
	 */
	public BigDecimal getCorporateId() {
		return corporateId;
	}


	/**
	 * @param corporateId the corporateId to set
	 */
	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
	}


	/**
	 * @return the corporateType
	 */
	public String getCorporateType() {
		return corporateType;
	}


	/**
	 * @param corporateType the corporateType to set
	 */
	public void setCorporateType(final String corporateType) {
		this.corporateType = corporateType;
	}


	/**
	 * @return the nbtCorporateType
	 */
	public String getNbtCorporateType() {
		return nbtCorporateType;
	}


	/**
	 * @param nbtCorporateType the nbtCorporateType to set
	 */
	public void setNbtCorporateType(final String nbtCorporateType) {
		this.nbtCorporateType = nbtCorporateType;
	}


}