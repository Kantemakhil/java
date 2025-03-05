package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OicOffenceIndicators extends BaseModel implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("oicOffenceIndicatorId")
private Integer oicOffenceIndicatorId;
@JsonProperty("modifyUserId")
private String modifyUserId;
@JsonProperty("createDatetime")
private Date createDatetime;

@JsonProperty("createUserId")
private String createUserId;

@JsonProperty("modifyDatetime")
private Date modifyDatetime;
@JsonProperty("oicOffenceId")
private Integer oicOffenceId;
@JsonProperty("sealFlag")
private String sealFlag;
@JsonProperty("indicatorCode")
private String indicatorCode;
public Integer getOicOffenceIndicatorId() {
	return oicOffenceIndicatorId;
}
public void setOicOffenceIndicatorId(Integer oicOffenceIndicatorId) {
	this.oicOffenceIndicatorId = oicOffenceIndicatorId;
}
public String getModifyUserId() {
	return modifyUserId;
}
public void setModifyUserId(String modifyUserId) {
	this.modifyUserId = modifyUserId;
}
public String getCreateUserId() {
	return createUserId;
}
public void setCreateUserId(String createUserId) {
	this.createUserId = createUserId;
}

public Integer getOicOffenceId() {
	return oicOffenceId;
}
public void setOicOffenceId(Integer oicOffenceId) {
	this.oicOffenceId = oicOffenceId;
}
public String getSealFlag() {
	return sealFlag;
}
public void setSealFlag(String sealFlag) {
	this.sealFlag = sealFlag;
}
public String getIndicatorCode() {
	return indicatorCode;
}
public void setIndicatorCode(String indicatorCode) {
	this.indicatorCode = indicatorCode;
}
public Date getCreateDatetime() {
	return createDatetime;
}
public void setCreateDatetime(Date createDatetime) {
	this.createDatetime = createDatetime;
}
public Date getModifyDatetime() {
	return modifyDatetime;
}
public void setModifyDatetime(Date modifyDatetime) {
	this.modifyDatetime = modifyDatetime;
}

}
