package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the MENU_SECURITIES_BAK database table.
 * 
 */
public class MenuSecurities extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@NotNull
	@Size(max = 32)
	@JsonProperty("createUserId")
	private String createUserId;

	@NotNull
	@JsonProperty("menuId")
	private BigDecimal menuId;

	@NotNull
	@Size(max = 60)
	@JsonProperty("menuItem")
	private String menuItem;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("moduleName")
	private String moduleName;

	@NotNull
	@JsonProperty("parentMenuId")
	private BigDecimal parentMenuId;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("DYNAMIC_FORM")
	private String dynamicForm;

	@NotNull
	@JsonProperty("sortOrder")
	private BigDecimal sortOrder;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("icon")
	private String icon;
	@JsonProperty("data")
	private String data;
	@JsonProperty("moduleDescription")
	private String moduleDescription;

	public MenuSecurities() {
		// MenuSecurities
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(final String icon) {
		this.icon = icon;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final String data) {
		this.data = data;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	public BigDecimal getParentMenuId() {
		return this.parentMenuId;
	}

	public void setParentMenuId(final BigDecimal parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(final String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(String dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

}
