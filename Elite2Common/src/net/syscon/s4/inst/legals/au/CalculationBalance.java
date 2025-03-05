package net.syscon.s4.inst.legals.au;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CalculationBalance extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VCbSentTerms> offBalSentDtl;
	private List<VCbCustodyPeriod> vCbCustodyPeriod;
	private VOffBalCals vOffBalCals;

	public List<VCbSentTerms> getOffBalSentDtl() {
		return offBalSentDtl;
	}

	public void setOffBalSentDtl(List<VCbSentTerms> offBalSentDtl) {
		this.offBalSentDtl = offBalSentDtl;
	}

	public List<VCbCustodyPeriod> getvCbCustodyPeriod() {
		return vCbCustodyPeriod;
	}

	public void setvCbCustodyPeriod(List<VCbCustodyPeriod> vCbCustodyPeriod) {
		this.vCbCustodyPeriod = vCbCustodyPeriod;
	}

	public VOffBalCals getvOffBalCals() {
		return vOffBalCals;
	}

	public void setvOffBalCals(VOffBalCals vOffBalCals) {
		this.vOffBalCals = vOffBalCals;
	}

	

}
