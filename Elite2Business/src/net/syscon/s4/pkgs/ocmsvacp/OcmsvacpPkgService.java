package net.syscon.s4.pkgs.ocmsvacp;

import java.math.BigDecimal;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.triggers.VCourseModules;

public interface OcmsvacpPkgService {

	String getAccProgram(final BigDecimal programId);

	VAddresses getDefaultaddress(final CourseActivities list);

	Boolean checkCodeExists(final Long providerPartyId, final String providerPartyCode, final String pCode,
			final String programCategory);

	Integer createCourseModules(VCourseModules vCourseModules);

}
