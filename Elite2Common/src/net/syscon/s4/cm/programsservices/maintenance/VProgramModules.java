package net.syscon.s4.cm.programsservices.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VProgramModules  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty( "programModuleId") 
	  private Integer programModuleId;
	
	@JsonProperty( "listSeq")
	private Integer listSeq;
	
	@JsonProperty( "description") 
	private String description;
	
	@JsonProperty( "programPhaseId") 
	private Integer programPhaseId;
	
	@JsonProperty( "noOfSessions")
	private Integer noOfSessions;
	
	@JsonProperty( "sessionLength") 
	private Integer sessionLength;
	
	@JsonProperty( "startFlag") 
	private String startFlag;
	
	@JsonProperty( "activeFlag") 
	private String activeFlag;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("seqOne")
	private BigDecimal seqOne;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public Integer getProgramModuleId() {
		return programModuleId;
	}

	public void setProgramModuleId(Integer programModuleId) {
		this.programModuleId = programModuleId;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProgramPhaseId() {
		return programPhaseId;
	}

	public void setProgramPhaseId(Integer programPhaseId) {
		this.programPhaseId = programPhaseId;
	}

	public Integer getNoOfSessions() {
		return noOfSessions;
	}

	public void setNoOfSessions(Integer noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	public Integer getSessionLength() {
		return sessionLength;
	}

	public void setSessionLength(Integer sessionLength) {
		this.sessionLength = sessionLength;
	}

	public String getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	

	public final BigDecimal getSeqOne() {
		return seqOne;
	}


	public final void setSeqOne(BigDecimal seqOne) {
		this.seqOne = seqOne;
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
 
	
	
}
