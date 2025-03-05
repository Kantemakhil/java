package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OicHearingNotices extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("oicHearingId")
	@NotNull
	private Integer oicHearingId;

	@JsonProperty("oicNoticeSeq")
	@NotNull
	private Integer oicNoticeSeq;

	@JsonProperty("deliveryDate")
	private Date deliveryDate;

	@JsonProperty("deliveryTime")
	private Date deliveryTime;

	@JsonProperty("deliveryStaffId")
	private Integer deliveryStaffId;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private Boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("deliveryStaffIdDes")
	private String deliveryStaffIdDes;

	/**
	 * Creates new OicHearingNotices class Object
	 */
	public OicHearingNotices() {
		// Super();
	}

	/**
	 * return the oicHearingId
	 */

	public Integer getOicHearingId() {
		return oicHearingId;
	}

	/**
	 * @param oicHearingId
	 *            oicHearingId to set
	 */

	public void setOicHearingId(final Integer oicHearingId) {
		this.oicHearingId = oicHearingId;
	}

	/**
	 * return the oicNoticeSeq
	 */

	public Integer getOicNoticeSeq() {
		return oicNoticeSeq;
	}

	/**
	 * @param oicNoticeSeq
	 *            oicNoticeSeq to set
	 */

	public void setOicNoticeSeq(final Integer oicNoticeSeq) {
		this.oicNoticeSeq = oicNoticeSeq;
	}

	/**
	 * return the deliveryDate
	 */

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            deliveryDate to set
	 */

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * return the deliveryTime
	 */

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 *            deliveryTime to set
	 */

	public void setDeliveryTime(final Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * return the deliveryStaffId
	 */

	public Integer getDeliveryStaffId() {
		return deliveryStaffId;
	}

	/**
	 * @param deliveryStaffId
	 *            deliveryStaffId to set
	 */

	public void setDeliveryStaffId(final Integer deliveryStaffId) {
		this.deliveryStaffId = deliveryStaffId;
	}

	/**
	 * return the createDateTime
	 */

	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param date
	 *            createDateTime to set
	 */

	public void setCreateDateTime(final Date date) {
		this.createDateTime = date;
	}

	/**
	 * return the createUserId
	 */

	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the modifyDateTime
	 */

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param date
	 *            modifyDateTime to set
	 */

	public void setModifyDateTime(final Date date) {
		this.modifyDateTime = date;
	}

	/**
	 * return the modifyUserId
	 */

	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the commentText
	 */

	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return the sealFlag
	 */

	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the inserted
	 */

	public Boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            inserted to set
	 */

	public void setInserted(final Boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * return the errorMessage
	 */

	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            errorMessage to set
	 */

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the deliveryStaffIdDes
	 */
	public String getDeliveryStaffIdDes() {
		return deliveryStaffIdDes;
	}

	/**
	 * @param deliveryStaffIdDes
	 *            the deliveryStaffIdDes to set
	 */
	public void setDeliveryStaffIdDes(final String deliveryStaffIdDes) {
		this.deliveryStaffIdDes = deliveryStaffIdDes;
	}

}