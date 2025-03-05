package net.syscon.s4.iwp.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.legals.beans.OffenderProceedingsCommitBean;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSentsCommitBean;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OcdenforCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("offprcsCommitBean")
	private OffenderProceedingsCommitBean offprcsCommitBean;
	@JsonProperty("voffsntCommitBean")
	private VOffenderProceedingSentsCommitBean voffsntCommitBean;

	public OffenderProceedingsCommitBean getOffprcsCommitBean() {
		return offprcsCommitBean;
	}

	public void setOffprcsCommitBean(OffenderProceedingsCommitBean offprcsCommitBean) {
		this.offprcsCommitBean = offprcsCommitBean;
	}

	public VOffenderProceedingSentsCommitBean getVoffsntCommitBean() {
		return voffsntCommitBean;
	}

	public void setVoffsntCommitBean(VOffenderProceedingSentsCommitBean voffsntCommitBean) {
		this.voffsntCommitBean = voffsntCommitBean;
	}
}
