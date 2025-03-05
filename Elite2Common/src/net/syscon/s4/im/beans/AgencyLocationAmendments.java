package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the AGENCY_LOCATION_AMENDMENTS database table.
 * 
 */
public class AgencyLocationAmendments implements Serializable {
	private static final long serialVersionUID = 1L;

	private long agyLocAmendId;

	private String agyLocId;

	private Date amendDatetime;

	private String amendUser;

	private Date createDatetime;

	private String createUserId;

	private String field;

	private Date modifyDatetime;

	private String modifyUserId;

	private String newValue;

	private String originalValue;

	private String sealFlag;
	
	private String pAgyLocId;

	private String pColName;

	private String pOldValue;

	private String lvOldValue;

	private String lvNewValue;

	private String pNewValue;

	private String user;
	
	private String lvAgyLocId;
	
	private String pDescType;
	
	private String pDomain;

	public AgencyLocationAmendments() {
	}

	public long getAgyLocAmendId() {
		return this.agyLocAmendId;
	}

	public void setAgyLocAmendId(long agyLocAmendId) {
		this.agyLocAmendId = agyLocAmendId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getAmendDatetime() {
		return this.amendDatetime;
	}

	public void setAmendDatetime(Date amendDatetime) {
		this.amendDatetime = amendDatetime;
	}

	public String getAmendUser() {
		return this.amendUser;
	}

	public void setAmendUser(String amendUser) {
		this.amendUser = amendUser;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNewValue() {
		return this.newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOriginalValue() {
		return this.originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getpAgyLocId() {
		return pAgyLocId;
	}

	public void setpAgyLocId(String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	public String getpColName() {
		return pColName;
	}

	public void setpColName(String pColName) {
		this.pColName = pColName;
	}

	public String getpOldValue() {
		return pOldValue;
	}

	public void setpOldValue(String pOldValue) {
		this.pOldValue = pOldValue;
	}

	public String getLvOldValue() {
		return lvOldValue;
	}

	public void setLvOldValue(String lvOldValue) {
		this.lvOldValue = lvOldValue;
	}

	public String getLvNewValue() {
		return lvNewValue;
	}

	public void setLvNewValue(String lvNewValue) {
		this.lvNewValue = lvNewValue;
	}

	public String getpNewValue() {
		return pNewValue;
	}

	public void setpNewValue(String pNewValue) {
		this.pNewValue = pNewValue;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLvAgyLocId() {
		return lvAgyLocId;
	}

	public void setLvAgyLocId(String lvAgyLocId) {
		this.lvAgyLocId = lvAgyLocId;
	}

	public String getpDescType() {
		return pDescType;
	}

	public void setpDescType(String pDescType) {
		this.pDescType = pDescType;
	}

	public String getpDomain() {
		return pDomain;
	}

	public void setpDomain(String pDomain) {
		this.pDomain = pDomain;
	}
	

}
