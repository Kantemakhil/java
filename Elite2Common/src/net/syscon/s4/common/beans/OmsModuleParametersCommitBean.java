package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OmsModuleParametersCommitBean extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("insertList")
    private List<OmsModuleParameter> insertList;

    @JsonProperty("deleteList")
    private List<OmsModuleParameter> deleteList;

    @JsonProperty("updateList")
    private List<OmsModuleParameter> updateList;

    /**
     * Creates new OffenderAlertsCommitBean class Object
     */
    public OmsModuleParametersCommitBean() {
        // OffenderAlertsCommitBean
    }

    public List<OmsModuleParameter> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<OmsModuleParameter> insertList) {
        this.insertList = insertList;
    }

    public List<OmsModuleParameter> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<OmsModuleParameter> deleteList) {
        this.deleteList = deleteList;
    }

    public List<OmsModuleParameter> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<OmsModuleParameter> updateList) {
        this.updateList = updateList;
    }

}
