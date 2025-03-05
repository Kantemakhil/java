package net.syscon.s4.im.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OtrbalanReportBean {
	@JsonProperty("parentName")
	private String parentName;
	@JsonProperty("childName")
	private String childName;
	@JsonProperty("subChildName")
	private String subChildName;
	@JsonProperty("amount")
	private BigDecimal amount;
	@JsonProperty("fReportHeaderLabelName")
	private String fReportHeaderLabelName;
	@JsonProperty("fCaseloadNameOne")
	private String fCaseloadNameOne;
	
	@JsonProperty("userDate")
	private String userDate;
	
	@JsonProperty("userName")
	private String userName;
	
	
	public OtrbalanReportBean() {
//		OtrbalanReportBean
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(final String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the childName
	 */
	public String getChildName() {
		return childName;
	}

	/**
	 * @param childName the childName to set
	 */
	public void setChildName(final String childName) {
		this.childName = childName;
	}

	/**
	 * @return the subChildName
	 */
	public String getSubChildName() {
		return subChildName;
	}

	/**
	 * @param subChildName the subChildName to set
	 */
	public void setSubChildName(final String subChildName) {
		this.subChildName = subChildName;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the fReportHeaderLabelName
	 */
	public String getfReportHeaderLabelName() {
		return fReportHeaderLabelName;
	}

	/**
	 * @param fReportHeaderLabelName the fReportHeaderLabelName to set
	 */
	public void setfReportHeaderLabelName(final String fReportHeaderLabelName) {
		this.fReportHeaderLabelName = fReportHeaderLabelName;
	}

	/**
	 * @return the fCaseloadNameOne
	 */
	public String getfCaseloadNameOne() {
		return fCaseloadNameOne;
	}

	/**
	 * @param fCaseloadNameOne the fCaseloadNameOne to set
	 */
	public void setfCaseloadNameOne(final String fCaseloadNameOne) {
		this.fCaseloadNameOne = fCaseloadNameOne;
	}

	/**
	 * @return the userDate
	 */
	public String getUserDate() {
		return userDate;
	}

	/**
	 * @param userDate the userDate to set
	 */
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
