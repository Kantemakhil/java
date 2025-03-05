package net.syscon.s4.inst.shiftlogs.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AgencyShiftLogs
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyShiftLogs extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("shiftLogSeq")
	private BigDecimal shiftLogSeq;

	@JsonProperty("agyActivityCode")
	private String agyActivityCode;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("internalLocationId")
	private BigDecimal internalLocationId;

	@JsonProperty("internalLocationId2")
	private BigDecimal internalLocationId2;

	@JsonProperty("internalLocationId3")
	private BigDecimal internalLocationId3;

	@JsonProperty("lockFlag")
	private String lockFlag;

	@JsonProperty("logDate")
	private Date logDate;

	@JsonProperty("logTime")
	private Date logTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("observationText")
	private Clob observationText;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("staffId")
	private BigDecimal staffId;
	@JsonProperty("globalCaseLoadId")
	private String globalCaseLoadId;

	@JsonProperty("observationDetails")
	private String observationDetails;

	@JsonProperty("dspAgyLocId4")
	private String dspAgyLocId4;
	@JsonProperty("dspAgyLocId3")
	private String dspAgyLocId3;
	@JsonProperty("dspLastName")
	private String dspLastName;
	@JsonProperty("dspAgyLocId2")
	private String dspAgyLocId2;
	@JsonProperty("shiftLogSaveSeq")
	private Integer shiftLogSaveSeq;
	@JsonProperty("insertAllowed")
	private Boolean insertAllowed;
	@JsonProperty("updateAllowed")
	private Boolean updateAllowed;
	@JsonProperty("deleteAllowed")
	private Boolean deleteAllowed;
	@JsonProperty("logDateAllowed")
	private Boolean logDateAllowed;
	@JsonProperty("logTimeAllowed")
	private Boolean logTimeAllowed;
	
	
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("endTime")
	private Date endTime;
	
	@JsonProperty("amendedFlag")
    private String amendedFlag;
	
	@JsonProperty("errorFlag")
    private String errorFlag;

	public String getReason() {
		return reason;
	}
	
	@JsonProperty("agyLocId")
	private String agyLocId;

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private List<String> observationTextBytes;
	private Integer listSeq;

	@JsonProperty("observationTextOne")
	private String observationTextOne;

	public String getObservationTextOne() {
		return observationTextOne;
	}

	public void setObservationTextOne(String observationTextOne) {
		this.observationTextOne = observationTextOne;
	}

	public AgencyShiftLogs() {
		// AgencyShiftLogs
	}

	/**
	 * @return the shiftLogSaveSeq
	 */
	public Integer getShiftLogSaveSeq() {
		return shiftLogSaveSeq;
	}

	/**
	 * @param shiftLogSaveSeq the shiftLogSaveSeq to set
	 */
	public void setShiftLogSaveSeq(Integer shiftLogSaveSeq) {
		this.shiftLogSaveSeq = shiftLogSaveSeq;
	}

	public BigDecimal getShiftLogSeq() {
		return this.shiftLogSeq;
	}

	public void setShiftLogSeq(final BigDecimal shiftLogSeq) {
		this.shiftLogSeq = shiftLogSeq;
	}

	public String getAgyActivityCode() {
		return this.agyActivityCode;
	}

	public void setAgyActivityCode(final String agyActivityCode) {
		this.agyActivityCode = agyActivityCode;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getInternalLocationId() {
		return this.internalLocationId;
	}

	public void setInternalLocationId(final BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public BigDecimal getInternalLocationId2() {
		return this.internalLocationId2;
	}

	public void setInternalLocationId2(final BigDecimal internalLocationId2) {
		this.internalLocationId2 = internalLocationId2;
	}

	public BigDecimal getInternalLocationId3() {
		return this.internalLocationId3;
	}

	public void setInternalLocationId3(final BigDecimal internalLocationId3) {
		this.internalLocationId3 = internalLocationId3;
	}

	public String getLockFlag() {
		return this.lockFlag;
	}

	public void setLockFlag(final String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(final Date logDate) {
		this.logDate = logDate;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(final Date logTime) {
		this.logTime = logTime;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Clob getObservationText() {
		return this.observationText;
	}

	public void setObservationText(final Clob observationText) {
		this.observationText = observationText;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(final BigDecimal staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the globalCaseLoadId
	 */
	public String getGlobalCaseLoadId() {
		return globalCaseLoadId;
	}

	/**
	 * @param globalCaseLoadId the globalCaseLoadId to set
	 */
	public void setGlobalCaseLoadId(final String globalCaseLoadId) {
		this.globalCaseLoadId = globalCaseLoadId;
	}

	/**
	 * @param modifyDateTime the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the observationDetails
	 */
	public String getObservationDetails() {
		return observationDetails;
	}

	/**
	 * @param observationDetails the observationDetails to set
	 */
	public void setObservationDetails(final String observationDetails) {
		this.observationDetails = observationDetails;
	}

	/**
	 * @return the dspAgyLocId4
	 */
	public String getDspAgyLocId4() {
		return dspAgyLocId4;
	}

	/**
	 * @param dspAgyLocId4 the dspAgyLocId4 to set
	 */
	public void setDspAgyLocId4(final String dspAgyLocId4) {
		this.dspAgyLocId4 = dspAgyLocId4;
	}

	/**
	 * @return the dspAgyLocId3
	 */
	public String getDspAgyLocId3() {
		return dspAgyLocId3;
	}

	/**
	 * @param dspAgyLocId3 the dspAgyLocId3 to set
	 */
	public void setDspAgyLocId3(final String dspAgyLocId3) {
		this.dspAgyLocId3 = dspAgyLocId3;
	}

	/**
	 * @return the dspLastName
	 */
	public String getDspLastName() {
		return dspLastName;
	}

	/**
	 * @param dspLastName the dspLastName to set
	 */
	public void setDspLastName(final String dspLastName) {
		this.dspLastName = dspLastName;
	}

	/**
	 * @return the dspAgyLocId2
	 */
	public String getDspAgyLocId2() {
		return dspAgyLocId2;
	}

	/**
	 * @param dspAgyLocId2 the dspAgyLocId2 to set
	 */
	public void setDspAgyLocId2(final String dspAgyLocId2) {
		this.dspAgyLocId2 = dspAgyLocId2;
	}

	/**
	 * @return the insertAllowed
	 */
	public Boolean getInsertAllowed() {
		return insertAllowed;
	}

	/**
	 * @param insertAllowed the insertAllowed to set
	 */
	public void setInsertAllowed(final Boolean insertAllowed) {
		this.insertAllowed = insertAllowed;
	}

	/**
	 * @return the updateAllowed
	 */
	public Boolean getUpdateAllowed() {
		return updateAllowed;
	}

	/**
	 * @param updateAllowed the updateAllowed to set
	 */
	public void setUpdateAllowed(final Boolean updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	/**
	 * @return the deleteAllowed
	 */
	public Boolean getDeleteAllowed() {
		return deleteAllowed;
	}

	/**
	 * @param deleteAllowed the deleteAllowed to set
	 */
	public void setDeleteAllowed(final Boolean deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}

	/**
	 * @return the logDateAllowed
	 */
	public Boolean getLogDateAllowed() {
		return logDateAllowed;
	}

	/**
	 * @param logDateAllowed the logDateAllowed to set
	 */
	public void setLogDateAllowed(final Boolean logDateAllowed) {
		this.logDateAllowed = logDateAllowed;
	}

	/**
	 * @return the logTimeAllowed
	 */
	public Boolean getLogTimeAllowed() {
		return logTimeAllowed;
	}

	/**
	 * @param logTimeAllowed the logTimeAllowed to set
	 */
	public void setLogTimeAllowed(final Boolean logTimeAllowed) {
		this.logTimeAllowed = logTimeAllowed;
	}

	/**
	 * @return the observationTextBytes
	 */
	public List<String> getObservationTextBytes() {
		return observationTextBytes;
	}

	/**
	 * @param observationTextBytes the observationTextBytes to set
	 */
	public void setObservationTextBytes(List<String> observationTextBytes) {
		this.observationTextBytes = observationTextBytes;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public String getAmendedFlag() {
		return amendedFlag;
	}

	public void setAmendedFlag(String amendedFlag) {
		this.amendedFlag = amendedFlag;
	}

	public String getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

}
