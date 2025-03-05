package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.Date;

public class PersonIdentifiers implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private String identifier;

	private String identifierType;

	private String issuedAuthorityText;

	private Date issuedDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private long personId;

	private long idSeq;

	public PersonIdentifiers() {
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getIdSeq() {
		return idSeq;
	}

	public void setIdSeq(long idSeq) {
		this.idSeq = idSeq;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifierType() {
		return this.identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public String getIssuedAuthorityText() {
		return this.issuedAuthorityText;
	}

	public void setIssuedAuthorityText(String issuedAuthorityText) {
		this.issuedAuthorityText = issuedAuthorityText;
	}

	public Date getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
