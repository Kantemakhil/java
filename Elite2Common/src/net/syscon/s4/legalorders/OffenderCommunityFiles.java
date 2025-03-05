package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCommunityFiles extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderFileSeq")
	private int offenderFileSeq;
	@JsonProperty("offenderId")
	private long offenderId;
	@JsonProperty("fileType")
	private String fileType;
	@JsonProperty("fileSubType")
	private String fileSubType;
	@JsonProperty("offenderFileNum")
	private long offenderFileNum;
	@JsonProperty("volumeSeq")
	private int volumeSeq;
	@JsonProperty("fileReference")
	private String fileReference;
	@JsonProperty("fileCreateDate")
	private Date fileCreateDate;
	@JsonProperty("holdingAgyLocId")
	private String holdingAgyLocId;
	@JsonProperty("holdingStaffId")
	private long holdingStaffId;
	@JsonProperty("nonOfficerStatus")
	private String nonOfficerStatus;
	@JsonProperty("storage")
	private String storage;
	@JsonProperty("resubmissionDate")
	private Date resubmissionDate;
	@JsonProperty("creationDate")
	private Date creationDate;
	@JsonProperty("creationUser")
	private String creationUser;
	@JsonProperty("caseloaType")
	private String caseloaType;
	@JsonProperty("transAgyLocId")
	private String transAgyLocId;
	@JsonProperty("transactionId")
	private long transactionId;
	@JsonProperty("transferFlag")
	private String transferFlag;
	@JsonProperty("closeFileNo")
	private String closeFileNo;
	@JsonProperty("dspDescription2")
	private String dspDescription2;
	@JsonProperty("dspDescription3")
	private String dspDescription3;
	@JsonProperty("dspDescription4")
	private String dspDescription4;
	@JsonProperty("dspDescription")
	private String dspDescription;
	@JsonProperty("transLocation")
	private String transLocation;
	@JsonProperty("requestChkbx")
	private boolean requestChkbx;
	@JsonProperty("transferChkbx")
	private boolean transferChkbx;
	@JsonProperty("drvOffenderId")
	private String drvOffenderId;
	
	private String sealFlag;
	

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public int getOffenderFileSeq() {
		return offenderFileSeq;
	}

	public void setOffenderFileSeq(int offenderFileSeq) {
		this.offenderFileSeq = offenderFileSeq;
	}

	public long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(long offenderId) {
		this.offenderId = offenderId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSubType() {
		return fileSubType;
	}

	public void setFileSubType(String fileSubType) {
		this.fileSubType = fileSubType;
	}

	public long getOffenderFileNum() {
		return offenderFileNum;
	}

	public void setOffenderFileNum(long offenderFileNum) {
		this.offenderFileNum = offenderFileNum;
	}

	public int getVolumeSeq() {
		return volumeSeq;
	}

	public void setVolumeSeq(int volumeSeq) {
		this.volumeSeq = volumeSeq;
	}

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	public Date getFileCreateDate() {
		return fileCreateDate;
	}

	public void setFileCreateDate(Date fileCreateDate) {
		this.fileCreateDate = fileCreateDate;
	}

	public String getHoldingAgyLocId() {
		return holdingAgyLocId;
	}

	public void setHoldingAgyLocId(String holdingAgyLocId) {
		this.holdingAgyLocId = holdingAgyLocId;
	}

	public long getHoldingStaffId() {
		return holdingStaffId;
	}

	public void setHoldingStaffId(long holdingStaffId) {
		this.holdingStaffId = holdingStaffId;
	}

	public String getNonOfficerStatus() {
		return nonOfficerStatus;
	}

	public void setNonOfficerStatus(String nonOfficerStatus) {
		this.nonOfficerStatus = nonOfficerStatus;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Date getResubmissionDate() {
		return resubmissionDate;
	}

	public void setResubmissionDate(Date resubmissionDate) {
		this.resubmissionDate = resubmissionDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getCaseloaType() {
		return caseloaType;
	}

	public void setCaseloaType(String caseloaType) {
		this.caseloaType = caseloaType;
	}

	public String getTransAgyLocId() {
		return transAgyLocId;
	}

	public void setTransAgyLocId(String transAgyLocId) {
		this.transAgyLocId = transAgyLocId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransferFlag() {
		return transferFlag;
	}

	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}

	public String getCloseFileNo() {
		return closeFileNo;
	}

	public void setCloseFileNo(String closeFileNo) {
		this.closeFileNo = closeFileNo;
	}

	public String getDspDescription2() {
		return dspDescription2;
	}

	public void setDspDescription2(String dspDescription2) {
		this.dspDescription2 = dspDescription2;
	}

	public String getDspDescription3() {
		return dspDescription3;
	}

	public void setDspDescription3(String dspDescription3) {
		this.dspDescription3 = dspDescription3;
	}

	public String getDspDescription4() {
		return dspDescription4;
	}

	public void setDspDescription4(String dspDescription4) {
		this.dspDescription4 = dspDescription4;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getTransLocation() {
		return transLocation;
	}

	public void setTransLocation(String transLocation) {
		this.transLocation = transLocation;
	}

	public boolean isRequestChkbx() {
		return requestChkbx;
	}

	public void setRequestChkbx(boolean requestChkbx) {
		this.requestChkbx = requestChkbx;
	}

	public boolean isTransferChkbx() {
		return transferChkbx;
	}

	public void setTransferChkbx(boolean transferChkbx) {
		this.transferChkbx = transferChkbx;
	}

	public String getDrvOffenderId() {
		return drvOffenderId;
	}

	public void setDrvOffenderId(String drvOffenderId) {
		this.drvOffenderId = drvOffenderId;
	}
	
	private String modifyUserId;

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Override
	public String toString() {
		return "OffenderCommunityFiles [offenderFileSeq=" + offenderFileSeq + ", offenderId=" + offenderId
				+ ", fileType=" + fileType + ", fileSubType=" + fileSubType + ", offenderFileNum=" + offenderFileNum
				+ ", volumeSeq=" + volumeSeq + ", fileReference=" + fileReference + ", fileCreateDate=" + fileCreateDate
				+ ", holdingAgyLocId=" + holdingAgyLocId + ", holdingStaffId=" + holdingStaffId + ", nonOfficerStatus="
				+ nonOfficerStatus + ", storage=" + storage + ", resubmissionDate=" + resubmissionDate
				+ ", creationDate=" + creationDate + ", creationUser=" + creationUser + ", caseloaType=" + caseloaType
				+ ", transAgyLocId=" + transAgyLocId + ", transactionId=" + transactionId + ", transferFlag="
				+ transferFlag + ", closeFileNo=" + closeFileNo + "]";
	}

}
