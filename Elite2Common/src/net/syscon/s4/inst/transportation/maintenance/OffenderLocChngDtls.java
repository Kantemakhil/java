package net.syscon.s4.inst.transportation.maintenance;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderLocChngDtls extends BaseModel implements Serializable {

	@JsonProperty("offenderBookId")
	public Integer offenderBookId;
	
	@JsonProperty("locationSeq")
	public Integer locationSeq;
	
	@JsonProperty("detailSeq")
	public Integer detailSeq;
	
	@JsonProperty("statusCode")
	public String statusCode;
	
	@JsonProperty("statusComment")
	public String  statusComment;
	
	@JsonProperty("recordedBy")
	public String recordedBy;
	
	@JsonProperty("recordedDate")
	public Date recordedDate;
	
	@JsonProperty("appRsn")
	public String appRsn;
	
	@JsonProperty("txnStatus")
	public String txnStatus;
	
	@JsonProperty("txnRsn")
	public String txnRsn;
	
	@JsonProperty("createUserId")
	public String createUserId;
	
	@JsonProperty("createDatetime")
	public Date createDatetime;
	
	@JsonProperty("sealFlag")
    public String sealFlag;
	
	@JsonProperty("modifyDatetime")
	public Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	 public String modifyUserId;
	
	@JsonProperty("choice")
	 public String choice;
	
	@JsonProperty("nonAssoFlag")
	 public String nonAssoFlag;
	
	@JsonProperty("appRole")
	 public String appRole;
	
	@JsonProperty("cancRole")
	 public String cancRole;
	
	@JsonProperty("recoredTime")
	private Date recoredTime;

	public Date getRecoredTime() {
		return recoredTime;
	}

	public void setRecoredTime(Date recoredTime) {
		this.recoredTime = recoredTime;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getLocationSeq() {
		return locationSeq;
	}

	public void setLocationSeq(Integer locationSeq) {
		this.locationSeq = locationSeq;
	}

	public Integer getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(Integer detailSeq) {
		this.detailSeq = detailSeq;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusComment() {
		return statusComment;
	}

	public void setStatusComment(String statusComment) {
		this.statusComment = statusComment;
	}

	public String getRecordedBy() {
		return recordedBy;
	}

	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}

	public Date getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}

	public String getAppRsn() {
		return appRsn;
	}

	public void setAppRsn(String appRsn) {
		this.appRsn = appRsn;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnRsn() {
		return txnRsn;
	}

	public void setTxnRsn(String txnRsn) {
		this.txnRsn = txnRsn;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getNonAssoFlag() {
		return nonAssoFlag;
	}

	public void setNonAssoFlag(String nonAssoFlag) {
		this.nonAssoFlag = nonAssoFlag;
	}

	public String getAppRole() {
		return appRole;
	}

	public String getCancRole() {
		return cancRole;
	}

	public void setAppRole(String appRole) {
		this.appRole = appRole;
	}

	public void setCancRole(String cancRole) {
		this.cancRole = cancRole;
	}
}
