package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCommunitySentense extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("matter")
	private String matter;
	
	@JsonProperty("commenceType")
	private String commenceType;
	
	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("no")
	private Integer no;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("commenceDate")
	private Date commenceDate;

	@JsonProperty("expiryDate")
	private Date expiryDate;
	
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

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getCommenceType() {
		return commenceType;
	}

	public void setCommenceType(String commenceType) {
		this.commenceType = commenceType;
	}

	public String getFormInfoJson() {
		return formInfoJson;
	}

	public void setFormInfoJson(String formInfoJson) {
		this.formInfoJson = formInfoJson;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}

	public void setFormInfoJsonBlob(byte[] formInfoJsonBlob) {
		this.formInfoJsonBlob = formInfoJsonBlob;
	}

	public Date getCommenceDate() {
		return commenceDate;
	}

	public void setCommenceDate(Date commenceDate) {
		this.commenceDate = commenceDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
