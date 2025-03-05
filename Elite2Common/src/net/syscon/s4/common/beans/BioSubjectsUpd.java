package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author vrnda4
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BioSubjectsUpd extends BaseModel implements Serializable {

	String sealFlag;
	Integer clusterHash;
	String ntemplateUpdate;
	String ntemplate;
	private BigDecimal subjectId;
	private BigDecimal scanId;
	public BigDecimal getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(BigDecimal subjectId) {
		this.subjectId = subjectId;
	}
	public BigDecimal getScanId() {
		return scanId;
	}
	public void setScanId(BigDecimal scanId) {
		this.scanId = scanId;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public Integer getClusterHash() {
		return clusterHash;
	}
	public void setClusterHash(Integer clusterHash) {
		this.clusterHash = clusterHash;
	}
	public String getNtemplateUpdate() {
		return ntemplateUpdate;
	}
	public void setNtemplateUpdate(String ntemplateUpdate) {
		this.ntemplateUpdate = ntemplateUpdate;
	}
	public String getNtemplate() {
		return ntemplate;
	}
	public void setNtemplate(String ntemplate) {
		this.ntemplate = ntemplate;
	}


}
