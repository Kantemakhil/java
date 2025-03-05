package net.syscon.s4.inst.legals.au;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class LegalCasesBaseCommitBean extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("courtCasesCommitBean")
	private CourtCasesCommitBean courtCasesCommitBean;
	
	@JsonProperty("courtEventsCommitBean")
	private CourtEventsCommitBean courtEventsCommitBean;
	
	@JsonProperty("offencesCommitBean")
	private OffencesCommitBean offencesCommitBean;
	
	@JsonProperty("sentencesCommitBean")
	private SentencesCommitBean sentencesCommitBean;
	
	@JsonProperty("termsCommitBean")
	private TermsCommitBean termsCommitBean;
	
	@JsonProperty("convictedOffencesCommitBean")
	private OffencesCommitBean convictedOffencesCommitBean;
	
	@JsonProperty("ordersCommitBean")
	private SentencesCommitBean ordersCommitBean;
	
	@JsonProperty("periodsCommitBean")
	private TermsCommitBean periodsCommitBean;
	
	@JsonProperty("convictedOffencesComCommitBean")
	private OffencesCommitBean convictedOffencesComCommitBean;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

	public CourtCasesCommitBean getCourtCasesCommitBean() {
		return courtCasesCommitBean;
	}

	public void setCourtCasesCommitBean(CourtCasesCommitBean courtCasesCommitBean) {
		this.courtCasesCommitBean = courtCasesCommitBean;
	}

	public CourtEventsCommitBean getCourtEventsCommitBean() {
		return courtEventsCommitBean;
	}

	public void setCourtEventsCommitBean(CourtEventsCommitBean courtEventsCommitBean) {
		this.courtEventsCommitBean = courtEventsCommitBean;
	}

	public OffencesCommitBean getOffencesCommitBean() {
		return offencesCommitBean;
	}

	public void setOffencesCommitBean(OffencesCommitBean offencesCommitBean) {
		this.offencesCommitBean = offencesCommitBean;
	}

	public SentencesCommitBean getSentencesCommitBean() {
		return sentencesCommitBean;
	}

	public void setSentencesCommitBean(SentencesCommitBean sentencesCommitBean) {
		this.sentencesCommitBean = sentencesCommitBean;
	}

	public TermsCommitBean getTermsCommitBean() {
		return termsCommitBean;
	}

	public void setTermsCommitBean(TermsCommitBean termsCommitBean) {
		this.termsCommitBean = termsCommitBean;
	}

	public OffencesCommitBean getConvictedOffencesCommitBean() {
		return convictedOffencesCommitBean;
	}

	public void setConvictedOffencesCommitBean(OffencesCommitBean convictedOffencesCommitBean) {
		this.convictedOffencesCommitBean = convictedOffencesCommitBean;
	}

	public SentencesCommitBean getOrdersCommitBean() {
		return ordersCommitBean;
	}

	public void setOrdersCommitBean(SentencesCommitBean ordersCommitBean) {
		this.ordersCommitBean = ordersCommitBean;
	}

	public TermsCommitBean getPeriodsCommitBean() {
		return periodsCommitBean;
	}

	public void setPeriodsCommitBean(TermsCommitBean periodsCommitBean) {
		this.periodsCommitBean = periodsCommitBean;
	}

	public OffencesCommitBean getConvictedOffencesComCommitBean() {
		return convictedOffencesComCommitBean;
	}

	public void setConvictedOffencesComCommitBean(OffencesCommitBean convictedOffencesComCommitBean) {
		this.convictedOffencesComCommitBean = convictedOffencesComCommitBean;
	}
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	
}
