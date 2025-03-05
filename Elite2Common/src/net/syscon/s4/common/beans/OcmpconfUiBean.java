package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class OcmpconfUiBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("compId")
	private Long compId;
	
	@JsonProperty("compType")
	private String compType;
	
	@JsonProperty("createdBy")
	private String createdBy;
	
	@JsonProperty("modifiedBy")
	private String modifiedBy;
	
	@JsonProperty("createDate")
	private Date createDate;
	
	@JsonProperty("modifyDate")
	private Date modifyDate;
	
	@JsonProperty("compConfig")
	private String compConfig;
	
	@JsonProperty("compConfigDef")
	private String compConfigDef;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createUserId")
	private String createUserId;

	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCompConfig() {
		return compConfig;
	}

	public void setCompConfig(String compConfig) {
		this.compConfig = compConfig;
	}

	public String getcompConfigDef() {
		return compConfigDef;
	}

	public void setCompVonfigDef(String compConfigDef) {
		this.compConfigDef = compConfigDef;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}