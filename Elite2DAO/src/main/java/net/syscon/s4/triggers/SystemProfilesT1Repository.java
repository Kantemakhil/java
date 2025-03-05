package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SystemProfilesT1Repository {

//	public Integer insertDbPatches(SystemProfiles systemPrfles);

	public Integer insertDbPatches(List<SystemProfiles> systemProfile);
	
}
