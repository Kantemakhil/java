package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the CORPORATE_TYPES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class DebsObligationsBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fType")
	private String fType;
	@JsonProperty("fPayable")
	private String fPayable;
	@JsonProperty("fInfoNumber")
	private String fInfoNumber;
	@JsonProperty("fAmount")
	private BigDecimal fAmount;
	@JsonProperty("fAmtPaid")
	private BigDecimal fAmtPaid;
	@JsonProperty("fWriteOff")
	private BigDecimal fWriteOff;

	public DebsObligationsBean() {
		// DebsObligationsBean
	}

	/**
	 * @return the fType
	 */
	public String getfType() {
		return fType;
	}

	/**
	 * @param fType
	 *            the fType to set
	 */
	public void setfType(final String fType) {
		this.fType = fType;
	}

	/**
	 * @return the fPayable
	 */
	public String getfPayable() {
		return fPayable;
	}

	/**
	 * @param fPayable
	 *            the fPayable to set
	 */
	public void setfPayable(final String fPayable) {
		this.fPayable = fPayable;
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
	 * @return the fAmount
	 */
	public BigDecimal getfAmount() {
		return fAmount;
	}

	/**
	 * @param fAmount
	 *            the fAmount to set
	 */
	public void setfAmount(final BigDecimal fAmount) {
		this.fAmount = fAmount;
	}

	/**
	 * @return the fAmtPaid
	 */
	public BigDecimal getfAmtPaid() {
		return fAmtPaid;
	}

	/**
	 * @param fAmtPaid
	 *            the fAmtPaid to set
	 */
	public void setfAmtPaid(final BigDecimal fAmtPaid) {
		this.fAmtPaid = fAmtPaid;
	}

	/**
	 * @return the fWriteOff
	 */
	public BigDecimal getfWriteOff() {
		return fWriteOff;
	}

	/**
	 * @param fWriteOff
	 *            the fWriteOff to set
	 */
	public void setfWriteOff(final BigDecimal fWriteOff) {
		this.fWriteOff = fWriteOff;
	}

}