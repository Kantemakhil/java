package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FacilityPlacement extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderDomain")
	private String offenderDomain;
	
	@JsonProperty("offenderCode")
	private String offenderCode;
	
	@JsonProperty("offenderTable")
	private String offenderTable;

	@JsonProperty("sourceSystem")
	private String sourceSystem;
	
	@JsonProperty("sourceSystemTblCol")
	private String sourceSystemTblCol;
	
	@JsonProperty("sourceDomain")
	private String sourceDomain;
	
	@JsonProperty("offenderTblCol1")
	private String offenderTblCol1;
	
	@JsonProperty("offenderTblCol2")
	private String offenderTblCol2;
	
	@JsonProperty("mappedFor")
	private String mappedFor;
	
	@JsonProperty("required")
	private String required;
	
	@JsonProperty("required")
	private String sourceSystemTable;
	
	public String getOffenderDomain() {
		return offenderDomain;
	}

	public void setOffenderDomain(String offenderDomain) {
		this.offenderDomain = offenderDomain;
	}

	public String getOffenderCode() {
		return offenderCode;
	}

	public void setOffenderCode(String offenderCode) {
		this.offenderCode = offenderCode;
	}

	public String getOffenderTable() {
		return offenderTable;
	}

	public void setOffenderTable(String offenderTable) {
		this.offenderTable = offenderTable;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getSourceSystemTblCol() {
		return sourceSystemTblCol;
	}

	public void setSourceSystemTblCol(String sourceSystemTblCol) {
		this.sourceSystemTblCol = sourceSystemTblCol;
	}

	public String getSourceDomain() {
		return sourceDomain;
	}

	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	public String getOffenderTblCol1() {
		return offenderTblCol1;
	}

	public void setOffenderTblCol1(String offenderTblCol1) {
		this.offenderTblCol1 = offenderTblCol1;
	}

	public String getOffenderTblCol2() {
		return offenderTblCol2;
	}

	public void setOffenderTblCol2(String offenderTblCol2) {
		this.offenderTblCol2 = offenderTblCol2;
	}

	public String getMappedFor() {
		return mappedFor;
	}

	public void setMappedFor(String mappedFor) {
		this.mappedFor = mappedFor;
	}
	public String getSourceSystemTable() {
		return sourceSystemTable;
	}

	public void setSourceSystemTable(String sourceSystemTable) {
		this.sourceSystemTable = sourceSystemTable;
	}
	
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacilityPlacement [offenderDomain=");
		builder.append(offenderDomain);
		builder.append(", offenderCode=");
		builder.append(offenderCode);
		builder.append(", offenderTable=");
		builder.append(offenderTable);
		builder.append(", sourceSystem=");
		builder.append(sourceSystem);
		builder.append(", sourceSystemTblCol=");
		builder.append(sourceSystemTblCol);
		builder.append(", sourceDomain=");
		builder.append(sourceDomain);
		builder.append(", offenderTblCol1=");
		builder.append(offenderTblCol1);
		builder.append(", offenderTblCol2=");
		builder.append(offenderTblCol2);
		builder.append(", mappedFor=");
		builder.append(mappedFor);
		builder.append(", required=");
		builder.append(required);
		builder.append(", sourceSystemTable=");
		builder.append(sourceSystemTable);
		builder.append("]");
		return builder.toString();
	}
	
}
