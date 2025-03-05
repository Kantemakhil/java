package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Holds implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("issuingAgyLocId")
	private String issuingAgyLocId;
	
	@JsonProperty("orderDate")
	private Date orderDate;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("orderStatus")
	private String orderStatus;
	
	@JsonProperty("caseId")
	private String caseId;
	
	@JsonProperty("eventId")
	private Integer eventId;
	
	@JsonProperty("orderId")
	private Integer orderId;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
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
	public String getOrderType() {
		return orderType;
	}
	/***
	 * 
	 * @param orderType
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}	
	/***
	 * 
	 * @return
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/***
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/***
	 * 
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/***
	 * 
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/***
	 * 
	 * @return
	 */
	public String getIssuingAgyLocId() {
		return issuingAgyLocId;
	}
	/***
	 * 
	 * @param issuingAgyLocId
	 */
	public void setIssuingAgyLocId(String issuingAgyLocId) {
		this.issuingAgyLocId = issuingAgyLocId;
	}
	/***
	 * 
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}
	/***
	 * 
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	/***
	 * 
	 * @return
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/***
	 * 
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	
	/***
	 * 
	 * @return
	 */
	public Integer getEventId() {
		return eventId;
	}
	/***
	 * 
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	/***
	 * 
	 * @return
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/***
	 * 
	 * @param orderId
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/***
	 * 
	 * @return
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	/***
	 * 
	 * @param offenderBookId
	 */
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	/***
	 * 
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/***
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/***
	 * 
	 * @return
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}
	/***
	 * 
	 * @param createDateTime
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	/***
	 * 
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/***
	 * 
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/***
	 * 
	 * @return
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	/***
	 * 
	 * @param modifyDateTime
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	
	
}
