package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class JujCommLocations extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("jujCommLocId")
	private Long jujCommLocId ;
	
	@JsonProperty("locationType")
	private String locationType ;
	
	@JsonProperty("description")
	private String description ;
	
	@JsonProperty("postcode")
	private String  postcode;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("commAgyLocId")
	private String commAgyLocId ;
	
	@JsonProperty("createDatetime")
	private Date  createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId ;
	
	@JsonProperty("modifyDatetime")
	private Date  modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	
	
	/**
	 * @return the jujCommLocId
	 */
	public Long getJujCommLocId() {
		return jujCommLocId;
	}

	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @return the commAgyLocId
	 */
	public String getCommAgyLocId() {
		return commAgyLocId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param jujCommLocId the jujCommLocId to set
	 */
	public void setJujCommLocId(final Long jujCommLocId) {
		this.jujCommLocId = jujCommLocId;
	}

	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(final String locationType) {
		this.locationType = locationType;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(final String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @param commAgyLocId the commAgyLocId to set
	 */
	public void setCommAgyLocId(final String commAgyLocId) {
		this.commAgyLocId = commAgyLocId;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
