package net.syscon.s4.triggers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class CaseNotes extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;
	private Long noteSeq;
	private Integer staffId;
	private Date noteDate;
	private String casNotType;
	private String reason;
	private String text;
	private Date contactDate;
	private String checkBox1;
	private String checkBox2;
	private String checkBox3;
	private String checkBox4;
	private String checkBox5;
	private String amendmentFlag;
	private Date noteTime;
	private BigDecimal scheduleId;
	private Date contactEndDate;
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

	public Long getNoteSeq() {
		return noteSeq;
	}

	public void setNoteSeq(Long noteSeq) {
		this.noteSeq = noteSeq;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public String getCasNotType() {
		return casNotType;
	}

	public void setCasNotType(String casNotType) {
		this.casNotType = casNotType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	public String getCheckBox1() {
		return checkBox1;
	}

	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public String getCheckBox2() {
		return checkBox2;
	}

	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	public String getCheckBox3() {
		return checkBox3;
	}

	public void setCheckBox3(String checkBox3) {
		this.checkBox3 = checkBox3;
	}

	public String getCheckBox4() {
		return checkBox4;
	}

	public void setCheckBox4(String checkBox4) {
		this.checkBox4 = checkBox4;
	}

	public String getCheckBox5() {
		return checkBox5;
	}

	public void setCheckBox5(String checkBox5) {
		this.checkBox5 = checkBox5;
	}

	public String getAmendmentFlag() {
		return amendmentFlag;
	}

	public void setAmendmentFlag(String amendmentFlag) {
		this.amendmentFlag = amendmentFlag;
	}

	public Date getNoteTime() {
		return noteTime;
	}

	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}

	public BigDecimal getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(BigDecimal scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Date getContactEndDate() {
		return contactEndDate;
	}

	public void setContactEndDate(Date contactEndDate) {
		this.contactEndDate = contactEndDate;
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
