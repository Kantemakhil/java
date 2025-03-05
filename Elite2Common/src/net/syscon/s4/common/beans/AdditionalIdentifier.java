package net.syscon.s4.common.beans;

public class AdditionalIdentifier {
	 public String identifierTypeCode;
	    public String issuer;
	    public String identifier;
		public String getIdentifierTypeCode() {
			return identifierTypeCode;
		}
		public void setIdentifierTypeCode(String identifierTypeCode) {
			this.identifierTypeCode = identifierTypeCode;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getIdentifier() {
			return identifier;
		}
		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
}
