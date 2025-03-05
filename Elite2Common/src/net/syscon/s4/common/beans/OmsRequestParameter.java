package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmsRequestParameter extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("parameterText")
	private String parameterText;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("requestId")
	private long requestId;

	@JsonProperty("paramSeq")
	private long paramSeq;

	@JsonProperty("omsRequests")
	private OmsRequests omsRequests;

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
	public String getParameterText() {
		return parameterText;
	}

	/**
	 *
	 * @param parameterText
	 */
	public void setParameterText(String parameterText) {
		this.parameterText = parameterText;
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
	public long getRequestId() {
		return requestId;
	}

	/**
	 *
	 * @param requestId
	 */
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	/**
	 *
	 * @return
	 */
	public long getParamSeq() {
		return paramSeq;
	}

	/**
	 *
	 * @param paramSeq
	 */
	public void setParamSeq(long paramSeq) {
		this.paramSeq = paramSeq;
	}

	/**
	 *
	 * @return
	 */
	public OmsRequests getOmsRequest() {
		return omsRequests;
	}

	/**
	 *
	 * @param omsRequest
	 */
	public void setOmsRequest(OmsRequests omsRequest) {
		this.omsRequests = omsRequest;
	}

}