package net.syscon.s4.common.beans;

public class PrisonerInTransit {
	public String transferOutDateAndTime;
    public String transferToFacilityCode;
    public String transferReasonCode;
	public String getTransferOutDateAndTime() {
		return transferOutDateAndTime;
	}
	public void setTransferOutDateAndTime(String transferOutDateAndTime) {
		this.transferOutDateAndTime = transferOutDateAndTime;
	}
	public String getTransferToFacilityCode() {
		return transferToFacilityCode;
	}
	public void setTransferToFacilityCode(String transferToFacilityCode) {
		this.transferToFacilityCode = transferToFacilityCode;
	}
	public String getTransferReasonCode() {
		return transferReasonCode;
	}
	public void setTransferReasonCode(String transferReasonCode) {
		this.transferReasonCode = transferReasonCode;
	}
    
}
