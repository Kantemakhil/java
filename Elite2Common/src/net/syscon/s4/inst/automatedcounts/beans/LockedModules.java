package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class LockedModules
 */
public class LockedModules extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("moduleName")
	private String moduleName;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("lockedDate")
	private Date lockedDate;
	@JsonProperty("offenderId")
	private Integer offenderId;
	@JsonProperty("sessionId")
	private Integer sessionId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("accountCode")
	private Integer accountCode;
	private boolean inserted;
	@JsonProperty("rowId")
	private Integer rowId;

	public LockedModules() {
		// LockedModules
	}

	/**
	 * @param moduleName
	 *            moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * return themoduleName
	 */
	public String getModuleName() {
		return this.moduleName;
	}

	/**
	 * @param caseloadId
	 *            caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * return thecaseloadId
	 */
	public String getCaseloadId() {
		return this.caseloadId;
	}

	/**
	 * @param userId
	 *            userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * return theuserId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param lockedDate
	 *            lockedDate to set
	 */
	public void setLockedDate(final Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	/**
	 * return thelockedDate
	 */
	public Date getLockedDate() {
		return this.lockedDate;
	}

	/**
	 * @param offenderId
	 *            offenderId to set
	 */
	public void setOffenderId(final Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * return theoffenderId
	 */
	public Integer getOffenderId() {
		return this.offenderId;
	}

	/**
	 * @param sessionId
	 *            sessionId to set
	 */
	public void setSessionId(final Integer sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * return thesessionId
	 */
	public Integer getSessionId() {
		return this.sessionId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param accountCode
	 *            accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * return theaccountCode
	 */
	public Integer getAccountCode() {
		return this.accountCode;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

}