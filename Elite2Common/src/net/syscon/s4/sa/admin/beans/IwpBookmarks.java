package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class IwpBookmarks extends BaseModel implements Serializable {
	/**
		 * serialVersionUID
		 */
	private static final long serialVersionUID = 1L;

	private String bookmarkName;

	private String activeFlag;

	private String bookmarkType;

	private Date createDatetime;

	private String createUserId;

	private Date dateCreated;

	private String description;

	private Date expiryDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String sqlText;

	private String sqlVerifiedFlag;

	private String userCreated;

	/**
	 * @return the bookmarkName
	 */
	public String getBookmarkName() {
		return bookmarkName;
	}

	/**
	 * @param bookmarkName
	 *            the bookmarkName to set
	 */
	public void setBookmarkName(final String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the bookmarkType
	 */
	public String getBookmarkType() {
		return bookmarkType;
	}

	/**
	 * @param bookmarkType
	 *            the bookmarkType to set
	 */
	public void setBookmarkType(final String bookmarkType) {
		this.bookmarkType = bookmarkType;
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
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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

	/**
	 * @return the sqlText
	 */
	public String getSqlText() {
		return sqlText;
	}

	/**
	 * @param sqlText
	 *            the sqlText to set
	 */
	public void setSqlText(final String sqlText) {
		this.sqlText = sqlText;
	}

	/**
	 * @return the sqlVerifiedFlag
	 */
	public String getSqlVerifiedFlag() {
		return sqlVerifiedFlag;
	}

	/**
	 * @param sqlVerifiedFlag
	 *            the sqlVerifiedFlag to set
	 */
	public void setSqlVerifiedFlag(final String sqlVerifiedFlag) {
		this.sqlVerifiedFlag = sqlVerifiedFlag;
	}

	/**
	 * @return the userCreated
	 */
	public String getUserCreated() {
		return userCreated;
	}

	/**
	 * @param userCreated
	 *            the userCreated to set
	 */
	public void setUserCreated(final String userCreated) {
		this.userCreated = userCreated;
	}

}
