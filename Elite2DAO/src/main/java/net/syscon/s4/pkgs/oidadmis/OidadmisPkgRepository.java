package net.syscon.s4.pkgs.oidadmis;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;

public interface OidadmisPkgRepository {

	List<CaseloadAdmOtherProfiles> getDefaultsCur(final String caseloadId);

	Integer getLvCapacity(final Integer pLivingUnitId);
	
	public OffenderExternalMovements getAdmissionTypeCur(final Long pOffenderBookId);
	
	public String getMvmentReasonCode(final String pMvtReason);
	
	public Integer getMsgNumber(final String pCaseloadId, final Integer pOffenderAge);
	
	public String getTrstFlag(final String pCaseloadId);


}
