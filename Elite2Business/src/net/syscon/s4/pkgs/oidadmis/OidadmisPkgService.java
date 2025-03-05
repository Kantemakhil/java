package net.syscon.s4.pkgs.oidadmis;

import java.util.Map;

import net.syscon.s4.im.beans.VHeaderBlock2;


public interface OidadmisPkgService {

	Map<String, Object> getDefaults(final String caseloadId);

	String getAdmissionType(final VHeaderBlock2 searchBean);

	Integer checkYoungOff(final String pCaseloadId, final Integer pOffenderAge);

	String getTrustFlag(final String pCaseloadId);
}
