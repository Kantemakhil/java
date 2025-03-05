package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.OmsModules;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmsModuleParameter extends BaseModel implements Serializable {

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

	@JsonProperty("omsModule")
	private OmsModules omsModule;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("parameterSeq")
	private long parameterSeq;
	
    @JsonProperty("parentLov")
    private String parentLov;  

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getMultivalueFlag() {
		return multivalueFlag;
	}

	/**
	 *
	 * @param multivalueFlag
	 */
	public void setMultivalueFlag(String multivalueFlag) {
		this.multivalueFlag = multivalueFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getOptionalFlag() {
		return optionalFlag;
	}

	/**
	 *
	 * @param optionalFlag
	 */
	public void setOptionalFlag(String optionalFlag) {
		this.optionalFlag = optionalFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterCode() {
		return parameterCode;
	}

	/**
	 *
	 * @param parameterCode
	 */
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterDomain() {
		return parameterDomain;
	}

	/**
	 *
	 * @param parameterDomain
	 */
	public void setParameterDomain(String parameterDomain) {
		this.parameterDomain = parameterDomain;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterLovGroup() {
		return parameterLovGroup;
	}

	/**
	 *
	 * @param parameterLovGroup
	 */
	public void setParameterLovGroup(String parameterLovGroup) {
		this.parameterLovGroup = parameterLovGroup;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterLovSelect() {
		return parameterLovSelect;
	}

	/**
	 *
	 * @param parameterLovSelect
	 */
	public void setParameterLovSelect(String parameterLovSelect) {
		this.parameterLovSelect = parameterLovSelect;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterLovTitle() {
		return parameterLovTitle;
	}

	/**
	 *
	 * @param parameterLovTitle
	 */
	public void setParameterLovTitle(String parameterLovTitle) {
		this.parameterLovTitle = parameterLovTitle;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 *
	 * @param parameterName
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 *
	 * @return
	 */
	public String getParameterType() {
		return parameterType;
	}

	/**
	 *
	 * @param parameterType
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public OmsModules getOmsModule() {
		return omsModule;
	}

	/**
	 *
	 * @param omsModule
	 */
	public void setOmsModule(OmsModules omsModule) {
		this.omsModule = omsModule;
	}

	/**
	 *
	 * @return
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 *
	 * @param moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 *
	 * @return
	 */
	public long getParameterSeq() {
		return parameterSeq;
	}

	/**
	 *
	 * @param parameterSeq
	 */
	public void setParameterSeq(long parameterSeq) {
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
     *            the parentLov to set  
     */
    public void setParentLov(String parentLov) {
        this.parentLov = parentLov;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, parameterName, parameterSeq);
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OmsModuleParameter other = (OmsModuleParameter) obj;
        return Objects.equals(moduleName, other.moduleName) && Objects.equals(parameterName, other.parameterName)
                && parameterSeq == other.parameterSeq;
    }
	
    

}