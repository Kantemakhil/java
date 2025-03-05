package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtmtfproFmbBean  extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cgCtrlcgDt")
	private String cgCtrlcgDt;

	@JsonProperty("cgCtrlcgUs")
	private String cgCtrlcgUs;

	@JsonProperty("cgCtrlcguSysdate")
	private String cgCtrlcguSysdate;

	@JsonProperty("cgCtrlcguUser")
	private String cgCtrlcguUser;

	@JsonProperty("cgCtrlcgPage1List")
	private String cgCtrlcgPage1List;

	@JsonProperty("cgCtrldummyPage")
	private String cgCtrldummyPage;

	@JsonProperty("cgCtrlrootWindowPage")
	private String cgCtrlrootWindowPage;

	@JsonProperty("cgCtrlcgLastCanvas")
	private String cgCtrlcgLastCanvas;

	@JsonProperty("activeCaseloadactiveCaseload")
	private String activeCaseloadactiveCaseload;

	@JsonProperty("activeCaseloadup")
	private String activeCaseloadup;

	@JsonProperty("activeCaseloaddw")
	private String activeCaseloaddw;

	@JsonProperty("csldDpcaseloadId")
	private String csldDpcaseloadId;

	@JsonProperty("csldDpmodifyUserId")
	private String csldDpmodifyUserId;

	@JsonProperty("csldDpcoCreditWhenIndigentFlag")
	private String csldDpcoCreditWhenIndigentFlag;

	@JsonProperty("csldDpmaxMonthlyAmount")
	private BigDecimal csldDpmaxMonthlyAmount;

	@JsonProperty("csldDpmaxTotalAmount")
	private BigDecimal csldDpmaxTotalAmount;

	@JsonProperty("csldDpdelayRecapture")
	private BigDecimal csldDpdelayRecapture;

	@JsonProperty("csldDplistSeq")
	private BigDecimal csldDplistSeq;

	@JsonProperty("csldDppayeePersonId")
	private BigDecimal csldDppayeePersonId;

	@JsonProperty("csldDpdeductionType")
	private String csldDpdeductionType;

	@JsonProperty("csldDpdeductionType2")
	private String csldDpdeductionType2;

	@JsonProperty("csldDpdspDeductionDesc")
	private String csldDpdspDeductionDesc;

	@JsonProperty("csldDpaccountCode")
	private BigDecimal csldDpaccountCode;

	@JsonProperty("csldDpaccountCode2")
	private String csldDpaccountCode2;

	@JsonProperty("csldDpdspAccountName")
	private String csldDpdspAccountName;

	@JsonProperty("csldDpeffectiveDate")
	private Date csldDpeffectiveDate;

	@JsonProperty("csldDpactiveFlag")
	private String csldDpactiveFlag;

	@JsonProperty("csldDpexpiryDate")
	private Date csldDpexpiryDate;

	@JsonProperty("csldDpfoAlAllOffenderFlag")
	private String csldDpfoAlAllOffenderFlag;

	@JsonProperty("csldDppayeeCorporateId")
	private BigDecimal csldDppayeeCorporateId;

	@JsonProperty("csldDppayeeCorporateId2")
	private String csldDppayeeCorporateId2;

	@JsonProperty("csldDpdspCorporateName")
	private String csldDpdspCorporateName;

	@JsonProperty("csldDpfifoFlag")
	private String csldDpfifoFlag;

	@JsonProperty("csldDppercentage")
	private BigDecimal csldDppercentage;

	@JsonProperty("csldDpcoLimitAmount")
	private BigDecimal csldDpcoLimitAmount;

	@JsonProperty("csldDpmodifyDate")
	private Date csldDpmodifyDate;

	@JsonProperty("csldDpinternalPriorityNo")
	private BigDecimal csldDpinternalPriorityNo;

	@JsonProperty("csldDpexternalPriorityNo")
	private BigDecimal csldDpexternalPriorityNo;

	@JsonProperty("csldDdcaseloadId")
	private String csldDdcaseloadId;

	@JsonProperty("csldDddeductionType")
	private String csldDddeductionType;

	@JsonProperty("csldDdreceiptTxnType")
	private String csldDdreceiptTxnType;

	@JsonProperty("csldDdreceiptTxnType2")
	private String csldDdreceiptTxnType2;

	@JsonProperty("csldDddspDescription")
	private String csldDddspDescription;

	@JsonProperty("csldDdpercentage")
	private BigDecimal csldDdpercentage;

	@JsonProperty("csldDdflatRate")
	private BigDecimal csldDdflatRate;

	@JsonProperty("csldDdmodifyUserId")
	private String csldDdmodifyUserId;

	@JsonProperty("csldDdmodifyDate")
	private Date csldDdmodifyDate;

	@JsonProperty("csldDdlistSeq")
	private BigDecimal csldDdlistSeq;

	@JsonProperty("sysPflprofileValue")
	private String sysPflprofileValue;

	@JsonProperty("sysPflcgnbtProfileValue")
	private String sysPflcgnbtProfileValue;

	@JsonProperty("sysPfldspNameLabel")
	private String sysPfldspNameLabel;

	@JsonProperty("sysPfldspLocLabel")
	private String sysPfldspLocLabel;

	@JsonProperty("mymenubutOffenders")
	private String mymenubutOffenders;

	@JsonProperty("mymenubutWorks")
	private String mymenubutWorks;

	@JsonProperty("mymenubutCalendar")
	private String mymenubutCalendar;

	@JsonProperty("mymenubutOffUpdates")
	private String mymenubutOffUpdates;

	@JsonProperty("mymenubutDetail")
	private String mymenubutDetail;
	
	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

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

	public OtmtfproFmbBean() {}

	/**
	 * @return the cgCtrlcgDt
	 */
	public String getCgCtrlcgDt() {
		return cgCtrlcgDt;
	}

	/**
	 * @param cgCtrlcgDt the cgCtrlcgDt to set
	 */
	public void setCgCtrlcgDt(final String cgCtrlcgDt) {
		this.cgCtrlcgDt = cgCtrlcgDt;
	}

	/**
	 * @return the cgCtrlcgUs
	 */
	public String getCgCtrlcgUs() {
		return cgCtrlcgUs;
	}

	/**
	 * @param cgCtrlcgUs the cgCtrlcgUs to set
	 */
	public void setCgCtrlcgUs(final String cgCtrlcgUs) {
		this.cgCtrlcgUs = cgCtrlcgUs;
	}

	/**
	 * @return the cgCtrlcguSysdate
	 */
	public String getCgCtrlcguSysdate() {
		return cgCtrlcguSysdate;
	}

	/**
	 * @param cgCtrlcguSysdate the cgCtrlcguSysdate to set
	 */
	public void setCgCtrlcguSysdate(final String cgCtrlcguSysdate) {
		this.cgCtrlcguSysdate = cgCtrlcguSysdate;
	}

	/**
	 * @return the cgCtrlcguUser
	 */
	public String getCgCtrlcguUser() {
		return cgCtrlcguUser;
	}

	/**
	 * @param cgCtrlcguUser the cgCtrlcguUser to set
	 */
	public void setCgCtrlcguUser(final String cgCtrlcguUser) {
		this.cgCtrlcguUser = cgCtrlcguUser;
	}

	/**
	 * @return the cgCtrlcgPage1List
	 */
	public String getCgCtrlcgPage1List() {
		return cgCtrlcgPage1List;
	}

	/**
	 * @param cgCtrlcgPage1List the cgCtrlcgPage1List to set
	 */
	public void setCgCtrlcgPage1List(final String cgCtrlcgPage1List) {
		this.cgCtrlcgPage1List = cgCtrlcgPage1List;
	}

	/**
	 * @return the cgCtrldummyPage
	 */
	public String getCgCtrldummyPage() {
		return cgCtrldummyPage;
	}

	/**
	 * @param cgCtrldummyPage the cgCtrldummyPage to set
	 */
	public void setCgCtrldummyPage(final String cgCtrldummyPage) {
		this.cgCtrldummyPage = cgCtrldummyPage;
	}

	/**
	 * @return the cgCtrlrootWindowPage
	 */
	public String getCgCtrlrootWindowPage() {
		return cgCtrlrootWindowPage;
	}

	/**
	 * @param cgCtrlrootWindowPage the cgCtrlrootWindowPage to set
	 */
	public void setCgCtrlrootWindowPage(final String cgCtrlrootWindowPage) {
		this.cgCtrlrootWindowPage = cgCtrlrootWindowPage;
	}

	/**
	 * @return the cgCtrlcgLastCanvas
	 */
	public String getCgCtrlcgLastCanvas() {
		return cgCtrlcgLastCanvas;
	}

	/**
	 * @param cgCtrlcgLastCanvas the cgCtrlcgLastCanvas to set
	 */
	public void setCgCtrlcgLastCanvas(final String cgCtrlcgLastCanvas) {
		this.cgCtrlcgLastCanvas = cgCtrlcgLastCanvas;
	}

	/**
	 * @return the activeCaseloadactiveCaseload
	 */
	public String getActiveCaseloadactiveCaseload() {
		return activeCaseloadactiveCaseload;
	}

	/**
	 * @param activeCaseloadactiveCaseload the activeCaseloadactiveCaseload to set
	 */
	public void setActiveCaseloadactiveCaseload(final String activeCaseloadactiveCaseload) {
		this.activeCaseloadactiveCaseload = activeCaseloadactiveCaseload;
	}

	/**
	 * @return the activeCaseloadup
	 */
	public String getActiveCaseloadup() {
		return activeCaseloadup;
	}

	/**
	 * @param activeCaseloadup the activeCaseloadup to set
	 */
	public void setActiveCaseloadup(final String activeCaseloadup) {
		this.activeCaseloadup = activeCaseloadup;
	}

	/**
	 * @return the activeCaseloaddw
	 */
	public String getActiveCaseloaddw() {
		return activeCaseloaddw;
	}

	/**
	 * @param activeCaseloaddw the activeCaseloaddw to set
	 */
	public void setActiveCaseloaddw(final String activeCaseloaddw) {
		this.activeCaseloaddw = activeCaseloaddw;
	}

	/**
	 * @return the csldDpcaseloadId
	 */
	public String getCsldDpcaseloadId() {
		return csldDpcaseloadId;
	}

	/**
	 * @param csldDpcaseloadId the csldDpcaseloadId to set
	 */
	public void setCsldDpcaseloadId(final String csldDpcaseloadId) {
		this.csldDpcaseloadId = csldDpcaseloadId;
	}

	/**
	 * @return the csldDpmodifyUserId
	 */
	public String getCsldDpmodifyUserId() {
		return csldDpmodifyUserId;
	}

	/**
	 * @param csldDpmodifyUserId the csldDpmodifyUserId to set
	 */
	public void setCsldDpmodifyUserId(final String csldDpmodifyUserId) {
		this.csldDpmodifyUserId = csldDpmodifyUserId;
	}

	/**
	 * @return the csldDpcoCreditWhenIndigentFlag
	 */
	public String getCsldDpcoCreditWhenIndigentFlag() {
		return csldDpcoCreditWhenIndigentFlag;
	}

	/**
	 * @param csldDpcoCreditWhenIndigentFlag the csldDpcoCreditWhenIndigentFlag to set
	 */
	public void setCsldDpcoCreditWhenIndigentFlag(final String csldDpcoCreditWhenIndigentFlag) {
		this.csldDpcoCreditWhenIndigentFlag = csldDpcoCreditWhenIndigentFlag;
	}

	/**
	 * @return the csldDpmaxMonthlyAmount
	 */
	public BigDecimal getCsldDpmaxMonthlyAmount() {
		return csldDpmaxMonthlyAmount;
	}

	/**
	 * @param csldDpmaxMonthlyAmount the csldDpmaxMonthlyAmount to set
	 */
	public void setCsldDpmaxMonthlyAmount(BigDecimal csldDpmaxMonthlyAmount) {
		this.csldDpmaxMonthlyAmount = csldDpmaxMonthlyAmount;
	}

	/**
	 * @return the csldDpmaxTotalAmount
	 */
	public BigDecimal getCsldDpmaxTotalAmount() {
		return csldDpmaxTotalAmount;
	}

	/**
	 * @param csldDpmaxTotalAmount the csldDpmaxTotalAmount to set
	 */
	public void setCsldDpmaxTotalAmount(BigDecimal csldDpmaxTotalAmount) {
		this.csldDpmaxTotalAmount = csldDpmaxTotalAmount;
	}

	/**
	 * @return the csldDpdelayRecapture
	 */
	public BigDecimal getCsldDpdelayRecapture() {
		return csldDpdelayRecapture;
	}

	/**
	 * @param csldDpdelayRecapture the csldDpdelayRecapture to set
	 */
	public void setCsldDpdelayRecapture(BigDecimal csldDpdelayRecapture) {
		this.csldDpdelayRecapture = csldDpdelayRecapture;
	}

	/**
	 * @return the csldDplistSeq
	 */
	public BigDecimal getCsldDplistSeq() {
		return csldDplistSeq;
	}

	/**
	 * @param csldDplistSeq the csldDplistSeq to set
	 */
	public void setCsldDplistSeq(BigDecimal csldDplistSeq) {
		this.csldDplistSeq = csldDplistSeq;
	}

	/**
	 * @return the csldDppayeePersonId
	 */
	public BigDecimal getCsldDppayeePersonId() {
		return csldDppayeePersonId;
	}

	/**
	 * @param csldDppayeePersonId the csldDppayeePersonId to set
	 */
	public void setCsldDppayeePersonId(BigDecimal csldDppayeePersonId) {
		this.csldDppayeePersonId = csldDppayeePersonId;
	}

	/**
	 * @return the csldDpdeductionType
	 */
	public String getCsldDpdeductionType() {
		return csldDpdeductionType;
	}

	/**
	 * @param csldDpdeductionType the csldDpdeductionType to set
	 */
	public void setCsldDpdeductionType(final String csldDpdeductionType) {
		this.csldDpdeductionType = csldDpdeductionType;
	}

	/**
	 * @return the csldDpdeductionType2
	 */
	public String getCsldDpdeductionType2() {
		return csldDpdeductionType2;
	}

	/**
	 * @param csldDpdeductionType2 the csldDpdeductionType2 to set
	 */
	public void setCsldDpdeductionType2(final String csldDpdeductionType2) {
		this.csldDpdeductionType2 = csldDpdeductionType2;
	}

	/**
	 * @return the csldDpdspDeductionDesc
	 */
	public String getCsldDpdspDeductionDesc() {
		return csldDpdspDeductionDesc;
	}

	/**
	 * @param csldDpdspDeductionDesc the csldDpdspDeductionDesc to set
	 */
	public void setCsldDpdspDeductionDesc(final String csldDpdspDeductionDesc) {
		this.csldDpdspDeductionDesc = csldDpdspDeductionDesc;
	}

	/**
	 * @return the csldDpaccountCode
	 */
	public BigDecimal getCsldDpaccountCode() {
		return csldDpaccountCode;
	}

	/**
	 * @param csldDpaccountCode the csldDpaccountCode to set
	 */
	public void setCsldDpaccountCode(BigDecimal csldDpaccountCode) {
		this.csldDpaccountCode = csldDpaccountCode;
	}

	/**
	 * @return the csldDpaccountCode2
	 */
	public String getCsldDpaccountCode2() {
		return csldDpaccountCode2;
	}

	/**
	 * @param csldDpaccountCode2 the csldDpaccountCode2 to set
	 */
	public void setCsldDpaccountCode2(final String csldDpaccountCode2) {
		this.csldDpaccountCode2 = csldDpaccountCode2;
	}

	/**
	 * @return the csldDpdspAccountName
	 */
	public String getCsldDpdspAccountName() {
		return csldDpdspAccountName;
	}

	/**
	 * @param csldDpdspAccountName the csldDpdspAccountName to set
	 */
	public void setCsldDpdspAccountName(final String csldDpdspAccountName) {
		this.csldDpdspAccountName = csldDpdspAccountName;
	}

	/**
	 * @return the csldDpeffectiveDate
	 */
	public Date getCsldDpeffectiveDate() {
		return csldDpeffectiveDate;
	}

	/**
	 * @param csldDpeffectiveDate the csldDpeffectiveDate to set
	 */
	public void setCsldDpeffectiveDate(final Date csldDpeffectiveDate) {
		this.csldDpeffectiveDate = csldDpeffectiveDate;
	}

	/**
	 * @return the csldDpactiveFlag
	 */
	public String getCsldDpactiveFlag() {
		return csldDpactiveFlag;
	}

	/**
	 * @param csldDpactiveFlag the csldDpactiveFlag to set
	 */
	public void setCsldDpactiveFlag(final String csldDpactiveFlag) {
		this.csldDpactiveFlag = csldDpactiveFlag;
	}

	/**
	 * @return the csldDpexpiryDate
	 */
	public Date getCsldDpexpiryDate() {
		return csldDpexpiryDate;
	}

	/**
	 * @param csldDpexpiryDate the csldDpexpiryDate to set
	 */
	public void setCsldDpexpiryDate(final Date csldDpexpiryDate) {
		this.csldDpexpiryDate = csldDpexpiryDate;
	}

	/**
	 * @return the csldDpfoAlAllOffenderFlag
	 */
	public String getCsldDpfoAlAllOffenderFlag() {
		return csldDpfoAlAllOffenderFlag;
	}

	/**
	 * @param csldDpfoAlAllOffenderFlag the csldDpfoAlAllOffenderFlag to set
	 */
	public void setCsldDpfoAlAllOffenderFlag(final String csldDpfoAlAllOffenderFlag) {
		this.csldDpfoAlAllOffenderFlag = csldDpfoAlAllOffenderFlag;
	}

	/**
	 * @return the csldDppayeeCorporateId
	 */
	public BigDecimal getCsldDppayeeCorporateId() {
		return csldDppayeeCorporateId;
	}

	/**
	 * @param csldDppayeeCorporateId the csldDppayeeCorporateId to set
	 */
	public void setCsldDppayeeCorporateId(BigDecimal csldDppayeeCorporateId) {
		this.csldDppayeeCorporateId = csldDppayeeCorporateId;
	}

	/**
	 * @return the csldDppayeeCorporateId2
	 */
	public String getCsldDppayeeCorporateId2() {
		return csldDppayeeCorporateId2;
	}

	/**
	 * @param csldDppayeeCorporateId2 the csldDppayeeCorporateId2 to set
	 */
	public void setCsldDppayeeCorporateId2(final String csldDppayeeCorporateId2) {
		this.csldDppayeeCorporateId2 = csldDppayeeCorporateId2;
	}

	/**
	 * @return the csldDpdspCorporateName
	 */
	public String getCsldDpdspCorporateName() {
		return csldDpdspCorporateName;
	}

	/**
	 * @param csldDpdspCorporateName the csldDpdspCorporateName to set
	 */
	public void setCsldDpdspCorporateName(final String csldDpdspCorporateName) {
		this.csldDpdspCorporateName = csldDpdspCorporateName;
	}

	/**
	 * @return the csldDpfifoFlag
	 */
	public String getCsldDpfifoFlag() {
		return csldDpfifoFlag;
	}

	/**
	 * @param csldDpfifoFlag the csldDpfifoFlag to set
	 */
	public void setCsldDpfifoFlag(final String csldDpfifoFlag) {
		this.csldDpfifoFlag = csldDpfifoFlag;
	}

	/**
	 * @return the csldDppercentage
	 */
	public BigDecimal getCsldDppercentage() {
		return csldDppercentage;
	}

	/**
	 * @param csldDppercentage the csldDppercentage to set
	 */
	public void setCsldDppercentage(BigDecimal csldDppercentage) {
		this.csldDppercentage = csldDppercentage;
	}

	/**
	 * @return the csldDpcoLimitAmount
	 */
	public BigDecimal getCsldDpcoLimitAmount() {
		return csldDpcoLimitAmount;
	}

	/**
	 * @param csldDpcoLimitAmount the csldDpcoLimitAmount to set
	 */
	public void setCsldDpcoLimitAmount(BigDecimal csldDpcoLimitAmount) {
		this.csldDpcoLimitAmount = csldDpcoLimitAmount;
	}

	/**
	 * @return the csldDpmodifyDate
	 */
	public Date getCsldDpmodifyDate() {
		return csldDpmodifyDate;
	}

	/**
	 * @param csldDpmodifyDate the csldDpmodifyDate to set
	 */
	public void setCsldDpmodifyDate(final Date csldDpmodifyDate) {
		this.csldDpmodifyDate = csldDpmodifyDate;
	}

	/**
	 * @return the csldDpinternalPriorityNo
	 */
	public BigDecimal getCsldDpinternalPriorityNo() {
		return csldDpinternalPriorityNo;
	}

	/**
	 * @param csldDpinternalPriorityNo the csldDpinternalPriorityNo to set
	 */
	public void setCsldDpinternalPriorityNo(BigDecimal csldDpinternalPriorityNo) {
		this.csldDpinternalPriorityNo = csldDpinternalPriorityNo;
	}

	/**
	 * @return the csldDpexternalPriorityNo
	 */
	public BigDecimal getCsldDpexternalPriorityNo() {
		return csldDpexternalPriorityNo;
	}

	/**
	 * @param csldDpexternalPriorityNo the csldDpexternalPriorityNo to set
	 */
	public void setCsldDpexternalPriorityNo(BigDecimal csldDpexternalPriorityNo) {
		this.csldDpexternalPriorityNo = csldDpexternalPriorityNo;
	}

	/**
	 * @return the csldDdcaseloadId
	 */
	public String getCsldDdcaseloadId() {
		return csldDdcaseloadId;
	}

	/**
	 * @param csldDdcaseloadId the csldDdcaseloadId to set
	 */
	public void setCsldDdcaseloadId(final String csldDdcaseloadId) {
		this.csldDdcaseloadId = csldDdcaseloadId;
	}

	/**
	 * @return the csldDddeductionType
	 */
	public String getCsldDddeductionType() {
		return csldDddeductionType;
	}

	/**
	 * @param csldDddeductionType the csldDddeductionType to set
	 */
	public void setCsldDddeductionType(final String csldDddeductionType) {
		this.csldDddeductionType = csldDddeductionType;
	}

	/**
	 * @return the csldDdreceiptTxnType
	 */
	public String getCsldDdreceiptTxnType() {
		return csldDdreceiptTxnType;
	}

	/**
	 * @param csldDdreceiptTxnType the csldDdreceiptTxnType to set
	 */
	public void setCsldDdreceiptTxnType(final String csldDdreceiptTxnType) {
		this.csldDdreceiptTxnType = csldDdreceiptTxnType;
	}

	/**
	 * @return the csldDdreceiptTxnType2
	 */
	public String getCsldDdreceiptTxnType2() {
		return csldDdreceiptTxnType2;
	}

	/**
	 * @param csldDdreceiptTxnType2 the csldDdreceiptTxnType2 to set
	 */
	public void setCsldDdreceiptTxnType2(final String csldDdreceiptTxnType2) {
		this.csldDdreceiptTxnType2 = csldDdreceiptTxnType2;
	}

	/**
	 * @return the csldDddspDescription
	 */
	public String getCsldDddspDescription() {
		return csldDddspDescription;
	}

	/**
	 * @param csldDddspDescription the csldDddspDescription to set
	 */
	public void setCsldDddspDescription(final String csldDddspDescription) {
		this.csldDddspDescription = csldDddspDescription;
	}

	/**
	 * @return the csldDdpercentage
	 */
	public BigDecimal getCsldDdpercentage() {
		return csldDdpercentage;
	}

	/**
	 * @param csldDdpercentage the csldDdpercentage to set
	 */
	public void setCsldDdpercentage(BigDecimal csldDdpercentage) {
		this.csldDdpercentage = csldDdpercentage;
	}

	/**
	 * @return the csldDdflatRate
	 */
	public BigDecimal getCsldDdflatRate() {
		return csldDdflatRate;
	}

	/**
	 * @param csldDdflatRate the csldDdflatRate to set
	 */
	public void setCsldDdflatRate(BigDecimal csldDdflatRate) {
		this.csldDdflatRate = csldDdflatRate;
	}

	/**
	 * @return the csldDdmodifyUserId
	 */
	public String getCsldDdmodifyUserId() {
		return csldDdmodifyUserId;
	}

	/**
	 * @param csldDdmodifyUserId the csldDdmodifyUserId to set
	 */
	public void setCsldDdmodifyUserId(final String csldDdmodifyUserId) {
		this.csldDdmodifyUserId = csldDdmodifyUserId;
	}

	/**
	 * @return the csldDdmodifyDate
	 */
	public Date getCsldDdmodifyDate() {
		return csldDdmodifyDate;
	}

	/**
	 * @param csldDdmodifyDate the csldDdmodifyDate to set
	 */
	public void setCsldDdmodifyDate(final Date csldDdmodifyDate) {
		this.csldDdmodifyDate = csldDdmodifyDate;
	}

	/**
	 * @return the csldDdlistSeq
	 */
	public BigDecimal getCsldDdlistSeq() {
		return csldDdlistSeq;
	}

	/**
	 * @param csldDdlistSeq the csldDdlistSeq to set
	 */
	public void setCsldDdlistSeq(BigDecimal csldDdlistSeq) {
		this.csldDdlistSeq = csldDdlistSeq;
	}

	/**
	 * @return the sysPflprofileValue
	 */
	public String getSysPflprofileValue() {
		return sysPflprofileValue;
	}

	/**
	 * @param sysPflprofileValue the sysPflprofileValue to set
	 */
	public void setSysPflprofileValue(final String sysPflprofileValue) {
		this.sysPflprofileValue = sysPflprofileValue;
	}

	/**
	 * @return the sysPflcgnbtProfileValue
	 */
	public String getSysPflcgnbtProfileValue() {
		return sysPflcgnbtProfileValue;
	}

	/**
	 * @param sysPflcgnbtProfileValue the sysPflcgnbtProfileValue to set
	 */
	public void setSysPflcgnbtProfileValue(final String sysPflcgnbtProfileValue) {
		this.sysPflcgnbtProfileValue = sysPflcgnbtProfileValue;
	}

	/**
	 * @return the sysPfldspNameLabel
	 */
	public String getSysPfldspNameLabel() {
		return sysPfldspNameLabel;
	}

	/**
	 * @param sysPfldspNameLabel the sysPfldspNameLabel to set
	 */
	public void setSysPfldspNameLabel(final String sysPfldspNameLabel) {
		this.sysPfldspNameLabel = sysPfldspNameLabel;
	}

	/**
	 * @return the sysPfldspLocLabel
	 */
	public String getSysPfldspLocLabel() {
		return sysPfldspLocLabel;
	}

	/**
	 * @param sysPfldspLocLabel the sysPfldspLocLabel to set
	 */
	public void setSysPfldspLocLabel(final String sysPfldspLocLabel) {
		this.sysPfldspLocLabel = sysPfldspLocLabel;
	}

	/**
	 * @return the mymenubutOffenders
	 */
	public String getMymenubutOffenders() {
		return mymenubutOffenders;
	}

	/**
	 * @param mymenubutOffenders the mymenubutOffenders to set
	 */
	public void setMymenubutOffenders(final String mymenubutOffenders) {
		this.mymenubutOffenders = mymenubutOffenders;
	}

	/**
	 * @return the mymenubutWorks
	 */
	public String getMymenubutWorks() {
		return mymenubutWorks;
	}

	/**
	 * @param mymenubutWorks the mymenubutWorks to set
	 */
	public void setMymenubutWorks(final String mymenubutWorks) {
		this.mymenubutWorks = mymenubutWorks;
	}

	/**
	 * @return the mymenubutCalendar
	 */
	public String getMymenubutCalendar() {
		return mymenubutCalendar;
	}

	/**
	 * @param mymenubutCalendar the mymenubutCalendar to set
	 */
	public void setMymenubutCalendar(final String mymenubutCalendar) {
		this.mymenubutCalendar = mymenubutCalendar;
	}

	/**
	 * @return the mymenubutOffUpdates
	 */
	public String getMymenubutOffUpdates() {
		return mymenubutOffUpdates;
	}

	/**
	 * @param mymenubutOffUpdates the mymenubutOffUpdates to set
	 */
	public void setMymenubutOffUpdates(final String mymenubutOffUpdates) {
		this.mymenubutOffUpdates = mymenubutOffUpdates;
	}

	/**
	 * @return the mymenubutDetail
	 */
	public String getMymenubutDetail() {
		return mymenubutDetail;
	}

	/**
	 * @param mymenubutDetail the mymenubutDetail to set
	 */
	public void setMymenubutDetail(final String mymenubutDetail) {
		this.mymenubutDetail = mymenubutDetail;
	}
	
	

}