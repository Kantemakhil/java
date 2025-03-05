package net.syscon.s4.inst.legals.au;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderCipCauses {
	@JsonProperty("offenderBookId")
	public Integer offenderBookId;
	@JsonProperty("internalStatusSeq")
	public Integer internalStatusSeq;
	@JsonProperty("reasonSeq")
	public Integer reasonSeq;
	@JsonProperty("causeSeq")
	public Integer causeSeq;
	@JsonProperty("cipCauseCode")
	public String cipCauseCode;
	@JsonProperty("cipCauseCodeDom")
	public String cipCauseCodeDom;

	public OffenderCipCauses() {
		// OffenderCipCauses
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
	 * @return the causeSeq
	 */
	public Integer getCauseSeq() {
		return causeSeq;
	}

	/**
	 * @param causeSeq
	 *            the causeSeq to set
	 */
	public void setCauseSeq(final Integer causeSeq) {
		this.causeSeq = causeSeq;
	}

	/**
	 * @return the cipCauseCode
	 */
	public String getCipCauseCode() {
		return cipCauseCode;
	}

	/**
	 * @param cipCauseCode
	 *            the cipCauseCode to set
	 */
	public void setCipCauseCode(final String cipCauseCode) {
		this.cipCauseCode = cipCauseCode;
	}

	/**
	 * @return the cipCauseCodeDom
	 */
	public String getCipCauseCodeDom() {
		return cipCauseCodeDom;
	}

	/**
	 * @param cipCauseCodeDom
	 *            the cipCauseCodeDom to set
	 */
	public void setCipCauseCodeDom(final String cipCauseCodeDom) {
		this.cipCauseCodeDom = cipCauseCodeDom;
	}

}
