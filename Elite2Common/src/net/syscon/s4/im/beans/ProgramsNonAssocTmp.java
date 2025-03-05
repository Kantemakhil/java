package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ProgramsNonAssocTmp  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("line")
	private BigDecimal line;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("programId")
	private BigDecimal programId;
	@JsonProperty("crsActyId")
	private BigDecimal crsActyId;
	@JsonProperty("warningMsg")
	private String warningMsg;
	@JsonProperty("warningPrompt")
	private String warningPrompt;
	@JsonProperty("lvOffenderId")
	private BigDecimal lvOffenderId;
	@JsonProperty("lvRootOffenderId")
	private BigDecimal lvRootOffenderId;
	@JsonProperty("coursePhaseId")
	private Long coursePhaseId;

	
	public ProgramsNonAssocTmp() {
		//ProgramsNonAssocTmp
	}

	/**
	 * @return the line
	 */
	public BigDecimal getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(final BigDecimal line) {
		this.line = line;
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
	 * @return the programId
	 */
	public BigDecimal getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(final BigDecimal programId) {
		this.programId = programId;
	}

	/**
	 * @return the crsActyId
	 */
	public BigDecimal getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId the crsActyId to set
	 */
	public void setCrsActyId(final BigDecimal crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 * @return the warningMsg
	 */
	public String getWarningMsg() {
		return warningMsg;
	}

	/**
	 * @param warningMsg the warningMsg to set
	 */
	public void setWarningMsg(final String warningMsg) {
		this.warningMsg = warningMsg;
	}

	/**
	 * @return the warningPrompt
	 */
	public String getWarningPrompt() {
		return warningPrompt;
	}

	/**
	 * @param warningPrompt the warningPrompt to set
	 */
	public void setWarningPrompt(final String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	/**
	 * @return the lvOffenderId
	 */
	public BigDecimal getLvOffenderId() {
		return lvOffenderId;
	}

	/**
	 * @param lvOffenderId the lvOffenderId to set
	 */
	public void setLvOffenderId(final BigDecimal lvOffenderId) {
		this.lvOffenderId = lvOffenderId;
	}

	/**
	 * @return the lvRootoffenderId
	 */
	public BigDecimal getLvRootOffenderId() {
		return lvRootOffenderId;
	}

	/**
	 * @param lvRootoffenderId the lvRootoffenderId to set
	 */
	public void setLvRootoffenderId(final BigDecimal lvRootOffenderId) {
		this.lvRootOffenderId = lvRootOffenderId;
	}

	/**
	 * @return the coursePhaseId
	 */
	public Long getCoursePhaseId() {
		return coursePhaseId;
	}

	/**
	 * @param coursePhaseId the coursePhaseId to set
	 */
	public void setCoursePhaseId(final Long coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}
	
	
	

}
