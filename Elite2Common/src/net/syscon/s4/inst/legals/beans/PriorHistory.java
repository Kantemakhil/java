package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
public class PriorHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	
	@JsonProperty("priorHistoryDate")
	private Date priorHistoryDate;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("offenseDescription")
	private String offenseDescription;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("verified")
	private String verified;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenseSeq")
	private Integer offenseSeq;
	
	/**
	 * @return the priorHistoryDate
	 */
	public Date getPriorHistoryDate() {
		return priorHistoryDate;
	}

	/**
	 * @param priorHistoryDate the priorHistoryDate to set
	 */
	public void setPriorHistoryDate(Date priorHistoryDate) {
		this.priorHistoryDate = priorHistoryDate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the offenseDescription
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}

	/**
	 * @param offenseDescription the offenseDescription to set
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the verified
	 */
	public String getVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(String verified) {
		this.verified = verified;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return the offenseSeq
	 */
	public Integer getOffenseSeq() {
		return offenseSeq;
	}

	/**
	 * @param offenseSeq the offenseSeq to set
	 */
	public void setOffenseSeq(Integer offenseSeq) {
		this.offenseSeq = offenseSeq;
	}
	
	
	
	
}
