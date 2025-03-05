package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StgCaseNotes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("noteDate")
	private Date noteDate;

	@JsonProperty("noteSubtype")
	private String noteSubtype;

	@JsonProperty("noteTime")
	private Date noteTime;

	@JsonProperty("noteType")
	private String noteType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("text")
	private String text;

	@JsonProperty("stgSeq")
	private BigDecimal stgSeq;

	@JsonProperty("stgId")
	private Long stgId;

	@JsonProperty("noteSeq")
	private Long noteSeq;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("newText")
	private String newText;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("ptrId")
	private BigDecimal ptrId;

	@JsonProperty("newValue")
	private String newValue;

	@JsonProperty("assessmentSeq")
	private String assessmentSeq;

	@JsonProperty("itemSeq")
	private String itemSeq;

	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue
	 *            the newValue to set
	 */
	public void setNewValue(final String newValue) {
		this.newValue = newValue;
	}

	/**
	 * @return the ptrId
	 */
	public BigDecimal getPtrId() {
		return ptrId;
	}

	/**
	 * @param ptrId
	 *            the ptrId to set
	 */
	public void setPtrId(final BigDecimal ptrId) {
		this.ptrId = ptrId;
	}

	/**
	 * @return the itemSeq
	 */

	public String getItemSeq() {
		return itemSeq;
	}

	/**
	 * @param itemSeq
	 *            the itemSeq to set
	 */
	public void setItemSeq(final String itemSeq) {
		this.itemSeq = itemSeq;
	}

	/**
	 * @return the assessmentSeq
	 */
	public String getAssessmentSeq() {
		return assessmentSeq;
	}

	/**
	 * @param assessmentSeq
	 *            the assessmentSeq to set
	 */
	public void setAssessmentSeq(final String assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getcreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setcreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getmodifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setmodifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the noteDate
	 */
	public Date getNoteDate() {
		return noteDate;
	}

	/**
	 * @param noteDate
	 *            the noteDate to set
	 */
	public void setNoteDate(final Date noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * @return the noteSubtype
	 */
	public String getNoteSubtype() {
		return noteSubtype;
	}

	/**
	 * @param noteSubtype
	 *            the noteSubtype to set
	 */
	public void setNoteSubtype(final String noteSubtype) {
		this.noteSubtype = noteSubtype;
	}

	/**
	 * @return the noteTime
	 */
	public Date getNoteTime() {
		return noteTime;
	}

	/**
	 * @param noteTime
	 *            the noteTime to set
	 */
	public void setNoteTime(final Date noteTime) {
		this.noteTime = noteTime;
	}

	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}

	/**
	 * @param noteType
	 *            the noteType to set
	 */
	public void setNoteType(final String noteType) {
		this.noteType = noteType;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return the stgId
	 */
	public Long getStgId() {
		return stgId;
	}

	/**
	 * @param stgId
	 *            the stgId to set
	 */
	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	/**
	 * @return the noteSeq
	 */
	public Long getNoteSeq() {
		return noteSeq;
	}

	/**
	 * @param noteSeq
	 *            the noteSeq to set
	 */
	public void setNoteSeq(final Long noteSeq) {
		this.noteSeq = noteSeq;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the newText
	 */
	public String getNewText() {
		return newText;
	}

	/**
	 * @param newText
	 *            the newText to set
	 */
	public void setNewText(final String newText) {
		this.newText = newText;
	}

	/**
	 * @return the stgSeq
	 */
	public BigDecimal getStgSeq() {
		return stgSeq;
	}

	/**
	 * @param stgSeq
	 *            the stgSeq to set
	 */
	public void setStgSeq(final BigDecimal stgSeq) {
		this.stgSeq = stgSeq;
	}
}
