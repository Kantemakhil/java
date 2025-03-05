package net.syscon.s4.inst.institutionalactivities;


import java.util.List;

import net.syscon.s4.inst.institutionalactivities.beans.VSchdPrisonActivities;
/**
 * Interface OidschacRepository
 */
public interface OidschacRepository {
	List<VSchdPrisonActivities> schActExecuteQuery(final VSchdPrisonActivities obj) ;

}
