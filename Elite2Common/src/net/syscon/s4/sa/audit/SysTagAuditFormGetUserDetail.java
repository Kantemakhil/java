package net.syscon.s4.sa.audit;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class SysTagAuditFormGetUserDetail extends BaseModel implements Serializable{
	
	
	private long sessionId;
	private Date stamp;
	private String osUsername;
	private String dbUser;
	private String clientHost;
	private String staffName;
	private String userName;
	private String actTimestamp;
	private Date fromDate;
	private Date toDate;
	private Date fromTime;
	private Date toTime;
	private String userId;
	
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


	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}


	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getUserName() {
		return userName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getOsUsername() {
		return osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getActTimestamp() {
		return actTimestamp;
	}

	public void setActTimestamp(String actTimestamp) {
		this.actTimestamp = actTimestamp;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	


}
