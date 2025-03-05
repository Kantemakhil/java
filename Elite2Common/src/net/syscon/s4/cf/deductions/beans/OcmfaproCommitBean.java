package net.syscon.s4.cf.deductions.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;

import java.io.Serializable;

public class OcmfaproCommitBean extends BaseModel implements Serializable {

    @JsonProperty("cslddpCommitModel")
    private CaseloadDeductionProfilesCommitBean cslddpCommitModel;
    @JsonProperty("cslddbenCommitModel")
    private CaseloadDedBeneficiariesCommitBean cslddbenCommitModel;
    @JsonProperty("csldddCommitModel")
    private CaseloadDeductionDetailsCommitBean csldddCommitModel;

    public CaseloadDeductionProfilesCommitBean getCslddpCommitModel() {
        return cslddpCommitModel;
    }

    public void setCslddpCommitModel(CaseloadDeductionProfilesCommitBean cslddpCommitModel) {
        this.cslddpCommitModel = cslddpCommitModel;
    }

    public CaseloadDedBeneficiariesCommitBean getCslddbenCommitModel() {
        return cslddbenCommitModel;
    }

    public void setCslddbenCommitModel(CaseloadDedBeneficiariesCommitBean cslddbenCommitModel) {
        this.cslddbenCommitModel = cslddbenCommitModel;
    }

    public CaseloadDeductionDetailsCommitBean getCsldddCommitModel() {
        return csldddCommitModel;
    }

    public void setCsldddCommitModel(CaseloadDeductionDetailsCommitBean csldddCommitModel) {
        this.csldddCommitModel = csldddCommitModel;
    }
}
