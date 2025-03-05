package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCaseIdentifier extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderCas")
	private OffenderCas offenderCas;

	@JsonProperty("identifierNo")
	private String identifierNo;

	@JsonProperty("identifierType")
	private String identifierType;

	@JsonProperty("caseId")
	private long caseId;

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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCas getOffenderCas() {
		return offenderCas;
	}

	/**
	 *
	 * @param offenderCas
	 */
	public void setOffenderCas(OffenderCas offenderCas) {
		this.offenderCas = offenderCas;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifierNo() {
		return identifierNo;
	}

	/**
	 *
	 * @param identifierNo
	 */
	public void setIdentifierNo(String identifierNo) {
		this.identifierNo = identifierNo;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifierType() {
		return identifierType;
	}

	/**
	 *
	 * @param identifierType
	 */
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 *
	 * @return
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 *
	 * @param caseId
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

}