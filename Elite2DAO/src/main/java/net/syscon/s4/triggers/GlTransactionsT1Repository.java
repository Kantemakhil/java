package net.syscon.s4.triggers;

public interface GlTransactionsT1Repository {
	String getVProfileValue();

	String getModuleName(Long vSessionid);

	Integer savetrustAudits(TrustAudits trustAudits);

}
