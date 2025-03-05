package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ActionApi extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("apiId")
	private String apiId; 
	
	@JsonProperty("apiDescription")
	private String apiDescription; 
	
	@JsonProperty("queryKey")
	private String queryKey; 
	
	@JsonProperty("requestType")
	private String requestType; 
	
	@JsonProperty("url")
	private String url; 
	
	@JsonProperty("queryId")
	private int queryId;
	
	@JsonProperty("queryText")
	private String queryText;
	
	@JsonProperty("queryDesc")
	private String queryDesc;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("verifiedBy")
	private String verifiedBy;
	
	@JsonProperty("verifiedDate")
	private Date verifiedDate;
	
	@JsonProperty("createDatetime")
	private Date createDatetime; 
	
	@JsonProperty("createUserId")
	private String createUserId; 
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime; 
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("rowId")
	private String rowId; 
	
	
	@JsonProperty("category")
	private String category;
	
	private List<AutomationQueryParameters> paramList;
	
	private List<Map<String, Object>> quickAction;
	
	private List<Map<String, Object>> actionParams;

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiDescription() {
		return apiDescription;
	}

	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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


	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getQueryDesc() {
		return queryDesc;
	}

	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public List<AutomationQueryParameters> getParamList() {
		return paramList;
	}

	public void setParamList(List<AutomationQueryParameters> paramList) {
		this.paramList = paramList;
	}

	public List<Map<String, Object>> getQuickAction() {
		return quickAction;
	}

	public void setQuickAction(List<Map<String, Object>> quickAction) {
		this.quickAction = quickAction;
	}

	public List<Map<String, Object>> getActionParams() {
		return actionParams;
	}

	public void setActionParams(List<Map<String, Object>> actionParams) {
		this.actionParams = actionParams;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	} 

}
