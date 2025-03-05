package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ArrestNumbers extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty ("arrestNumberId")
	private Long arrestNumberId;

	
	@JsonProperty ("arrestId")
	private Long arrestId;
	
	@JsonProperty ("arrestNumberType")
	private String arrestNumberType;
	
	@JsonProperty ("arrestNumber")
	private Long arrestNumber;
	
	@JsonProperty ("commentText")
	private String commentText;
	
	@JsonProperty ("createUserId")
	private String createUserId;
	
	
	@JsonProperty ("createDate")
	private Date createDate;
	
	@JsonProperty ("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty ("modifyUserId")
	private String modifyUserId;

	/**
	 * @return the arrestNumberId
	 */
	public Long getArrestNumberId() {
		return arrestNumberId;
	}

	/**
	 * @param arrestNumberId the arrestNumberId to set
	 */
	public void setArrestNumberId(final Long arrestNumberId) {
		this.arrestNumberId = arrestNumberId;
	}

	/**
	 * @return the arrestId
	 */
	public Long getArrestId() {
		return arrestId;
	}

	/**
	 * @param arrestId the arrestId to set
	 */
	public void setArrestId(final Long arrestId) {
		this.arrestId = arrestId;
	}

	/**
	 * @return the arrestNumberType
	 */
	public String getArrestNumberType() {
		return arrestNumberType;
	}

	/**
	 * @param arrestNumberType the arrestNumberType to set
	 */
	public void setArrestNumberType(final String arrestNumberType) {
		this.arrestNumberType = arrestNumberType;
	}

	/**
	 * @return the arrestNumber
	 */
	public Long getArrestNumber() {
		return arrestNumber;
	}

	/**
	 * @param arrestNumber the arrestNumber to set
	 */
	public void setArrestNumber(final Long arrestNumber) {
		this.arrestNumber = arrestNumber;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	
	
	
	
	
	
}
