package net.syscon.s4.inst.programswithoutschedules;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;

/**
 * Interface OiuvlcteRepository
 */
public interface OiuvlcteRepository {

	public CourseActivities getCrsDetails(final CourseActivities obj);

	public String getFacilityDet(final CourseActivities obj);
	
	public VAddresses getAdresss(final long addressId);

}
