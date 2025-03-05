package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("menus")
	private List<MenuNode> userMenus;
	
	@JsonProperty("roles")
	private Map<String, String> userRoles;

	/**
	 * @return the userMenus
	 */
	public List<MenuNode> getUserMenus() {
		return userMenus;
	}

	/**
	 * @param userMenus the userMenus to set
	 */
	public void setUserMenus(List<MenuNode> userMenus) {
		this.userMenus = userMenus;
	}

	/**
	 * @return the userRoles
	 */
	public Map<String, String> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(Map<String, String> userRoles) {
		this.userRoles = userRoles;
	}

}
