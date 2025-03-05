package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AutomationQueryParameters  extends BaseModel implements Serializable{  
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("apiId")
	private String apiId; 
	
	@JsonProperty("parameterId")
	private int parameterId; 
	
	@JsonProperty("queryKey")
	private String queryKey; 
	
	@JsonProperty("parameterCode")
	private String parameterCode; 
	
	@JsonProperty("parameterDescription")
	private String parameterDescription; 
	
	@JsonProperty("parameterType")
	private String parameterType; 
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("category")
	private String category;
	
	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public int getParameterId() {
		return parameterId;
	}

	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterDescription() {
		return parameterDescription;
	}

	public void setParameterDescription(String parameterDescription) {
		this.parameterDescription = parameterDescription;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
