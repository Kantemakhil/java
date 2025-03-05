package net.syscon.s4.im.beans;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

public class GenerateEmailPreview extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pWorkflowType;
	private Integer pWorkId;
	private Integer pOffenderBookId;
	private String pXml;
	public String getpWorkflowType() {
		return pWorkflowType;
	}
	public void setpWorkflowType(String pWorkflowType) {
		this.pWorkflowType = pWorkflowType;
	}
	public Integer getpWorkId() {
		return pWorkId;
	}
	public void setpWorkId(Integer pWorkId) {
		this.pWorkId = pWorkId;
	}
	public Integer getpOffenderBookId() {
		return pOffenderBookId;
	}
	public void setpOffenderBookId(Integer pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}
	public String getpXml() {
		return pXml;
	}
	public void setpXml(String pXml) {
		this.pXml = pXml;
	}
	@Override
	public String toString() {
		return "GenerateEmailPreview [pWorkflowType=" + pWorkflowType + ", pWorkId=" + pWorkId + ", pOffenderBookId="
				+ pOffenderBookId + ", pXml=" + pXml + "]";
	}

}
