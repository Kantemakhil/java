package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderProfiles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("profileSeq")
	private Integer profileSeq;
	@JsonProperty("checkdate")
	private Date checkdate;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	public OffenderProfiles(){
		// OffenderProfiles
		
	}
	
	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	/**
	 * @return the profileSeq
	 */
	public Integer getProfileSeq() {
		return profileSeq;
	}
	/**
	 * @param profileSeq the profileSeq to set
	 */
	public void setProfileSeq(Integer profileSeq) {
		this.profileSeq = profileSeq;
	}
	/**
	 * @return the checkdate
	 */
	public Date getCheckdate() {
		return checkdate;
	}
	/**
	 * @param checkdate the checkdate to set
	 */
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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
	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	/**
	 * @param modifyDateTime the modifyDateTime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}
	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
}
