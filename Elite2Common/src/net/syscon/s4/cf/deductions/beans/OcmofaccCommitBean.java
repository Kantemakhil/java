package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OcmofaccCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offdedCommitBean")
	private FeeAccountProfilesCommitBean offdedCommitBean;
	@JsonProperty("cslddbenCommitBean")
    private CaseloadDedBeneficiariesCommitBean cslddbenCommitBean;
	@JsonProperty("csldddCommitBean")
    private CaseloadDeductionDetailsCommitBean csldddCommitBean;
	
	@JsonProperty("longSupvModelUpdate")
    private FeeAccountProfiles longSupvModelUpdate;
	
	
	
    public FeeAccountProfilesCommitBean getOffdedCommitBean() {
		return offdedCommitBean;
	}
	public void setOffdedCommitBean(FeeAccountProfilesCommitBean offdedCommitBean) {
		this.offdedCommitBean = offdedCommitBean;
	}
	public CaseloadDedBeneficiariesCommitBean getCslddbenCommitBean() {
		return cslddbenCommitBean;
	}
	public void setCslddbenCommitBean(CaseloadDedBeneficiariesCommitBean cslddbenCommitBean) {
		this.cslddbenCommitBean = cslddbenCommitBean;
	}
	public CaseloadDeductionDetailsCommitBean getCsldddCommitBean() {
		return csldddCommitBean;
	}
	public void setCsldddCommitBean(CaseloadDeductionDetailsCommitBean csldddCommitBean) {
		this.csldddCommitBean = csldddCommitBean;
	}
	public FeeAccountProfiles getLongSupvModelUpdate() {
		return longSupvModelUpdate;
	}
	public void setLongSupvModelUpdate(FeeAccountProfiles longSupvModelUpdate) {
		this.longSupvModelUpdate = longSupvModelUpdate;
	}
    
    
    
	

}
