package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the MENU_SECURITIES_BAK database table.
 * 
 */
public class MenuNode extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@JsonProperty("id")
	private Long id;

	@NotNull
	@Size(max = 60)
	@JsonProperty("name")
	private String name;

	@JsonProperty("href")
	private String href;

	@NotNull
	@JsonProperty("parentId")
	private Long parentId;

	@JsonProperty("state")
	private String state;

	@NotNull
	@JsonProperty("order")
	private Long order;
	
	@JsonProperty("level")
	private Long level;
	
	@JsonProperty("icon")
	private String icon;
	
	@JsonProperty("children")
	private List<MenuNode> children;
	
	@JsonProperty("isActive")
	private boolean isActive;
	
	@JsonProperty("dynamicForm")
	private String dynamicForm;
	
	@JsonProperty("insDashboard")
	private String insDashboard;

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(final String href) {
		this.href = href;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(final Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the order
	 */
	public Long getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(final Long order) {
		this.order = order;
	}

	/**
	 * @return the level
	 */
	public Long getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(final Long level) {
		this.level = level;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(final String icon) {
		this.icon = icon;
	}

	/**
	 * @return the children
	 */
	public List<MenuNode> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(final List<MenuNode> children) {
		this.children = children;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

	@Override
	public String toString() {
		return "MenuNode [id=" + id + ", name=" + name + ", href=" + href + ", parentId=" + parentId + ", state="
				+ state + ", order=" + order + ", level=" + level + ", icon=" + icon + ", children=" + children
				+ ", isActive=" + isActive + "]";
	}

	public String getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(String dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

	public String getInsDashboard() {
		return insDashboard;
	}

	public void setInsDashboard(String insDashboard) {
		this.insDashboard = insDashboard;
	}
	
}
