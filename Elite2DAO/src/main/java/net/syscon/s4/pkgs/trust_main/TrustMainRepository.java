package net.syscon.s4.pkgs.trust_main;

import net.syscon.s4.common.beans.Caseloads;

public interface TrustMainRepository {

	String getTstHdrQryApplnCode(final String pFormName);

	Caseloads getCaseloadsDetails(final String pCaseloadId);

}