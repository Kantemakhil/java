package net.syscon.s4.im.beans;

public class FacilityParameters {
	private String sourceSystem;
	private String sourceSystemTable;
	private String sourceSystemTblCol;
    private String required;
    private int weight;
    private String sourceDomain;

    private String offenderDomain;
    private String offenderCode;
    private String offenderTable;
    private String offenderTblCol1;
    private String offenderTblCol2;
    private String mappedFor;
    private String facilityId;

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceSystem == null) ? 0 : sourceSystem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacilityParameters other = (FacilityParameters) obj;
		if (sourceSystem == null) {
			if (other.sourceSystem != null)
				return false;
		} else if (!sourceSystem.equals(other.sourceSystem))
			return false;
		return true;
    }
    
	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getSourceSystemTable() {
		return sourceSystemTable;
	}

	public void setSourceSystemTable(String sourceSystemTable) {
		this.sourceSystemTable = sourceSystemTable;
	}

	public String getSourceSystemTblCol() {
		return sourceSystemTblCol;
	}

	public void setSourceSystemTblCol(String sourceSystemTblCol) {
		this.sourceSystemTblCol = sourceSystemTblCol;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getSourceDomain() {
		return sourceDomain;
	}

	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

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

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacilityParameters [sourceSystem=");
		builder.append(sourceSystem);
		builder.append(", sourceSystemTable=");
		builder.append(sourceSystemTable);
		builder.append(", sourceSystemTblCol=");
		builder.append(sourceSystemTblCol);
		builder.append(", required=");
		builder.append(required);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", sourceDomain=");
		builder.append(sourceDomain);
		builder.append(", offenderDomain=");
		builder.append(offenderDomain);
		builder.append(", offenderCode=");
		builder.append(offenderCode);
		builder.append(", offenderTable=");
		builder.append(offenderTable);
		builder.append(", offenderTblCol1=");
		builder.append(offenderTblCol1);
		builder.append(", offenderTblCol2=");
		builder.append(offenderTblCol2);
		builder.append(", mappedFor=");
		builder.append(mappedFor);
		builder.append(", facilityId=");
		builder.append(facilityId);
		builder.append("]");
		return builder.toString();
	}

}
