package net.syscon.s4.inst.legals.au;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderCipReasons {
	@JsonProperty("offenderBookId")
	public Integer offenderBookId;
	@JsonProperty("internalStatusSeq")
	public Integer internalStatusSeq;
	@JsonProperty("reasonSeq")
	public Integer reasonSeq;
	@JsonProperty("intStsReasonCode")
	public String intStsReasonCode;
	@JsonProperty("intStsRsnCodeDom")
	public String intStsRsnCodeDom;

	public OffenderCipReasons() {
		// OffenderCipReasons
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the internalStatusSeq
	 */
	public Integer getInternalStatusSeq() {
		return internalStatusSeq;
	}

	/**
	 * @param internalStatusSeq
	 *            the internalStatusSeq to set
	 */
	public void setInternalStatusSeq(final Integer internalStatusSeq) {
		this.internalStatusSeq = internalStatusSeq;
	}

	/**
	 * @return the reasonSeq
	 */
	public Integer getReasonSeq() {
		return reasonSeq;
	}

	/**
	 * @param reasonSeq
	 *            the reasonSeq to set
	 */
	public void setReasonSeq(final Integer reasonSeq) {
		this.reasonSeq = reasonSeq;
	}

	/**
	 * @return the intStsReasonCode
	 */
	public String getIntStsReasonCode() {
		return intStsReasonCode;
	}

	/**
	 * @param intStsReasonCode
	 *            the intStsReasonCode to set
	 */
	public void setIntStsReasonCode(final String intStsReasonCode) {
		this.intStsReasonCode = intStsReasonCode;
	}

	/**
	 * @return the intStsRsnCodeDom
	 */
	public String getIntStsRsnCodeDom() {
		return intStsRsnCodeDom;
	}

	/**
	 * @param intStsRsnCodeDom
	 *            the intStsRsnCodeDom to set
	 */
	public void setIntStsRsnCodeDom(final String intStsRsnCodeDom) {
		this.intStsRsnCodeDom = intStsRsnCodeDom;
	}
}
