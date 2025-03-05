package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgyIncInvStatements extends BaseModel  implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyIiStatementId")
	@NotNull
	private Integer agyIiStatementId;
	
	@JsonProperty("agyIncInvestigationId")
	@NotNull
	private Integer agyIncInvestigationId;
	
	@JsonProperty("statementType")
	@NotNull
	@Size(max=12)
	private String statementType;
	
	@JsonProperty("nameOfStatementTaker")
	@Size(max=60)
	private String nameOfStatementTaker;
	
	@JsonProperty("dateOfStatementTaken")
	@NotNull
	private Date dateOfStatementTaken;
	
	@JsonProperty("statementDetail")
	@NotNull
	@Size(max=4000)
	private String statementDetail;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	@NotNull
	@Size(max=32)
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	@Size(max=32)
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	@Size(max=1)
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	
	/**
	 * Creates new AgyIncInvStatements class Object
	 */
	public AgyIncInvStatements() {
		//AgyIncInvStatements
	}
	
	
	/**
	 * @return the agyIiStatementId
	 */
	public Integer getAgyIiStatementId() {
		return agyIiStatementId;
	}
	/**
	 * @param agyIiStatementId the agyIiStatementId to set
	 */
	public void setAgyIiStatementId(final Integer agyIiStatementId) {
		this.agyIiStatementId = agyIiStatementId;
	}
	/**
	 * @return the agyIncInvestigationId
	 */
	public Integer getAgyIncInvestigationId() {
		return agyIncInvestigationId;
	}
	/**
	 * @param agyIncInvestigationId the agyIncInvestigationId to set
	 */
	public void setAgyIncInvestigationId(final Integer agyIncInvestigationId) {
		this.agyIncInvestigationId = agyIncInvestigationId;
	}
	/**
	 * @return the statementType
	 */
	public String getStatementType() {
		return statementType;
	}
	/**
	 * @param statementType the statementType to set
	 */
	public void setStatementType(final String statementType) {
		this.statementType = statementType;
	}
	/**
	 * @return the nameOfStatementTaker
	 */
	public String getNameOfStatementTaker() {
		return nameOfStatementTaker;
	}
	/**
	 * @param nameOfStatementTaker the nameOfStatementTaker to set
	 */
	public void setNameOfStatementTaker(final String nameOfStatementTaker) {
		this.nameOfStatementTaker = nameOfStatementTaker;
	}
	/**
	 * @return the dateOfStatementTaken
	 */
	public Date getDateOfStatementTaken() {
		return dateOfStatementTaken;
	}
	/**
	 * @param dateOfStatementTaken the dateOfStatementTaken to set
	 */
	public void setDateOfStatementTaken(final Date dateOfStatementTaken) {
		this.dateOfStatementTaken = dateOfStatementTaken;
	}
	/**
	 * @return the statementDetail
	 */
	public String getStatementDetail() {
		return statementDetail;
	}
	/**
	 * @param statementDetail the statementDetail to set
	 */
	public void setStatementDetail(final String statementDetail) {
		this.statementDetail = statementDetail;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}
	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}
	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}


 
}