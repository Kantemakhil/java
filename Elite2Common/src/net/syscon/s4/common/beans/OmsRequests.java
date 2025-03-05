package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.OmsModules;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmsRequests extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("requestId")
	private Long requestId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("displayFlag")
	private String displayFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("numberOfCopy")
	private BigDecimal numberOfCopy;

	@JsonProperty("printerId")
	private String printerId;

	@JsonProperty("requestDate")
	private Date requestDate;

	@JsonProperty("requestStatus")
	private String requestStatus;

	@JsonProperty("requestUserId")
	private String requestUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("omsModule")
	private OmsModules omsModule;

	@JsonProperty("omsRequestParameters")
	private List<OmsRequestParameter> omsRequestParameters;

	@JsonProperty("deductionType")
	private String deductionType;

	@JsonProperty("informationNumber")
	private String informationNumber;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("nbtUserId")
	private String nbtUserId;
	
	@JsonProperty("nbtDateFrom")
	private Date nbtDateFrom;
	
	@JsonProperty("nbtDateTo")
	private Date nbtDateTo;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("sessionId")
	private Long sessionId;
	
	@JsonProperty("txnId")
	private BigDecimal txnId;
	
	@JsonProperty("screenId")
	private String screenId;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}

	/**
	 * @param deductionType
	 *            the deductionType to set
	 */
	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the informationNumber
	 */
	public String getInformationNumber() {
		return informationNumber;
	}

	/**
	 * @param informationNumber
	 *            the informationNumber to set
	 */
	public void setInformationNumber(final String informationNumber) {
		this.informationNumber = informationNumber;
	}

	/**
	 *
	 * @return
	 */
	public Long getRequestId() {
		return requestId;
	}

	/**
	 *
	 * @param requestId
	 */
	public void setRequestId(final Long requestId) {
		this.requestId = requestId;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getDisplayFlag() {
		return displayFlag;
	}

	/**
	 *
	 * @param displayFlag
	 */
	public void setDisplayFlag(final String displayFlag) {
		this.displayFlag = displayFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getNumberOfCopy() {
		return numberOfCopy;
	}

	/**
	 *
	 * @param numberOfCopy
	 */
	public void setNumberOfCopy(final BigDecimal numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}

	/**
	 *
	 * @return
	 */
	public String getPrinterId() {
		return printerId;
	}

	/**
	 *
	 * @param printerId
	 */
	public void setPrinterId(final String printerId) {
		this.printerId = printerId;
	}

	/**
	 *
	 * @return
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 *
	 * @param requestDate
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 *
	 * @return
	 */
	public String getRequestStatus() {
		return requestStatus;
	}

	/**
	 *
	 * @param requestStatus
	 */
	public void setRequestStatus(final String requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getRequestUserId() {
		return requestUserId;
	}

	/**
	 *
	 * @param requestUserId
	 */
	public void setRequestUserId(final String requestUserId) {
		this.requestUserId = requestUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public OmsModules getOmsModule() {
		return omsModule;
	}

	/**
	 *
	 * @param omsModule
	 */
	public void setOmsModule(final OmsModules omsModule) {
		this.omsModule = omsModule;
	}

	/**
	 *
	 * @return
	 */
	public List<OmsRequestParameter> getOmsRequestParameters() {
		return omsRequestParameters;
	}

	/**
	 *
	 * @param omsRequestParameters
	 */
	public void setOmsRequestParameters(final List<OmsRequestParameter> omsRequestParameters) {
		this.omsRequestParameters = omsRequestParameters;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the nbtUserId
	 */
	public String getNbtUserId() {
		return nbtUserId;
	}

	/**
	 * @param nbtUserId the nbtUserId to set
	 */
	public void setNbtUserId(String nbtUserId) {
		this.nbtUserId = nbtUserId;
	}

	/**
	 * @return the nbtDateFrom
	 */
	public Date getNbtDateFrom() {
		return nbtDateFrom;
	}

	/**
	 * @param nbtDateFrom the nbtDateFrom to set
	 */
	public void setNbtDateFrom(Date nbtDateFrom) {
		this.nbtDateFrom = nbtDateFrom;
	}

	/**
	 * @return the nbtDateTo
	 */
	public Date getNbtDateTo() {
		return nbtDateTo;
	}

	/**
	 * @param nbtDateTo the nbtDateTo to set
	 */
	public void setNbtDateTo(Date nbtDateTo) {
		this.nbtDateTo = nbtDateTo;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	/**
	 * @return the txnId
	 */
	public BigDecimal getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(BigDecimal txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}