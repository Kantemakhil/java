package net.syscon.s4.pkgs;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class TagExceptions extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal tagErrorId;
	private Long sid;
	private String moduleName;
	private String procedureName;
	private String errorMessage;
	private String errorLocation;
	private String modifyUserId;
	private Date modifyDatetime;
	private Date createDatetime;
	private String createUserId;
	private String userModule;
	private String userLocation;
	private String userMessage;
	private BigDecimal userErrorCode;
	private String sealFlag;

	public BigDecimal getTagErrorId() {
		return tagErrorId;
	}

	public void setTagErrorId(BigDecimal tagErrorId) {
		this.tagErrorId = tagErrorId;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(String errorLocation) {
		this.errorLocation = errorLocation;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUserModule() {
		return userModule;
	}

	public void setUserModule(String userModule) {
		this.userModule = userModule;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public BigDecimal getUserErrorCode() {
		return userErrorCode;
	}

	public void setUserErrorCode(BigDecimal userErrorCode) {
		this.userErrorCode = userErrorCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
