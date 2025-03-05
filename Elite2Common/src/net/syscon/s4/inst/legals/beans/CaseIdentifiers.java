package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CaseIdentifiers extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("seqNumber")
	private Integer seqNumber;
	
	@JsonProperty("caseId")
	private String caseId;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("number")
	private String number;
	
	@JsonProperty("oldType")
	private String oldType;
	
	@JsonProperty("oldNumber")
	private String oldNumber;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	/***
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
	/***
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/***
	 * 
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/***
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/***
	 * 
	 * @return
	 */
	public String getCaseId() {
		return caseId;
	}
	/***
	 * 
	 * @param caseId
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public String getOldType() {
		return oldType;
	}
	public void setOldType(String oldType) {
		this.oldType = oldType;
	}
	public String getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}
	public Integer getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}
	
}
