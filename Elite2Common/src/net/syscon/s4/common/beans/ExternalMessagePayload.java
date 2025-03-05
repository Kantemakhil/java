package net.syscon.s4.common.beans;

public class ExternalMessagePayload {
    public AstriaMessageHeader astriaMessageHeader;
    public PrisonerDetails prisonerDetails;
    public PrisonerEventDetails prisonerEventDetails;
    
	public AstriaMessageHeader getAstriaMessageHeader() {
		return astriaMessageHeader;
	}
	public void setAstriaMessageHeader(AstriaMessageHeader astriaMessageHeader) {
		this.astriaMessageHeader = astriaMessageHeader;
	}
	public PrisonerDetails getPrisonerDetails() {
		return prisonerDetails;
	}
	public void setPrisonerDetails(PrisonerDetails prisonerDetails) {
		this.prisonerDetails = prisonerDetails;
	}
	public PrisonerEventDetails getPrisonerEventDetails() {
		
		return prisonerEventDetails;
	}
	public void setPrisonerEventDetails(PrisonerEventDetails prisonerEventDetails) {
		
		this.prisonerEventDetails = prisonerEventDetails;
	}
    
    
}


 




 












