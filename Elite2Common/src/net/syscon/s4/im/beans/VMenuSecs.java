package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_MENU_SECS database table.
 * 
 */
public class VMenuSecs extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("applnCode")
	private String applnCode;

	@JsonProperty("description")
	private String description;

	@JsonProperty("menuId")
	private BigDecimal menuId;

	@JsonProperty("menuItem")
	private String menuItem;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("moduleType")
	private String moduleType;

	@JsonProperty("parentMenuId")
	private BigDecimal parentMenuId;

	@JsonProperty("sortOrder")
	private BigDecimal sortOrder;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

	private String moduleDescription;
	
	@JsonProperty("menuExistCount")
	private Integer menuExistCount;
	public VMenuSecs() {
		// VMenuSecs
	}

	public String getApplnCode() {
		return this.applnCode;
	}

	public void setApplnCode(final String applnCode) {
		this.applnCode = applnCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getMenuId() {
		return this.menuId;
	}

	public void setMenuId(final BigDecimal menuId) {
		this.menuId = menuId;
	}

	public String getMenuItem() {
		return this.menuItem;
	}

	public void setMenuItem(final String menuItem) {
		this.menuItem = menuItem;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(final String moduleType) {
		this.moduleType = moduleType;
	}

	public BigDecimal getParentMenuId() {
		return this.parentMenuId;
	}

	public void setParentMenuId(final BigDecimal parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public BigDecimal getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(final BigDecimal sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

}
