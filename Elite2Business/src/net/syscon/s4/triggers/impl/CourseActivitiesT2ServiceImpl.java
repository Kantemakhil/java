package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.triggers.CourseActivitiesT2Repository;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
@Service
public class CourseActivitiesT2ServiceImpl implements CourseActivitiesT2Service {

	@Autowired
	private CourseActivitiesT2Repository courseActivitiesT2Repository;

	private static final String AGY = "AGY";
	private static final String TEAM = "TEAM";
	@Override
	public void CourseActivitiesT2Trigger(final String providerPartyCode, final Long providerPartyId,
			final String providerPartyClass, final Long crsActyId,final String userName) {
		String lvAreaCode = null, lvRegionCode;
		final AgencyLocations obj = courseActivitiesT2Repository.getCAl(providerPartyCode);
		if (AGY.equals(providerPartyClass)) {

			lvAreaCode = obj.getAreaCode();
			lvRegionCode = obj.getNomsRegionCode();
			if (lvRegionCode != null) {
				lvAreaCode = lvRegionCode;
			}
		} else if (TEAM.equals(providerPartyClass)) {
			lvAreaCode = courseActivitiesT2Repository.getTeamCode(providerPartyId);
		}

		if (lvAreaCode != null) {
			courseActivitiesT2Repository.insertCourseActivityAreas(lvAreaCode, crsActyId,userName);
		}
	}

}
