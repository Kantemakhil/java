package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderFileLocation extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("caseStaffId")
	private BigDecimal caseStaffId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createTime")
	private Date createTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("locationSeq")
	private long locationSeq;

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCaseStaffId() {
		return caseStaffId;
	}

	/**
	 *
	 * @param caseStaffId
	 */
	public void setCaseStaffId(BigDecimal caseStaffId) {
		this.caseStaffId = caseStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 *
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getLocationSeq() {
		return locationSeq;
	}

	/**
	 *
	 * @param locationSeq
	 */
	public void setLocationSeq(long locationSeq) {
		this.locationSeq = locationSeq;
	}

}