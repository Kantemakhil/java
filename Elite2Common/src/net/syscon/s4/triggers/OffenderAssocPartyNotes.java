package net.syscon.s4.triggers;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderAssocPartyNotes extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long associatedPartyId;
	private Integer noteSeq;
	private Date noteDatetime;
	private String noteType;
	private String noteSubtype;
	private String casenoteText;
	private Date createDatetime;
	private String createUserId;
	private String sealFlag;
	private Date modifyDatetime;
	private String modifyUserId;

	public Long getAssociatedPartyId() {
		return associatedPartyId;
	}

	public void setAssociatedPartyId(Long associatedPartyId) {
		this.associatedPartyId = associatedPartyId;
	}

	public Integer getNoteSeq() {
		return noteSeq;
	}

	public void setNoteSeq(Integer noteSeq) {
		this.noteSeq = noteSeq;
	}

	public Date getNoteDatetime() {
		return noteDatetime;
	}

	public void setNoteDatetime(Date noteDatetime) {
		this.noteDatetime = noteDatetime;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getNoteSubtype() {
		return noteSubtype;
	}

	public void setNoteSubtype(String noteSubtype) {
		this.noteSubtype = noteSubtype;
	}

	public String getCasenoteText() {
		return casenoteText;
	}

	public void setCasenoteText(String casenoteText) {
		this.casenoteText = casenoteText;
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

}
