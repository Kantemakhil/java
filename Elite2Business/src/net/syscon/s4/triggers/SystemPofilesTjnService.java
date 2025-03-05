package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SystemPofilesTjnService {

	Integer systemProfilesForInserting(final List<SystemProfiles> lstSystemProfiles,final String status);
}
