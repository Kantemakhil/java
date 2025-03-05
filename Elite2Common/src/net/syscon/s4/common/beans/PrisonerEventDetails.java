package net.syscon.s4.common.beans;

public class PrisonerEventDetails {
	 public String eventType;
	    public PrisonerAdmission prisonerAdmission;
	    public PrisonerInTransit prisonerInTransit;
	    public PrisonerHousing prisonerHousing;
	    public PrisonerRelease prisonerRelease;
		public String getEventType() {
			return eventType;
		}
		public void setEventType(String eventType) {
			this.eventType = eventType;
		}
		public PrisonerAdmission getPrisonerAdmission() {
			return prisonerAdmission;
		}
		public void setPrisonerAdmission(PrisonerAdmission prisonerAdmission) {
			this.prisonerAdmission = prisonerAdmission;
		}
		public PrisonerInTransit getPrisonerInTransit() {
			return prisonerInTransit;
		}
		public void setPrisonerInTransit(PrisonerInTransit prisonerInTransit) {
			this.prisonerInTransit = prisonerInTransit;
		}
		public PrisonerHousing getPrisonerHousing() {
			return prisonerHousing;
		}
		public void setPrisonerHousing(PrisonerHousing prisonerHousing) {
			this.prisonerHousing = prisonerHousing;
		}
		public PrisonerRelease getPrisonerRelease() {
			return prisonerRelease;
		}
		public void setPrisonerRelease(PrisonerRelease prisonerRelease) {
			this.prisonerRelease = prisonerRelease;
		}
}
