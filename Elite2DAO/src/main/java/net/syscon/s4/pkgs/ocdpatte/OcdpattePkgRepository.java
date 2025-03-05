package net.syscon.s4.pkgs.ocdpatte;

import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;

public interface OcdpattePkgRepository {
	List<CourseActivities> getActOutcomeFlag(final String eventType, final String outcomeCode, final Date eventDate);

	String getProviderType(final String agyLocId);

	String getPaylock(final Long eventId);
}