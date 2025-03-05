package net.syscon.s4.pkgs.oms_miscellaneous;

import java.util.Map;

import net.syscon.s4.common.beans.SystemProfiles;

public interface OmsMiscellaneousService {
	String getDescCode(final String areaClass);

	Map<String, Object> getProfileValues(final String profileType, final String profileCode);

	String staffName(final Long staffId);

	String getDescCode(final String domain, final String refCode);

	String getProfileValue(final SystemProfiles symProfile);
	
}