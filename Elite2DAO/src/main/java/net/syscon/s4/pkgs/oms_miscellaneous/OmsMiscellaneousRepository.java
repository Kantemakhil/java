package net.syscon.s4.pkgs.oms_miscellaneous;

import net.syscon.s4.common.beans.SystemProfiles;

public interface OmsMiscellaneousRepository {
	String getDescCode(final String areaClass);

	SystemProfiles getProfileValue(final String profileType, final String profileCode);

	String staffName(final Long staffId);

	String getDescCode(final String domain, final String refCode);

	SystemProfiles getProfileValues(final SystemProfiles bean);

}