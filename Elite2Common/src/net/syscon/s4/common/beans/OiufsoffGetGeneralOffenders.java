package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OiufsoffGetGeneralOffenders extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pAgyLocId;
	private BigDecimal pLv1Id;
	private BigDecimal pLv2Id;
	private BigDecimal pLv3Id;
	private String pLastName;
	private String pFirstName;
	private String pMiddleName;
	private String pActiveFlag;
	private String pCaseloadId;
	private String pReportApplnCode;
	private String pOffenderIdDisplay;
	private String lastName;
	private String firstName;
	private BigDecimal offenderId;
	private BigDecimal rootOffenderId;
	private String offenderIdDisplay;
	private String agyLocId;
	private String prisonLocation;
	private BigDecimal offenderBookId;
	private String middleName;
	private String nbtActvTrustFlag;
	private String dspLvOneId;
	private String dspLvTwoId;
	private String dspLvThreeId;

	private String lvLivingUnitId;
	private String lvActiveFlag;
	private String lvcaseLoadType;

	public String getLvLivingUnitId() {
		return lvLivingUnitId;
	}

	public void setLvLivingUnitId(String lvLivingUnitId) {
		this.lvLivingUnitId = lvLivingUnitId;
	}

	public String getLvActiveFlag() {
		return lvActiveFlag;
	}

	public void setLvActiveFlag(String lvActiveFlag) {
		this.lvActiveFlag = lvActiveFlag;
	}

	public String getLvcaseLoadType() {
		return lvcaseLoadType;
	}

	public void setLvcaseLoadType(String lvcaseLoadType) {
		this.lvcaseLoadType = lvcaseLoadType;
	}

	public OiufsoffGetGeneralOffenders() {
		// OiufsoffGetGeneralOffenders
	}

	/**
	 * @return the pAgyLocId
	 */
	public String getpAgyLocId() {
		return pAgyLocId;
	}

	/**
	 * @param pAgyLocId the pAgyLocId to set
	 */
	public void setpAgyLocId(final String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	/**
	 * @return the pLv1Id
	 */
	public BigDecimal getpLv1Id() {
		return pLv1Id;
	}

	/**
	 * @param pLv1Id the pLv1Id to set
	 */
	public void setpLv1Id(final BigDecimal pLv1Id) {
		this.pLv1Id = pLv1Id;
	}

	/**
	 * @return the pLv2Id
	 */
	public BigDecimal getpLv2Id() {
		return pLv2Id;
	}

	/**
	 * @param pLv2Id the pLv2Id to set
	 */
	public void setpLv2Id(final BigDecimal pLv2Id) {
		this.pLv2Id = pLv2Id;
	}

	/**
	 * @return the pLv3Id
	 */
	public BigDecimal getpLv3Id() {
		return pLv3Id;
	}

	/**
	 * @param pLv3Id the pLv3Id to set
	 */
	public void setpLv3Id(final BigDecimal pLv3Id) {
		this.pLv3Id = pLv3Id;
	}

	/**
	 * @return the pLastName
	 */
	public String getpLastName() {
		return pLastName;
	}

	/**
	 * @param pLastName the pLastName to set
	 */
	public void setpLastName(final String pLastName) {
		this.pLastName = pLastName;
	}

	/**
	 * @return the pFirstName
	 */
	public String getpFirstName() {
		return pFirstName;
	}

	/**
	 * @param pFirstName the pFirstName to set
	 */
	public void setpFirstName(final String pFirstName) {
		this.pFirstName = pFirstName;
	}

	/**
	 * @return the pMiddleName
	 */
	public String getpMiddleName() {
		return pMiddleName;
	}

	/**
	 * @param pMiddleName the pMiddleName to set
	 */
	public void setpMiddleName(final String pMiddleName) {
		this.pMiddleName = pMiddleName;
	}

	/**
	 * @return the pActiveFlag
	 */
	public String getpActiveFlag() {
		return pActiveFlag;
	}

	/**
	 * @param pActiveFlag the pActiveFlag to set
	 */
	public void setpActiveFlag(final String pActiveFlag) {
		this.pActiveFlag = pActiveFlag;
	}

	/**
	 * @return the pCaseloadId
	 */
	public String getpCaseloadId() {
		return pCaseloadId;
	}

	/**
	 * @param pCaseloadId the pCaseloadId to set
	 */
	public void setpCaseloadId(final String pCaseloadId) {
		this.pCaseloadId = pCaseloadId;
	}

	/**
	 * @return the pReportApplnCode
	 */
	public String getpReportApplnCode() {
		return pReportApplnCode;
	}

	/**
	 * @param pReportApplnCode the pReportApplnCode to set
	 */
	public void setpReportApplnCode(final String pReportApplnCode) {
		this.pReportApplnCode = pReportApplnCode;
	}

	/**
	 * @return the pOffenderIdDisplay
	 */
	public String getpOffenderIdDisplay() {
		return pOffenderIdDisplay;
	}

	/**
	 * @param pOffenderIdDisplay the pOffenderIdDisplay to set
	 */
	public void setpOffenderIdDisplay(final String pOffenderIdDisplay) {
		this.pOffenderIdDisplay = pOffenderIdDisplay;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
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
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId the rootOffenderId to set
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the prisonLocation
	 */
	public String getPrisonLocation() {
		return prisonLocation;
	}

	/**
	 * @param prisonLocation the prisonLocation to set
	 */
	public void setPrisonLocation(final String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the nbtActvTrustFlag
	 */
	public String getNbtActvTrustFlag() {
		return nbtActvTrustFlag;
	}

	/**
	 * @param nbtActvTrustFlag the nbtActvTrustFlag to set
	 */
	public void setNbtActvTrustFlag(final String nbtActvTrustFlag) {
		this.nbtActvTrustFlag = nbtActvTrustFlag;
	}

	/**
	 * @return the dspLvOneId
	 */
	public String getDspLvOneId() {
		return dspLvOneId;
	}

	/**
	 * @param dspLvOneId the dspLvOneId to set
	 */
	public void setDspLvOneId(final String dspLvOneId) {
		this.dspLvOneId = dspLvOneId;
	}

	/**
	 * @return the dspLvTwoId
	 */
	public String getDspLvTwoId() {
		return dspLvTwoId;
	}

	/**
	 * @param dspLvTwoId the dspLvTwoId to set
	 */
	public void setDspLvTwoId(final String dspLvTwoId) {
		this.dspLvTwoId = dspLvTwoId;
	}

	/**
	 * @return the dspLvThreeId
	 */
	public String getDspLvThreeId() {
		return dspLvThreeId;
	}

	/**
	 * @param dspLvThreeId the dspLvThreeId to set
	 */
	public void setDspLvThreeId(final String dspLvThreeId) {
		this.dspLvThreeId = dspLvThreeId;
	}

}
