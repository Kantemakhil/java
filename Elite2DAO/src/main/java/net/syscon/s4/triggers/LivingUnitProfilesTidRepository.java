package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.im.beans.LivingUnitProfiles;

public interface LivingUnitProfilesTidRepository {
	List<LivingUnitProfiles> getLivingUnitProfiles(LivingUnitProfiles livingUnitProfiles);

	Integer insert(AgyIntLocProfiles agyIntLocProfiles);

	Integer delete(AgyIntLocProfiles agyIntLocProfiles);
}
