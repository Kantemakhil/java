package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.LivingUnitProfiles;

public interface LivingUnitProfilesTidService {
	Integer livingUnitProfilesTid(LivingUnitProfiles livingUnitProfiles,LivingUnitProfiles livingUnitProfilesOld,String sqlOperation);
}
