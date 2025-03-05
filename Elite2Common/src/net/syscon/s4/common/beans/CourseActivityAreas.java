package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseActivityAreas extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("crsActyId")
	private BigDecimal crsActyId;

	@JsonProperty("areaCode")
	private String areaCode;

	@JsonProperty("areaClass")
	private String areaClass;

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

	@JsonProperty("caseLoadType")
	private String caseLoadType;
	
	@JsonProperty("seqOne")
	private BigDecimal seqOne;


	public final BigDecimal getSeqOne() {
		return seqOne;
	}

	public final void setSeqOne(BigDecimal seqOne) {
		this.seqOne = seqOne;
	}

	public BigDecimal getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(BigDecimal crsActyId) {
		this.crsActyId = crsActyId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getAreaClass() {
		return areaClass;
	}

	public void setAreaClass(String areaClass) {
		this.areaClass = areaClass;
	}

	public String getCaseLoadType() {
		return caseLoadType;
	}

	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}

}
