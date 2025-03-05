package net.syscon.s4.common.beans;

public class PrisonerRelease {
	 public String releaseDateAndTime;
	    public String releaseReasonCode;
		public String getReleaseDateAndTime() {
			return releaseDateAndTime;
		}
		public void setReleaseDateAndTime(String releaseDateAndTime) {
			this.releaseDateAndTime = releaseDateAndTime;
		}
		public String getReleaseReasonCode() {
			return releaseReasonCode;
		}
		public void setReleaseReasonCode(String releaseReasonCode) {
			this.releaseReasonCode = releaseReasonCode;
		}
}
