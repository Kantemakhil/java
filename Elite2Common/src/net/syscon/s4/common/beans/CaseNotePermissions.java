package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CaseNotePermissions extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal roleId ;
	private BigDecimal workId;
	private String createFlag;
	private String viewFlag;
	private Date createDatetimeTimestamp;
	private String createUserId;
	private Date modifyDatetime;
	private  String modifyUserId;
	private String sealFlag;
	public BigDecimal getRoleId() {
		return roleId;
	}
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
	public BigDecimal getWorkId() {
		return workId;
	}
	public void setWorkId(BigDecimal workId) {
		this.workId = workId;
	}
	public String getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public Date getCreateDatetimeTimestamp() {
		return createDatetimeTimestamp;
	}
	public void setCreateDatetimeTimestamp(Date createDatetimeTimestamp) {
		this.createDatetimeTimestamp = createDatetimeTimestamp;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	@Override
	public String toString() {
		return "CaseNotePermissions [roleId=" + roleId + ", workId=" + workId + ", createFlag=" + createFlag
				+ ", viewFlag=" + viewFlag + ", createDatetimeTimestamp=" + createDatetimeTimestamp + ", createUserId="
				+ createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag="
				+ sealFlag + "]";
	} 

	

}
