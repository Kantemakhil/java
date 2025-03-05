package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class IwpBookmarkParameters extends BaseModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private Date dateCreated;

	private String description;

	private Date modifyDatetime;

	private String modifyUserId;

	private String parameterDataType;

	private String parameterType;

	private String sealFlag;

	private String userCreated;
	
	private String parameterName;

	private String bookmarkName;
	
	private String moduleBlockCode;
	
	
	private String field;
	
	private String parameterDesc;

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
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
	 * @param dateCreated the dateCreated to set
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
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
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

	/**
	 * @return the parameterDataType
	 */
	public String getParameterDataType() {
		return parameterDataType;
	}

	/**
	 * @param parameterDataType the parameterDataType to set
	 */
	public void setParameterDataType(final String parameterDataType) {
		this.parameterDataType = parameterDataType;
	}

	/**
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}

	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(final String parameterType) {
		this.parameterType = parameterType;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the userCreated
	 */
	public String getUserCreated() {
		return userCreated;
	}

	/**
	 * @param userCreated the userCreated to set
	 */
	public void setUserCreated(final String userCreated) {
		this.userCreated = userCreated;
	}

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(final String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * @return the bookmarkName
	 */
	public String getBookmarkName() {
		return bookmarkName;
	}

	/**
	 * @param bookmarkName the bookmarkName to set
	 */
	public void setBookmarkName(final String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public String getModuleBlockCode() {
		return moduleBlockCode;
	}

	public void setModuleBlockCode(String moduleBlockCode) {
		this.moduleBlockCode = moduleBlockCode;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getParameterDesc() {
		return parameterDesc;
	}

	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc;
	}

	
	
}
