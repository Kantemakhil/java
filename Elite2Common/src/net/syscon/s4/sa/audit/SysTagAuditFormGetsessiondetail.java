package net.syscon.s4.sa.audit;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SysTagAuditFormGetsessiondetail extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("sessionid")
	private Long sessionid;
	
	@JsonProperty("scn")
	private Long scn;
	
	@JsonProperty("sqlText")
	private String sqlText;
	
	@JsonProperty("statementType")
	private String statementType;
	
	@JsonProperty("sqlBind")
	private String sqlBind;
	
	@JsonProperty("policyName")
	private String policyName;
	
	@JsonProperty("objectName")
	private String objectName;
	
	@JsonProperty("actTimestamp")
	private String actTimestamp;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("time")
	private String time;

	
	public Long getSessionid() {
		return sessionid;
	}

	public void setSessionid(final Long sessionid) {
		this.sessionid = sessionid;
	}

	public Long getScn() {
		return scn;
	}

	public void setScn(final Long scn) {
		this.scn = scn;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(final String sqlText) {
		this.sqlText = sqlText;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(final String statementType) {
		this.statementType = statementType;
	}

	public String getSqlBind() {
		return sqlBind;
	}

	public void setSqlBind(final String sqlBind) {
		this.sqlBind = sqlBind;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(final String policyName) {
		this.policyName = policyName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(final String objectName) {
		this.objectName = objectName;
	}

	

	public String getActTimestamp() {
		return actTimestamp;
	}

	public void setActTimestamp(final String actTimestamp) {
		this.actTimestamp = actTimestamp;
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SysTagAuditFormGetsessiondetail [sessionid=" + sessionid + ", scn=" + scn + ", sqlText=" + sqlText
				+ ", statementType=" + statementType + ", sqlBind=" + sqlBind + ", policyName=" + policyName
				+ ", objectName=" + objectName + ", actTimestamp=" + actTimestamp + "]";
	}
	
	

	
	

}
