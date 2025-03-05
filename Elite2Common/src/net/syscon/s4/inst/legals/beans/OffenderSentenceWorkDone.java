package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceWorkDone extends BaseModel implements Serializable {
	private BigDecimal offenderBookId;
	private int sentenceSeq;
	private int consecToSentenceSeq;
	private String orderType;
	private Long workHours;
	private Long attendenceHours;
	private Date startDate;
	private Date expiryDate;
	private Long workHoursDone;
	private Long attendenceHoursDone;
	private String status;
	private String tableName;
	private Date creationDate;
	private String creationUser;
	private Long workOverrideDone;
	private Long workOverride;
	private Long otherOverrideDone;
	private Long otherOverride;
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public int getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(int sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public int getConsecToSentenceSeq() {
		return consecToSentenceSeq;
	}
	public void setConsecToSentenceSeq(int consecToSentenceSeq) {
		this.consecToSentenceSeq = consecToSentenceSeq;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getWorkHours() {
		return workHours;
	}
	public void setWorkHours(Long workHours) {
		this.workHours = workHours;
	}
	public Long getAttendenceHours() {
		return attendenceHours;
	}
	public void setAttendenceHours(Long attendenceHours) {
		this.attendenceHours = attendenceHours;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getWorkHoursDone() {
		return workHoursDone;
	}
	public void setWorkHoursDone(Long workHoursDone) {
		this.workHoursDone = workHoursDone;
	}
	public Long getAttendenceHoursDone() {
		return attendenceHoursDone;
	}
	public void setAttendenceHoursDone(Long attendenceHoursDone) {
		this.attendenceHoursDone = attendenceHoursDone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	public Long getWorkOverrideDone() {
		return workOverrideDone;
	}
	public void setWorkOverrideDone(Long workOverrideDone) {
		this.workOverrideDone = workOverrideDone;
	}
	public Long getWorkOverride() {
		return workOverride;
	}
	public void setWorkOverride(Long workOverride) {
		this.workOverride = workOverride;
	}
	public Long getOtherOverrideDone() {
		return otherOverrideDone;
	}
	public void setOtherOverrideDone(Long otherOverrideDone) {
		this.otherOverrideDone = otherOverrideDone;
	}
	public Long getOtherOverride() {
		return otherOverride;
	}
	public void setOtherOverride(Long otherOverride) {
		this.otherOverride = otherOverride;
	}
	

}
