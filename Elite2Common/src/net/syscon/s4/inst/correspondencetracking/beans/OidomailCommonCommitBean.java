package net.syscon.s4.inst.correspondencetracking.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OidomailCommonCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderMailLogCommitBean")
	private OffenderMailLogCommitBean offenderMailLogCommitBean;

	@JsonProperty("offenderMailRestrictionCommitBean")
	private OffMailRestrictionsCommitBean offenderMailRestrictionCommitBean;

	public OffenderMailLogCommitBean getOffenderMailLogCommitBean() {
		return offenderMailLogCommitBean;
	}

	public void setOffenderMailLogCommitBean(OffenderMailLogCommitBean offenderMailLogCommitBean) {
		this.offenderMailLogCommitBean = offenderMailLogCommitBean;
	}

	public OffMailRestrictionsCommitBean getOffenderMailRestrictionCommitBean() {
		return offenderMailRestrictionCommitBean;
	}

	public void setOffenderMailRestrictionCommitBean(OffMailRestrictionsCommitBean offenderMailRestrictionCommitBean) {
		this.offenderMailRestrictionCommitBean = offenderMailRestrictionCommitBean;
	}

}
