package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SysDual extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("dummy")
	private String dummy;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	/**
	 * Creates new SysDual class Object
	 */
	public SysDual() {
		// SysDual
	}

	/**
	 * @return the dummy
	 */
	public String getDummy() {
		return dummy;
	}

	/**
	 * @param dummy the dummy to set
	 */
	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}