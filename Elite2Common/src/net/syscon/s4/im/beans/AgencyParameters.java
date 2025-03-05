package net.syscon.s4.im.beans;

import java.util.List;

public class AgencyParameters {
	
    private String pAgyLocId;
    private List<FacilityParameters> paramList;
	private int rank;
	private int noOfAvailable;
	private String description;
    
    public String getpAgyLocId() {
		return pAgyLocId;
	}
	public void setpAgyLocId(String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}
	public int getNoOfAvailable() {
		return noOfAvailable;
	}
	public void setNoOfAvailable(int noOfAvailable) {
		this.noOfAvailable = noOfAvailable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int compareTo(String anotherString) {
		return pAgyLocId.compareTo(anotherString);
	}
	public String getAgency() {
		return pAgyLocId;
	}
	public void setAgency(String agency) {
		this.pAgyLocId = agency;
	}
	public List<FacilityParameters> getParamList() {
		return paramList;
	}
	public void setParamList(List<FacilityParameters> paramList) {
		this.paramList = paramList;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		//long count = paramList.stream().filter(l -> l.getRequired().equals("Y")).count();
		StringBuilder builder = new StringBuilder();
		builder.append("AgencyParameters [agency=");
		builder.append(pAgyLocId);
		builder.append(", paramList=");
		builder.append(paramList);
		builder.append(", rank=");
		builder.append(rank);
		builder.append("]");
		return builder.toString();
	}
    
	
    
}
