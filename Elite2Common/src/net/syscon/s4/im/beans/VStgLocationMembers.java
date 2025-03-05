package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgLocationMembers  extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("stgId")
	private Long stgId;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("listseq")
	private BigDecimal listseq;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("status")
	private String status;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getStgId() {
		return stgId;
	}

	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getListseq() {
		return listseq;
	}

	public void setListseq(final BigDecimal listseq) {
		this.listseq = listseq;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

}