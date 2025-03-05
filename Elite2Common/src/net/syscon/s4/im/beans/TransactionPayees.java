package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionPayees extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("defaultPayeeFlag")
	private String defaultPayeeFlag;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;
	@JsonProperty("payeePersonId")
	private Long payeePersonId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("payeeSeq")
	private Long payeeSeq;
	@JsonProperty("corporateName")
	private String corporateName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("jnOperation")
	private String jnOperation;

	@JsonProperty("jnOracleUser")
	private String jnOracleUser;
	
	@JsonProperty("jnDatetime")
	private Date jnDatetime;
	
	@JsonProperty("jnNotes")
	private String jnNotes;
	
	@JsonProperty("jnAppln")
	private String jnAppln;
	
	@JsonProperty("jnSession")
	private BigDecimal jnSession;

	/**
	 * Creates new TransactionPayees class Object
	 */
	public TransactionPayees() {
		// TransactionPayees
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the corporateName
	 */
	public String getCorporateName() {
		return corporateName;
	}

	/**
	 * @param corporateName
	 *            the corporateName to set
	 */
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the defaultPayeeFlag
	 */
	public String getDefaultPayeeFlag() {
		return defaultPayeeFlag;
	}

	/**
	 * @param defaultPayeeFlag
	 *            the defaultPayeeFlag to set
	 */
	public void setDefaultPayeeFlag(final String defaultPayeeFlag) {
		this.defaultPayeeFlag = defaultPayeeFlag;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the payeeCorporateId
	 */
	public BigDecimal getPayeeCorporateId() {
		return payeeCorporateId;
	}

	/**
	 * @param payeeCorporateId
	 *            the payeeCorporateId to set
	 */
	public void setPayeeCorporateId(final BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	/**
	 * @return the payeePersonId
	 */
	public Long getPayeePersonId() {
		return payeePersonId;
	}

	/**
	 * @param payeePersonId
	 *            the payeePersonId to set
	 */
	public void setPayeePersonId(final Long payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the payeeSeq
	 */
	public Long getPayeeSeq() {
		return payeeSeq;
	}

	/**
	 * @param payeeSeq
	 *            the payeeSeq to set
	 */
	public void setPayeeSeq(final Long payeeSeq) {
		this.payeeSeq = payeeSeq;
	}

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}

	
}
