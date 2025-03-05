package net.syscon.s4.im.incidentsoic.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OicHearingResults extends BaseModel implements Serializable {

@JsonProperty("oicHearingId")
@NotNull
private BigDecimal oicHearingId;

@JsonProperty("resultSeq")
@NotNull
private BigDecimal resultSeq;

@JsonProperty("agencyIncidentId")
@NotNull
private BigDecimal agencyIncidentId;

@JsonProperty("chargeSeq")
@NotNull
private BigDecimal chargeSeq;

@JsonProperty("pleaFindingCode")
@NotNull
@Size(max=12)
private String pleaFindingCode;

@JsonProperty("findingCode")
@NotNull
@Size(max=12)
private String findingCode;

@JsonProperty("createDatetime")
@NotNull
private Date createDatetime;

@JsonProperty("createUserId")
@NotNull
@Size(max=32)
private String createUserId;

@JsonProperty("modifyDatetime")
private Date modifyDatetime;

@JsonProperty("modifyUserId")
@Size(max=32)
private String modifyUserId;

@JsonProperty("oicOffenceId")
@NotNull
private BigDecimal oicOffenceId;

@JsonProperty("sealFlag")
@Size(max=1)
private String sealFlag;

@JsonProperty("oicResultOffenceId")
@NotNull
private BigDecimal oicResultOffenceId;

@JsonProperty("oicOffenceCode")
private String oicOffenceCode;

@JsonProperty("type")
private String type;

@JsonProperty("chargeDescription")
private String chargeDescription;

@JsonProperty("resultOicOffenceCode")
private String resultOicOffenceCode;

@JsonProperty("typeResult")
private String typeResult;

@JsonProperty("chargeDescriptionResult")
private String chargeDescriptionResult;

private boolean inserted;
private String errorMessage;

@JsonProperty("code")
private Integer code;

@JsonProperty("description")
private String description;



@JsonProperty("oicIncidentId")
private Integer oicIncidentId;

@JsonProperty("hearingDate")
private Date hearingDate;

public Integer getOicIncidentId() {
	return oicIncidentId;
}


public void setOicIncidentId(Integer oicIncidentId) {
	this.oicIncidentId = oicIncidentId;
}


public Date getHearingDate() {
	return hearingDate;
}


public void setHearingDate(Date hearingDate) {
	this.hearingDate = hearingDate;
}


public Integer getCode() {
	return code;
}


public void setCode(Integer code) {
	this.code = code;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


/**
 * Creates new OicHearingResults class Object
 */
public OicHearingResults() {
	
	//OicHearingResults
}


/**
 * @return the oicHearingId
 */
public BigDecimal getOicHearingId() {
	return oicHearingId;
}


/**
 * @param oicHearingId the oicHearingId to set
 */
public void setOicHearingId(BigDecimal oicHearingId) {
	this.oicHearingId = oicHearingId;
}


/**
 * @return the resultSeq
 */
public BigDecimal getResultSeq() {
	return resultSeq;
}


/**
 * @param resultSeq the resultSeq to set
 */
public void setResultSeq(BigDecimal resultSeq) {
	this.resultSeq = resultSeq;
}


/**
 * @return the agencyIncidentId
 */
public BigDecimal getAgencyIncidentId() {
	return agencyIncidentId;
}


/**
 * @param agencyIncidentId the agencyIncidentId to set
 */
public void setAgencyIncidentId(BigDecimal agencyIncidentId) {
	this.agencyIncidentId = agencyIncidentId;
}


/**
 * @return the chargeSeq
 */
public BigDecimal getChargeSeq() {
	return chargeSeq;
}


/**
 * @param chargeSeq the chargeSeq to set
 */
public void setChargeSeq(BigDecimal chargeSeq) {
	this.chargeSeq = chargeSeq;
}


/**
 * @return the pleaFindingCode
 */
public String getPleaFindingCode() {
	return pleaFindingCode;
}


/**
 * @param pleaFindingCode the pleaFindingCode to set
 */
public void setPleaFindingCode(String pleaFindingCode) {
	this.pleaFindingCode = pleaFindingCode;
}


/**
 * @return the findingCode
 */
public String getFindingCode() {
	return findingCode;
}


/**
 * @param findingCode the findingCode to set
 */
public void setFindingCode(String findingCode) {
	this.findingCode = findingCode;
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
public void setCreateDatetime(Date createDatetime) {
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
public void setCreateUserId(String createUserId) {
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
public void setModifyDatetime(Date modifyDatetime) {
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
public void setModifyUserId(String modifyUserId) {
	this.modifyUserId = modifyUserId;
}


/**
 * @return the oicOffenceId
 */
public BigDecimal getOicOffenceId() {
	return oicOffenceId;
}


/**
 * @param oicOffenceId the oicOffenceId to set
 */
public void setOicOffenceId(BigDecimal oicOffenceId) {
	this.oicOffenceId = oicOffenceId;
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
 * @return the oicResultOffenceId
 */
public BigDecimal getOicResultOffenceId() {
	return oicResultOffenceId;
}


/**
 * @param oicResultOffenceId the oicResultOffenceId to set
 */
public void setOicResultOffenceId(BigDecimal oicResultOffenceId) {
	this.oicResultOffenceId = oicResultOffenceId;
}


/**
 * @return the inserted
 */
public boolean isInserted() {
	return inserted;
}


/**
 * @param inserted the inserted to set
 */
public void setInserted(boolean inserted) {
	this.inserted = inserted;
}


/**
 * @return the errorMessage
 */
public String getErrorMessage() {
	return errorMessage;
}


/**
 * @param errorMessage the errorMessage to set
 */
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}


/**
 * @return the oicOffenceCode
 */
public String getOicOffenceCode() {
	return oicOffenceCode;
}


/**
 * @param oicOffenceCode the oicOffenceCode to set
 */
public void setOicOffenceCode(String oicOffenceCode) {
	this.oicOffenceCode = oicOffenceCode;
}


/**
 * @return the type
 */
public String getType() {
	return type;
}


/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}


/**
 * @return the chargeDescription
 */
public String getChargeDescription() {
	return chargeDescription;
}


/**
 * @param chargeDescription the chargeDescription to set
 */
public void setChargeDescription(String chargeDescription) {
	this.chargeDescription = chargeDescription;
}


/**
 * @return the resultOicOffenceCode
 */
public String getResultOicOffenceCode() {
	return resultOicOffenceCode;
}


/**
 * @param resultOicOffenceCode the resultOicOffenceCode to set
 */
public void setResultOicOffenceCode(String resultOicOffenceCode) {
	this.resultOicOffenceCode = resultOicOffenceCode;
}


/**
 * @return the typeResult
 */
public String getTypeResult() {
	return typeResult;
}


/**
 * @param typeResult the typeResult to set
 */
public void setTypeResult(String typeResult) {
	this.typeResult = typeResult;
}


/**
 * @return the chargeDescriptionResult
 */
public String getChargeDescriptionResult() {
	return chargeDescriptionResult;
}


/**
 * @param chargeDescriptionResult the chargeDescriptionResult to set
 */
public void setChargeDescriptionResult(String chargeDescriptionResult) {
	this.chargeDescriptionResult = chargeDescriptionResult;
}


}
