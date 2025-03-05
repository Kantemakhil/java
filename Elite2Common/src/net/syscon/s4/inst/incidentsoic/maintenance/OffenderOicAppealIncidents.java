package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppealIncidents extends BaseModel implements Serializable {

	@JsonProperty("oicApprealId")
	private Integer oicApprealId;

	@JsonProperty("oicHearingId")
	private Integer oicHearingId;

	@JsonProperty("resultSeq")
	private Integer resultSeq;

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

	@JsonProperty("offenderBookingId")
	private BigDecimal offenderBookingId;

	@JsonProperty("chargedOffenceCode")
	private String chargedOffenceCode;

	@JsonProperty("offenceDescription")
	private String offenceDescription;

	@JsonProperty("agencyIncidentId")
	private BigDecimal agencyIncidentId;

	@JsonProperty("chargeSeq")
	private BigDecimal chargeSeq;

	@JsonProperty("oicOffenceId")
	private BigDecimal oicOffenceId;

	@JsonProperty("oicIncidentId")
	private Integer oicIncidentId;

	@JsonProperty("hearingDate")
	private Date hearingDate;

	public Date getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	public Integer getOicIncidentId() {
		return oicIncidentId;
	}

	public void setOicIncidentId(Integer oicIncidentId) {
		this.oicIncidentId = oicIncidentId;
	}

	public BigDecimal getOicOffenceId() {
		return oicOffenceId;
	}

	public void setOicOffenceId(BigDecimal oicOffenceId) {
		this.oicOffenceId = oicOffenceId;
	}

	public BigDecimal getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public BigDecimal getAgencyIncidentId() {
		return agencyIncidentId;
	}

	public void setAgencyIncidentId(BigDecimal agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	public String getChargedOffenceCode() {
		return chargedOffenceCode;
	}

	public void setChargedOffenceCode(String chargedOffenceCode) {
		this.chargedOffenceCode = chargedOffenceCode;
	}

	public String getOffenceDescription() {
		return offenceDescription;
	}

	public void setOffenceDescription(String offenceDescription) {
		this.offenceDescription = offenceDescription;
	}

	public BigDecimal getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(BigDecimal offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public Integer getOicApprealId() {
		return oicApprealId;
	}

	public void setOicApprealId(Integer oicApprealId) {
		this.oicApprealId = oicApprealId;
	}

	public Integer getOicHearingId() {
		return oicHearingId;
	}

	public void setOicHearingId(Integer oicHearingId) {
		this.oicHearingId = oicHearingId;
	}

	public Integer getResultSeq() {
		return resultSeq;
	}

	public void setResultSeq(Integer resultSeq) {
		this.resultSeq = resultSeq;
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
}
