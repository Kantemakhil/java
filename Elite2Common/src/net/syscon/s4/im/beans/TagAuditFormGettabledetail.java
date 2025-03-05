package net.syscon.s4.im.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagAuditFormGettabledetail extends BaseModel{
	
	
	private long sessionId;
	private Date stamp;
	private String osUser;
	private String dbUser;
	private String clientip;
	private String staffName;

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public String getOsUser() {
		return osUser;
	}

	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	@JsonProperty("pTableName")
	private String  pTableName;
	
	@JsonProperty("pDateFrom")
	private Date pDateFrom;
	
	@JsonProperty("pDateTo")
	private Date pDateTo;
	
	@JsonProperty("pTimeFrom")
	private Date pTimeFrom;
	
	@JsonProperty("pTimeTo")
	private Date pTimeTo;

	public String getpTableName() {
		return pTableName;
	}

	public void setpTableName(String pTableName) {
		this.pTableName = pTableName;
	}

	public Date getpDateFrom() {
		return pDateFrom;
	}

	public void setpDateFrom(Date pDateFrom) {
		this.pDateFrom = pDateFrom;
	}

	public Date getpDateTo() {
		return pDateTo;
	}

	public void setpDateTo(Date pDateTo) {
		this.pDateTo = pDateTo;
	}

	public Date getpTimeFrom() {
		return pTimeFrom;
	}

	public void setpTimeFrom(Date pTimeFrom) {
		this.pTimeFrom = pTimeFrom;
	}

	public Date getpTimeTo() {
		return pTimeTo;
	}

	public void setpTimeTo(Date pTimeTo) {
		this.pTimeTo = pTimeTo;
	}
	
	
}
