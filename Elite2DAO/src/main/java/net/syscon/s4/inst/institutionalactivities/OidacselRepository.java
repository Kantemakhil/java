package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

/**
 * Interface OidacselRepository
 */
public interface OidacselRepository {
	List<VPrisonActivities> scheduledActivitiesExecuteQuery(VPrisonActivities obj);

}
