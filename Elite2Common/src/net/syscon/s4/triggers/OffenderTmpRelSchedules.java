package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderTmpRelSchedules extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;
	private Long sessionId;
	private String offenderIdDisplay;
	private String lastName;
	private String firstName;
	private Date columnFourDate;
	private Date columnFiveDate;
	private Date columnSixDate;
	private Date releaseDate;
	private String commentText;
	private String movementType;
	private String movementReasonCode;
	private Date columnSevenDate;
	private Date columnEightDate;
	private Date columnNineDate;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getColumnFourDate() {
		return columnFourDate;
	}

	public void setColumnFourDate(Date columnFourDate) {
		this.columnFourDate = columnFourDate;
	}

	public Date getColumnFiveDate() {
		return columnFiveDate;
	}

	public void setColumnFiveDate(Date columnFiveDate) {
		this.columnFiveDate = columnFiveDate;
	}

	public Date getColumnSixDate() {
		return columnSixDate;
	}

	public void setColumnSixDate(Date columnSixDate) {
		this.columnSixDate = columnSixDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public Date getColumnSevenDate() {
		return columnSevenDate;
	}

	public void setColumnSevenDate(Date columnSevenDate) {
		this.columnSevenDate = columnSevenDate;
	}

	public Date getColumnEightDate() {
		return columnEightDate;
	}

	public void setColumnEightDate(Date columnEightDate) {
		this.columnEightDate = columnEightDate;
	}

	public Date getColumnNineDate() {
		return columnNineDate;
	}

	public void setColumnNineDate(Date columnNineDate) {
		this.columnNineDate = columnNineDate;
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
