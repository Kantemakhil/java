package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderHwdHty extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long hwdHtyId;

	private Long hwdId;

	private Date createDatetime;

	private String createUserId;

	private String eventComment;

	private Date eventDatetime;

	private String eventType;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;
	
	private Date evenTimeTemp;
	
	public Date getEvenTimeTemp() {
		return evenTimeTemp;
	}

	public void setEvenTimeTemp(final Date evenTimeTemp) {
		this.evenTimeTemp = evenTimeTemp;
	}

	/**
	 * @return the hwdHtyId
	 */
	public Long getHwdHtyId() {
		return hwdHtyId;
	}

	/**
	 * @param hwdHtyId
	 *            the hwdHtyId to set
	 */
	public void setHwdHtyId(final Long hwdHtyId) {
		this.hwdHtyId = hwdHtyId;
	}

	/**
	 * @return the hwdId
	 */
	public Long getHwdId() {
		return hwdId;
	}

	/**
	 * @param hwdId
	 *            the hwdId to set
	 */
	public void setHwdId(final Long hwdId) {
		this.hwdId = hwdId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the eventComment
	 */
	public String getEventComment() {
		return eventComment;
	}

	/**
	 * @param eventComment
	 *            the eventComment to set
	 */
	public void setEventComment(final String eventComment) {
		this.eventComment = eventComment;
	}

	/**
	 * @return the eventDatetime
	 */
	public Date getEventDatetime() {
		return eventDatetime;
	}

	/**
	 * @param eventDatetime
	 *            the eventDatetime to set
	 */
	public void setEventDatetime(final Date eventDatetime) {
		this.eventDatetime = eventDatetime;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
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
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}