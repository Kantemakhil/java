package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class PassPolcBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String beginWith;
	private String contain;
	private int minLength;
	private int maxLength;
	private String nonDictionryFlag;
	private String nonUserIdFlag;
	private int expireWithIn;
	private int prevPasswords;
	private String unitTime;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	
	public String getBeginWith() {
		return beginWith;
	}
	public void setBeginWith(String beginWith) {
		this.beginWith = beginWith;
	}
	public String getContain() {
		return contain;
	}
	public void setContain(String contain) {
		this.contain = contain;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getNonDictionryFlag() {
		return nonDictionryFlag;
	}
	public void setNonDictionryFlag(String nonDictionryFlag) {
		this.nonDictionryFlag = nonDictionryFlag;
	}
	public String getNonUserIdFlag() {
		return nonUserIdFlag;
	}
	public void setNonUserIdFlag(String nonUserIdFlag) {
		this.nonUserIdFlag = nonUserIdFlag;
	}
	public int getExpireWithIn() {
		return expireWithIn;
	}
	public void setExpireWithIn(int expireWithIn) {
		this.expireWithIn = expireWithIn;
	}
	public int getPrevPasswords() {
		return prevPasswords;
	}
	public void setPrevPasswords(int prevPasswords) {
		this.prevPasswords = prevPasswords;
	}
	public String getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
