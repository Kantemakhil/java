package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OmsModuleParameters extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("commentText")
    private String commentText;
    @JsonProperty("createDatetime")
    private Date createDatetime;
    @JsonProperty("createUserId")
    private String createUserId;
    @JsonProperty("modifyDatetime")
    private Date modifyDatetime;
    @JsonProperty("modifyUserId")
    private String modifyUserId;
    @JsonProperty("multivalueFlag")
    private String multivalueFlag;
    @JsonProperty("optionalFlag")
    private String optionalFlag;
    @JsonProperty("parameterCode")
    private String parameterCode;
    @JsonProperty("parameterDomain")
    private String parameterDomain;
    @JsonProperty("parameterLovGroup")
    private String parameterLovGroup;
    @JsonProperty("parameterLovSelect")
    private String parameterLovSelect;
    @JsonProperty("parameterLovTitle")
    private String parameterLovTitle;
    @JsonProperty("parameterName")
    private String parameterName;
    @JsonProperty("parameterType")
    private String parameterType;
    @JsonProperty("sealFlag")
    private String sealFlag;
    @JsonProperty("moduleName")
    private String moduleName;
    @JsonProperty("parameterSeq")
    private Long parameterSeq;
    @JsonProperty("reportApplnCode")
    private String reportApplnCode;
    @JsonProperty("parentLov")
    private String parentLov;

    /**
     * @return the reportApplnCode
     */
    public String getReportApplnCode() {
        return reportApplnCode;
    }

    /**
     * @param reportApplnCode
     *                        the reportApplnCode to set
     */
    public void setReportApplnCode(final String reportApplnCode) {
        this.reportApplnCode = reportApplnCode;
    }

    public OmsModuleParameters() {
        // OmsModuleParameters
    }

    /**
     * @return the commentText
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * @param commentText
     *                    the commentText to set
     */
    public void setCommentText(final String commentText) {
        this.commentText = commentText;
    }

    /**
     * @return the createDatetime
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * @param createDatetime
     *                       the createDatetime to set
     */
    public void setCreateDatetime(final Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * @return the createUserId
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     *                     the createUserId to set
     */
    public void setCreateUserId(final String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return the modifyDatetime
     */
    public Date getModifyDatetime() {
        return modifyDatetime;
    }

    /**
     * @param modifyDatetime
     *                       the modifyDatetime to set
     */
    public void setModifyDatetime(final Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    /**
     * @return the modifyUserId
     */
    public String getModifyUserId() {
        return modifyUserId;
    }

    /**
     * @param modifyUserId
     *                     the modifyUserId to set
     */
    public void setModifyUserId(final String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * @return the multivalueFlag
     */
    public String getMultivalueFlag() {
        return multivalueFlag;
    }

    /**
     * @param multivalueFlag
     *                       the multivalueFlag to set
     */
    public void setMultivalueFlag(final String multivalueFlag) {
        this.multivalueFlag = multivalueFlag;
    }

    /**
     * @return the optionalFlag
     */
    public String getOptionalFlag() {
        return optionalFlag;
    }

    /**
     * @param optionalFlag
     *                     the optionalFlag to set
     */
    public void setOptionalFlag(final String optionalFlag) {
        this.optionalFlag = optionalFlag;
    }

    /**
     * @return the parameterCode
     */
    public String getParameterCode() {
        return parameterCode;
    }

    /**
     * @param parameterCode
     *                      the parameterCode to set
     */
    public void setParameterCode(final String parameterCode) {
        this.parameterCode = parameterCode;
    }

    /**
     * @return the parameterDomain
     */
    public String getParameterDomain() {
        return parameterDomain;
    }

    /**
     * @param parameterDomain
     *                        the parameterDomain to set
     */
    public void setParameterDomain(final String parameterDomain) {
        this.parameterDomain = parameterDomain;
    }

    /**
     * @return the parameterLovGroup
     */
    public String getParameterLovGroup() {
        return parameterLovGroup;
    }

    /**
     * @param parameterLovGroup
     *                          the parameterLovGroup to set
     */
    public void setParameterLovGroup(final String parameterLovGroup) {
        this.parameterLovGroup = parameterLovGroup;
    }

    /**
     * @return the parameterLovSelect
     */
    public String getParameterLovSelect() {
        return parameterLovSelect;
    }

    /**
     * @param parameterLovSelect
     *                           the parameterLovSelect to set
     */
    public void setParameterLovSelect(final String parameterLovSelect) {
        this.parameterLovSelect = parameterLovSelect;
    }

    /**
     * @return the parameterLovTitle
     */
    public String getParameterLovTitle() {
        return parameterLovTitle;
    }

    /**
     * @param parameterLovTitle
     *                          the parameterLovTitle to set
     */
    public void setParameterLovTitle(final String parameterLovTitle) {
        this.parameterLovTitle = parameterLovTitle;
    }

    /**
     * @return the parameterName
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * @param parameterName
     *                      the parameterName to set
     */
    public void setParameterName(final String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * @return the parameterType
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * @param parameterType
     *                      the parameterType to set
     */
    public void setParameterType(final String parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * @return the sealFlag
     */
    public String getSealFlag() {
        return sealFlag;
    }

    /**
     * @param sealFlag
     *                 the sealFlag to set
     */
    public void setSealFlag(final String sealFlag) {
        this.sealFlag = sealFlag;
    }

    /**
     * @return the moduleName
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName
     *                   the moduleName to set
     */
    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return the parameterSeq
     */
    public Long getParameterSeq() {
        return parameterSeq;
    }

    /**
     * @param parameterSeq
     *                     the parameterSeq to set
     */
    public void setParameterSeq(final Long parameterSeq) {
        this.parameterSeq = parameterSeq;
    }

    /**
     * @return the parentLov.
     */
    public String getParentLov() {
        return parentLov;
    }

    /**
     * @param parentLov
     *                  the parentLov to set
     */
    public void setParentLov(String parentLov) {
        this.parentLov = parentLov;
    }

}
