package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

public class AssessmentSummaries implements Serializable {
	private static final long serialVersionUID = 1L;

	private String category;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String summary;


	public AssessmentSummaries() {
		// AssessmentSummaries
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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


	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}


	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	
}
