package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;

/**
 * Interface OiicmociRepository
 */
public interface OiicmociRepository {
	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseLoadId);

	List<LivingUnits> rgLu1RecordGroup(final String agyLocId);

	List<LivingUnits> rgLu3RecordGroup(final String agyLocId, final Integer livingUnit);

	List<VCourtEvents> offSchExecuteQuery(VCourtEvents objVCourtEvents);

	List<LivingUnits> rgLu2RecordGroup(final String agyLocId, final Integer livingUnit);

}
