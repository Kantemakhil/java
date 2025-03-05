package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the OFFENDER_DEDUCTIONS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class DeductionsLogBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fSysDateOne")
	private Date fSysDateOne;
	@JsonProperty("fReportHeaderLabelTwo")
	private String fReportHeaderLabelTwo;
	@JsonProperty("fPageOne")
	private Long fPageOne;
	@JsonProperty("fTotOnw")
	private Long fTotOnw;
	@JsonProperty("fUserOne")
	private String fUserOne;
	@JsonProperty("fReportHeaderLabelOne")
	private String fReportHeaderLabelOne;
	@JsonProperty("fTwo")
	private String fTwo;
	@JsonProperty("fThree")
	private String fThree;
	@JsonProperty("fFour")
	private String fFour;
	@JsonProperty("fWriteOfDeductionType")
	private String fWriteOfDeductionType;
	@JsonProperty("fInfoNumber")
	private String fInfoNumber;
	@JsonProperty("fOffenderId")
	private String fOffenderId;
	@JsonProperty("fDeductionType")
	private String fDeductionType;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;
	@JsonProperty("fCsTotDebit")
	private String fCsTotDebit;
	@JsonProperty("fCsTotPayments")
	private BigDecimal fCsTotPayments;
	@JsonProperty("fCfTotOwing")
	private String fCfTotOwing;

	private List<DeductionsHistoryBean> deductionsHistoryBean;
	private List<DeductionsHistoryBean> deductionsProfileHistoryBean;

	/**
	 * @return the fCsTotPayments
	 */
	public BigDecimal getfCsTotPayments() {
		return fCsTotPayments;
	}

	/**
	 * @param fCsTotPayments
	 *            the fCsTotPayments to set
	 */
	public void setfCsTotPayments(final BigDecimal fCsTotPayments) {
		this.fCsTotPayments = fCsTotPayments;
	}

	/**
	 * @return the fCfTotOwing
	 */
	public String getfCfTotOwing() {
		return fCfTotOwing;
	}

	/**
	 * @param fCfTotOwing
	 *            the fCfTotOwing to set
	 */
	public void setfCfTotOwing(final String fCfTotOwing) {
		this.fCfTotOwing = fCfTotOwing;
	}

	/**
	 * @return the fCsTotDebit
	 */
	public String getfCsTotDebit() {
		return fCsTotDebit;
	}

	/**
	 * @param fCsTotDebit
	 *            the fCsTotDebit to set
	 */
	public void setfCsTotDebit(final String fCsTotDebit) {
		this.fCsTotDebit = fCsTotDebit;
	}

	/**
	 * @return the deductionsProfileHistoryBean
	 */
	public List<DeductionsHistoryBean> getDeductionsProfileHistoryBean() {
		return deductionsProfileHistoryBean;
	}

	/**
	 * @param deductionsProfileHistoryBean
	 *            the deductionsProfileHistoryBean to set
	 */
	public void setDeductionsProfileHistoryBean(final List<DeductionsHistoryBean> deductionsProfileHistoryBean) {
		this.deductionsProfileHistoryBean = deductionsProfileHistoryBean;
	}

	/**
	 * @return the deductionsHistoryBean
	 */
	public List<DeductionsHistoryBean> getDeductionsHistoryBean() {
		return deductionsHistoryBean;
	}

	/**
	 * @param deductionsHistoryBean
	 *            the deductionsHistoryBean to set
	 */
	public void setDeductionsHistoryBean(final List<DeductionsHistoryBean> deductionsHistoryBean) {
		this.deductionsHistoryBean = deductionsHistoryBean;
	}

	public DeductionsLogBean() {
		// DeductionsLogBean
	}

	public Long getOffenderDeductionId() {
		return this.offenderDeductionId;
	}

	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the fSysDateOne
	 */
	public Date getfSysDateOne() {
		return fSysDateOne;
	}

	/**
	 * @param fSysDateOne
	 *            the fSysDateOne to set
	 */
	public void setfSysDateOne(final Date fSysDateOne) {
		this.fSysDateOne = fSysDateOne;
	}

	/**
	 * @return the fReportHeaderLabelTwo
	 */
	public String getfReportHeaderLabelTwo() {
		return fReportHeaderLabelTwo;
	}

	/**
	 * @param fReportHeaderLabelTwo
	 *            the fReportHeaderLabelTwo to set
	 */
	public void setfReportHeaderLabelTwo(final String fReportHeaderLabelTwo) {
		this.fReportHeaderLabelTwo = fReportHeaderLabelTwo;
	}

	/**
	 * @return the fPageOne
	 */
	public Long getfPageOne() {
		return fPageOne;
	}

	/**
	 * @param fPageOne
	 *            the fPageOne to set
	 */
	public void setfPageOne(final Long fPageOne) {
		this.fPageOne = fPageOne;
	}

	/**
	 * @return the fTotOnw
	 */
	public Long getfTotOnw() {
		return fTotOnw;
	}

	/**
	 * @param fTotOnw
	 *            the fTotOnw to set
	 */
	public void setfTotOnw(final Long fTotOnw) {
		this.fTotOnw = fTotOnw;
	}

	/**
	 * @return the fUserOne
	 */
	public String getfUserOne() {
		return fUserOne;
	}

	/**
	 * @param fUserOne
	 *            the fUserOne to set
	 */
	public void setfUserOne(final String fUserOne) {
		this.fUserOne = fUserOne;
	}

	/**
	 * @return the fReportHeaderLabelOne
	 */
	public String getfReportHeaderLabelOne() {
		return fReportHeaderLabelOne;
	}

	/**
	 * @param fReportHeaderLabelOne
	 *            the fReportHeaderLabelOne to set
	 */
	public void setfReportHeaderLabelOne(final String fReportHeaderLabelOne) {
		this.fReportHeaderLabelOne = fReportHeaderLabelOne;
	}

	/**
	 * @return the fTwo
	 */
	public String getfTwo() {
		return fTwo;
	}

	/**
	 * @param fTwo
	 *            the fTwo to set
	 */
	public void setfTwo(final String fTwo) {
		this.fTwo = fTwo;
	}

	/**
	 * @return the fThree
	 */
	public String getfThree() {
		return fThree;
	}

	/**
	 * @param fThree
	 *            the fThree to set
	 */
	public void setfThree(final String fThree) {
		this.fThree = fThree;
	}

	/**
	 * @return the fFour
	 */
	public String getfFour() {
		return fFour;
	}

	/**
	 * @param fFour
	 *            the fFour to set
	 */
	public void setfFour(final String fFour) {
		this.fFour = fFour;
	}

	/**
	 * @return the fWriteOfDeductionType
	 */
	public String getfWriteOfDeductionType() {
		return fWriteOfDeductionType;
	}

	/**
	 * @param fWriteOfDeductionType
	 *            the fWriteOfDeductionType to set
	 */
	public void setfWriteOfDeductionType(final String fWriteOfDeductionType) {
		this.fWriteOfDeductionType = fWriteOfDeductionType;
	}

	/**
	 * @return the fInfoNumber
	 */
	public String getfInfoNumber() {
		return fInfoNumber;
	}

	/**
	 * @param fInfoNumber
	 *            the fInfoNumber to set
	 */
	public void setfInfoNumber(final String fInfoNumber) {
		this.fInfoNumber = fInfoNumber;
	}

	/**
	 * @return the fOffenderId
	 */
	public String getfOffenderId() {
		return fOffenderId;
	}

	/**
	 * @param fOffenderId
	 *            the fOffenderId to set
	 */
	public void setfOffenderId(final String fOffenderId) {
		this.fOffenderId = fOffenderId;
	}

	/**
	 * @return the fDeductionType
	 */
	public String getfDeductionType() {
		return fDeductionType;
	}

	/**
	 * @param fDeductionType
	 *            the fDeductionType to set
	 */
	public void setfDeductionType(final String fDeductionType) {
		this.fDeductionType = fDeductionType;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}
}
