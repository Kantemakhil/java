package net.syscon.s4.inst.legalscreens.bean;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class OcdclistCourtListQuery
 */

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OcdclistCourtListQuery extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("pCourtEventTypeDesc")
	private String pCourtEventTypeDesc;
	@JsonProperty("pCourtEventType")
	private String pCourtEventType;
	@JsonProperty("pCheckSum")
	private Integer pCheckSum;
	@JsonProperty("pBirthDate")
	private Date pBirthDate;
	@JsonProperty("pCaseInfoprefix")
	private String pCaseInfoprefix;
	@JsonProperty("pCaseId")
	private Integer pCaseId;
	@JsonProperty("pMiddleName")
	private String pMiddleName;
	@JsonProperty("pAgyLocId")
	private String pAgyLocId;
	@JsonProperty("pStartTime")
	private Date pStartTime;
	@JsonProperty("pLastName")
	private String pLastName;
	@JsonProperty("pCaseInfoNumber")
	private String pCaseInfoNumber;
	@JsonProperty("pStartDate")
	private Date pStartDate;
	@JsonProperty("pCourtDate")
	private Date pCourtDate;
	@JsonProperty("pEventId")
	private Integer pEventId;
	@JsonProperty("pOffBkgId")
	private Integer pOffBkgId;
	@JsonProperty("pOffDisplay")
	private String pOffDisplay;
	@JsonProperty("pFirstName")
	private String pFirstName;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("pEventDate")
	private Date pEventDate;
	@JsonProperty("pEventStatus")
	private String pEventStatus;
	@JsonProperty("matter")
	private String matter;
	@JsonProperty("appearanceType")
	private String appearanceType;
	@JsonProperty("appearanceLocation")
	private String appearanceLocation;
	
	private String outcomeReasonCode;
	
	private String modifyUserId;
	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	private Boolean cancelFlag;

	/**
	 * @param pCourtEventTypeDesc
	 *            pCourtEventTypeDesc to set
	 */
	public void setpCourtEventTypeDesc(String pCourtEventTypeDesc) {
		this.pCourtEventTypeDesc = pCourtEventTypeDesc;
	}

	/**
	 * return thepCourtEventTypeDesc
	 */
	public String getpCourtEventTypeDesc() {
		return this.pCourtEventTypeDesc;
	}

	/**
	 * @param pCourtEventType
	 *            pCourtEventType to set
	 */
	public void setpCourtEventType(String pCourtEventType) {
		this.pCourtEventType = pCourtEventType;
	}

	/**
	 * return thepCourtEventType
	 */
	public String getpCourtEventType() {
		return this.pCourtEventType;
	}

	/**
	 * @param pCheckSum
	 *            pCheckSum to set
	 */
	public void setpCheckSum(Integer pCheckSum) {
		this.pCheckSum = pCheckSum;
	}

	/**
	 * return thepCheckSum
	 */
	public Integer getpCheckSum() {
		return this.pCheckSum;
	}

	/**
	 * @param pBirthDate
	 *            pBirthDate to set
	 */
	public void setpBirthDate(Date pBirthDate) {
		this.pBirthDate = pBirthDate;
	}

	/**
	 * return thepBirthDate
	 */
	public Date getpBirthDate() {
		return this.pBirthDate;
	}

	/**
	 * @param pCaseInfoprefix
	 *            pCaseInfoprefix to set
	 */
	public void setpCaseInfoprefix(String pCaseInfoprefix) {
		this.pCaseInfoprefix = pCaseInfoprefix;
	}

	/**
	 * return thepCaseInfoprefix
	 */
	public String getpCaseInfoprefix() {
		return this.pCaseInfoprefix;
	}

	/**
	 * @param pCaseId
	 *            pCaseId to set
	 */
	public void setpCaseId(Integer pCaseId) {
		this.pCaseId = pCaseId;
	}

	/**
	 * return thepCaseId
	 */
	public Integer getpCaseId() {
		return this.pCaseId;
	}

	/**
	 * @param pMiddleName
	 *            pMiddleName to set
	 */
	public void setpMiddleName(String pMiddleName) {
		this.pMiddleName = pMiddleName;
	}

	/**
	 * return thepMiddleName
	 */
	public String getpMiddleName() {
		return this.pMiddleName;
	}

	/**
	 * @param pAgyLocId
	 *            pAgyLocId to set
	 */
	public void setpAgyLocId(String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	/**
	 * return thepAgyLocId
	 */
	public String getpAgyLocId() {
		return this.pAgyLocId;
	}

	/**
	 * @param pStartTime
	 *            pStartTime to set
	 */
	public void setpStartTime(Date pStartTime) {
		this.pStartTime = pStartTime;
	}

	/**
	 * return thepStartTime
	 */
	public Date getpStartTime() {
		return this.pStartTime;
	}

	/**
	 * @param pLastName
	 *            pLastName to set
	 */
	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}

	/**
	 * return thepLastName
	 */
	public String getpLastName() {
		return this.pLastName;
	}

	/**
	 * @param pCaseInfoNumber
	 *            pCaseInfoNumber to set
	 */
	public void setpCaseInfoNumber(String pCaseInfoNumber) {
		this.pCaseInfoNumber = pCaseInfoNumber;
	}

	/**
	 * return thepCaseInfoNumber
	 */
	public String getpCaseInfoNumber() {
		return this.pCaseInfoNumber;
	}

	/**
	 * @param pStartDate
	 *            pStartDate to set
	 */
	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}

	/**
	 * return thepStartDate
	 */
	public Date getpStartDate() {
		return this.pStartDate;
	}

	/**
	 * @param pCourtDate
	 *            pCourtDate to set
	 */
	public void setpCourtDate(Date pCourtDate) {
		this.pCourtDate = pCourtDate;
	}

	/**
	 * return thepCourtDate
	 */
	public Date getpCourtDate() {
		return this.pCourtDate;
	}

	/**
	 * @param pEventId
	 *            pEventId to set
	 */
	public void setpEventId(Integer pEventId) {
		this.pEventId = pEventId;
	}

	/**
	 * return thepEventId
	 */
	public Integer getpEventId() {
		return this.pEventId;
	}

	/**
	 * @param pOffBkgId
	 *            pOffBkgId to set
	 */
	public void setpOffBkgId(Integer pOffBkgId) {
		this.pOffBkgId = pOffBkgId;
	}

	/**
	 * return thepOffBkgId
	 */
	public Integer getpOffBkgId() {
		return this.pOffBkgId;
	}

	/**
	 * @param pOffDisplay
	 *            pOffDisplay to set
	 */
	public void setpOffDisplay(String pOffDisplay) {
		this.pOffDisplay = pOffDisplay;
	}

	/**
	 * return thepOffDisplay
	 */
	public String getpOffDisplay() {
		return this.pOffDisplay;
	}

	/**
	 * @param pFirstName
	 *            pFirstName to set
	 */
	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}

	/**
	 * return thepFirstName
	 */
	public String getpFirstName() {
		return this.pFirstName;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getpEventDate() {
		return pEventDate;
	}

	public void setpEventDate(Date pEventDate) {
		this.pEventDate = pEventDate;
	}

	public String getpEventStatus() {
		return pEventStatus;
	}

	public void setpEventStatus(String pEventStatus) {
		this.pEventStatus = pEventStatus;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getAppearanceLocation() {
		return appearanceLocation;
	}

	public void setAppearanceLocation(String appearanceLocation) {
		this.appearanceLocation = appearanceLocation;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}