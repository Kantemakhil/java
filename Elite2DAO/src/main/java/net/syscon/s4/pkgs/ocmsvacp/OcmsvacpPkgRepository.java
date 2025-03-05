package net.syscon.s4.pkgs.ocmsvacp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VProgramModules;

public interface OcmsvacpPkgRepository {

	String getAccProgram(final BigDecimal programId);

	VAddresses ocmsvacpDefaultAddrWAgy(String providerCode);

	VAddresses ocmsvacpDefaultAddrWCorp(Long providerId);

	Long ocmsvacpGetCodeUniqueCntCur(Long providerPartyId, String providerPartyCode, String pCode,
			String programCategory);

	Integer createCourseModules(VCourseModules vCourseModules);

	List<VProgramModules> getVprogramModulesCrsMod(Long programPhaseId);

}