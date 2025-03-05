package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

/**
 * Interface OidacselService 
 */
public interface OidacselService  {
	List<VPrisonActivities> scheduledActivitiesExecuteQuery(final VPrisonActivities obj) ;


}
