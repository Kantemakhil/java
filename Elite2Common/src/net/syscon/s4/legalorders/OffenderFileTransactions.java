package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderFileTransactions extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("transactionId")
	private long transactionId;
	@JsonProperty("offenderFileSeq")
	private long offenderFileSeq;
	@JsonProperty("offenderId")
	private long offenderId;
	@JsonProperty("staffIdTo")
	private long staffIdTo;
	@JsonProperty("staffIdFrom")
	private long staffIdFrom;
	@JsonProperty("nonOfficerTo")
	private String nonOfficerTo;
	@JsonProperty("nonOfficeFrom")
	private String nonOfficeFrom;
	@JsonProperty("agyLocIdTo")
	private String agyLocIdTo;
	@JsonProperty("agyLocIdFrom")
	private String agyLocIdFrom;
	@JsonProperty("transactionDate")
	private Date transactionDate;
	@JsonProperty("transferDate")
	private Date transferDate;
	@JsonProperty("fileTransType")
	private String fileTransType;
	@JsonProperty("confirmed")
	private String confirmed;
	@JsonProperty("fileTransComment")
	private String fileTransComment;
	@JsonProperty("creationDate")
	private Date creationDate;
	@JsonProperty("creationUser")
	private String creationUser;
	@JsonProperty("drvTransactionId")
	private String drvTransactionId;
	@JsonProperty("drvTransactionId2")
	private String drvTransactionId2;
	@JsonProperty("dspTransactionDate")
	private String dspTransactionDate;
	@JsonProperty("dspDescription3")
	private String dspDescription3;
	@JsonProperty("dspDescription5")
	private String dspDescription5;
	@JsonProperty("dspDescription")
	private String dspDescription;
	@JsonProperty("dspDescription4")
	private String dspDescription4;
	@JsonProperty("dspDescription2")
	private String dspDescription2;
	@JsonProperty("drvOffenderBookId")
	private String drvOffenderBookId;
	
	@JsonProperty("createUserId")
	private String createUserId;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getOffenderFileSeq() {
		return offenderFileSeq;
	}

	public void setOffenderFileSeq(long offenderFileSeq) {
		this.offenderFileSeq = offenderFileSeq;
	}

	public long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(long offenderId) {
		this.offenderId = offenderId;
	}

	public long getStaffIdTo() {
		return staffIdTo;
	}

	public void setStaffIdTo(long staffIdTo) {
		this.staffIdTo = staffIdTo;
	}

	public long getStaffIdFrom() {
		return staffIdFrom;
	}

	public void setStaffIdFrom(long staffIdFrom) {
		this.staffIdFrom = staffIdFrom;
	}

	public String getNonOfficeFrom() {
		return nonOfficeFrom;
	}

	public void setNonOfficeFrom(String nonOfficeFrom) {
		this.nonOfficeFrom = nonOfficeFrom;
	}

	public String getAgyLocIdTo() {
		return agyLocIdTo;
	}

	public void setAgyLocIdTo(String agyLocIdTo) {
		this.agyLocIdTo = agyLocIdTo;
	}

	public String getAgyLocIdFrom() {
		return agyLocIdFrom;
	}

	public void setAgyLocIdFrom(String agyLocIdFrom) {
		this.agyLocIdFrom = agyLocIdFrom;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getFileTransType() {
		return fileTransType;
	}

	public void setFileTransType(String fileTransType) {
		this.fileTransType = fileTransType;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getFileTransComment() {
		return fileTransComment;
	}

	public void setFileTransComment(String fileTransComment) {
		this.fileTransComment = fileTransComment;
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

	public String getNonOfficerTo() {
		return nonOfficerTo;
	}

	public void setNonOfficerTo(String nonOfficerTo) {
		this.nonOfficerTo = nonOfficerTo;
	}

	public String getDrvTransactionId() {
		return drvTransactionId;
	}

	public void setDrvTransactionId(String drvTransactionId) {
		this.drvTransactionId = drvTransactionId;
	}

	public String getDrvTransactionId2() {
		return drvTransactionId2;
	}

	public void setDrvTransactionId2(String drvTransactionId2) {
		this.drvTransactionId2 = drvTransactionId2;
	}

	public String getDspTransactionDate() {
		return dspTransactionDate;
	}

	public void setDspTransactionDate(String dspTransactionDate) {
		this.dspTransactionDate = dspTransactionDate;
	}

	public String getDspDescription3() {
		return dspDescription3;
	}

	public void setDspDescription3(String dspDescription3) {
		this.dspDescription3 = dspDescription3;
	}

	public String getDspDescription5() {
		return dspDescription5;
	}

	public void setDspDescription5(String dspDescription5) {
		this.dspDescription5 = dspDescription5;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getDspDescription4() {
		return dspDescription4;
	}

	public void setDspDescription4(String dspDescription4) {
		this.dspDescription4 = dspDescription4;
	}

	public String getDspDescription2() {
		return dspDescription2;
	}

	public void setDspDescription2(String dspDescription2) {
		this.dspDescription2 = dspDescription2;
	}

	public String getDrvOffenderBookId() {
		return drvOffenderBookId;
	}

	public void setDrvOffenderBookId(String drvOffenderBookId) {
		this.drvOffenderBookId = drvOffenderBookId;
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

	@Override
	public String toString() {
		return "OffenderFileTransactions [transactionId=" + transactionId + ", offenderFileSeq=" + offenderFileSeq
				+ ", offenderId=" + offenderId + ", staffIdTo=" + staffIdTo + ", staffIdFrom=" + staffIdFrom
				+ ", nonOfficeFrom=" + nonOfficeFrom + ", agyLocIdTo=" + agyLocIdTo + ", agyLocIdFrom=" + agyLocIdFrom
				+ ", transactionDate=" + transactionDate + ", transferDate=" + transferDate + ", fileTransType="
				+ fileTransType + ", confirmed=" + confirmed + ", fileTransComment=" + fileTransComment
				+ ", creationDate=" + creationDate + ", creationUser=" + creationUser + "]";
	}

}
