package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagSearchPopulateOffDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("pRootOffenderId")
	private BigDecimal pRootOffenderId;

	@JsonProperty("pPrisonLocation")
	private String pPrisonLocation;

	@JsonProperty("pCommunityOfficer")
	private String pCommunityOfficer;

	@JsonProperty("pPrisonStatus")
	private String pPrisonStatus;

	@JsonProperty("pCommunityStatus")
	private String pCommunityStatus;

	@JsonProperty("pOffenderBookId")
	private BigDecimal pOffenderBookId;

	@JsonProperty("pAddress")
	private String pAddress;

	/**
	 * Creates new ReferenceCodes class Object
	 */
	public TagSearchPopulateOffDetails() {
		// TagSearchPopulateOffDetails
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPRootOffenderId() {
		return pRootOffenderId;
	}

	/**
	 *
	 * @param pRootOffenderId
	 */
	public void setPRootOffenderId(final BigDecimal pRootOffenderId) {
		this.pRootOffenderId = pRootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getPPrisonLocation() {
		return pPrisonLocation;
	}

	/**
	 *
	 * @param pPrisonLocation
	 */
	public void setPPrisonLocation(final String pPrisonLocation) {
		this.pPrisonLocation = pPrisonLocation;
	}

	/**
	 *
	 * @return
	 */
	public String getPCommunityOfficer() {
		return pCommunityOfficer;
	}

	/**
	 *
	 * @param pCommunityOfficer
	 */
	public void setPCommunityOfficer(final String pCommunityOfficer) {
		this.pCommunityOfficer = pCommunityOfficer;
	}

	/**
	 *
	 * @return
	 */
	public String getPPrisonStatus() {
		return pPrisonStatus;
	}

	/**
	 *
	 * @param pPrisonStatus
	 */
	public void setPPrisonStatus(final String pPrisonStatus) {
		this.pPrisonStatus = pPrisonStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getPCommunityStatus() {
		return pCommunityStatus;
	}

	/**
	 *
	 * @param pCommunityStatus
	 */
	public void setPCommunityStatus(final String pCommunityStatus) {
		this.pCommunityStatus = pCommunityStatus;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPOffenderBookId() {
		return pOffenderBookId;
	}

	/**
	 *
	 * @param pOffenderBookId
	 */
	public void setPOffenderBookId(final BigDecimal pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public String getPAddress() {
		return pAddress;
	}

	/**
	 *
	 * @param pAddress
	 */
	public void setPAddress(final String pAddress) {
		this.pAddress = pAddress;
	}

}