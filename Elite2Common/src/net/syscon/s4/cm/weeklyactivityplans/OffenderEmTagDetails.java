package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderEmTagDetails extends BaseModel {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal offenderBookId;
	    private BigDecimal emTagId;
	    private String emTagStrapSize;
	    private String emTagDailyChargingPeriod;
	    private Date modifyDatetime;
	    private Date createDatetime;
	    private String createUserId;
	    private String modifyUserId;
	    private BigDecimal liReturn;
	    private String emTagData;
	    
	    private Date emTagStartTime;
	    private Date emTagEndTime;
	    
		public BigDecimal getOffenderBookId() {
			return offenderBookId;
		}
		public void setOffenderBookId(BigDecimal offenderBookId) {
			this.offenderBookId = offenderBookId;
		}
		public BigDecimal getEmTagId() {
			return emTagId;
		}
		public void setEmTagId(BigDecimal emTagId) {
			this.emTagId = emTagId;
		}
		public String getEmTagStrapSize() {
			return emTagStrapSize;
		}
		public void setEmTagStrapSize(String emTagStrapSize) {
			this.emTagStrapSize = emTagStrapSize;
		}
		public String getEmTagDailyChargingPeriod() {
			return emTagDailyChargingPeriod;
		}
		public void setEmTagDailyChargingPeriod(String emTagDailyChargingPeriod) {
			this.emTagDailyChargingPeriod = emTagDailyChargingPeriod;
		}
		public Date getModifyDatetime() {
			return modifyDatetime;
		}
		public void setModifyDatetime(Date modifyDatetime) {
			this.modifyDatetime = modifyDatetime;
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
		public String getModifyUserId() {
			return modifyUserId;
		}
		public void setModifyUserId(String modifyUserId) {
			this.modifyUserId = modifyUserId;
		}
		public BigDecimal getLiReturn() {
			return liReturn;
		}
		public void setLiReturn(BigDecimal liReturn) {
			this.liReturn = liReturn;
		}
		public String getEmTagData() {
			return emTagData;
		}
		public void setEmTagData(String emTagData) {
			this.emTagData = emTagData;
		}
		public Date getEmTagStartTime() {
			return emTagStartTime;
		}
		public void setEmTagStartTime(Date emTagStartTime) {
			this.emTagStartTime = emTagStartTime;
		}
		public Date getEmTagEndTime() {
			return emTagEndTime;
		}
		public void setEmTagEndTime(Date emTagEndTime) {
			this.emTagEndTime = emTagEndTime;
		}
	    
}
