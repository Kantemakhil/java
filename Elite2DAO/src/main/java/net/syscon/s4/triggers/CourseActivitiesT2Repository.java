package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.AgencyLocations;

public interface CourseActivitiesT2Repository {
	AgencyLocations getCAl(String providerPartyCode);

	String getTeamCode(Long providerPartyId);

	Integer insertCourseActivityAreas(String lvAreaCode, Long crsActyId,String userName);
}
