package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the OFFENDER_LANGUAGES database table.
 * 
 */
public class OffenderLanguages implements Serializable {
	private static final long serialVersionUID = 1L;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private String interpreterRequestedFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private String numeracySkill;

	private String preferedSpeakFlag;

	private String preferedWriteFlag;

	private String readSkill;

	private String sealFlag;

	private String speakSkill;

	private String writeSkill;

	private Long offenderBookId;

	private String languageCode;

	private String languageType;

	private String languageCodeTemp;

	public OffenderLanguages() {
		// OffenderLanguages
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(final String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(final String languageType) {
		this.languageType = languageType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getInterpreterRequestedFlag() {
		return this.interpreterRequestedFlag;
	}

	public void setInterpreterRequestedFlag(final String interpreterRequestedFlag) {
		this.interpreterRequestedFlag = interpreterRequestedFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNumeracySkill() {
		return this.numeracySkill;
	}

	public void setNumeracySkill(final String numeracySkill) {
		this.numeracySkill = numeracySkill;
	}

	public String getPreferedSpeakFlag() {
		return this.preferedSpeakFlag;
	}

	public void setPreferedSpeakFlag(final String preferedSpeakFlag) {
		this.preferedSpeakFlag = preferedSpeakFlag;
	}

	public String getPreferedWriteFlag() {
		return this.preferedWriteFlag;
	}

	public void setPreferedWriteFlag(final String preferedWriteFlag) {
		this.preferedWriteFlag = preferedWriteFlag;
	}

	public String getReadSkill() {
		return this.readSkill;
	}

	public void setReadSkill(final String readSkill) {
		this.readSkill = readSkill;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSpeakSkill() {
		return this.speakSkill;
	}

	public void setSpeakSkill(final String speakSkill) {
		this.speakSkill = speakSkill;
	}

	public String getWriteSkill() {
		return this.writeSkill;
	}

	public void setWriteSkill(final String writeSkill) {
		this.writeSkill = writeSkill;
	}

	/**
	 * @return the languageCodeTemp
	 */
	public String getLanguageCodeTemp() {
		return languageCodeTemp;
	}

	/**
	 * @param languageCodeTemp
	 *            the languageCodeTemp to set
	 */
	public void setLanguageCodeTemp(final String languageCodeTemp) {
		this.languageCodeTemp = languageCodeTemp;
	}

}
