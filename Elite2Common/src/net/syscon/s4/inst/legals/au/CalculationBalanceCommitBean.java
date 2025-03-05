package net.syscon.s4.inst.legals.au;

import net.syscon.s4.common.beans.BaseModel;

public class CalculationBalanceCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VOffBalCalsCommitBean hearings;
	private VCbSentTermsCommitBean sentences;
	private VCbCustodyPeriodCommitBean custodyPeriods;

	public VOffBalCalsCommitBean getHearings() {
		return hearings;
	}

	public void setHearings(VOffBalCalsCommitBean hearings) {
		this.hearings = hearings;
	}

	public VCbSentTermsCommitBean getSentences() {
		return sentences;
	}

	public void setSentences(VCbSentTermsCommitBean sentences) {
		this.sentences = sentences;
	}

	public VCbCustodyPeriodCommitBean getCustodyPeriods() {
		return custodyPeriods;
	}

	public void setCustodyPeriods(VCbCustodyPeriodCommitBean custodyPeriods) {
		this.custodyPeriods = custodyPeriods;
	}

}
