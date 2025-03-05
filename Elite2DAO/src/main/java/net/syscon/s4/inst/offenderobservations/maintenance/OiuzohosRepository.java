package net.syscon.s4.inst.offenderobservations.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;

public interface OiuzohosRepository {

	List<ReferenceCodes> rgUnitTypeRecordGroup();

	List<LivingUnits> rgLevel1LovData(final String unitTypeValue,  final String facility);

	List<LivingUnits> rgLevel2LovData(Integer livigUnitId);

	List<LivingUnits> rgLevel3LovData(Integer parentLivigUnitId);
	
	List<LivingUnits> getLevel4Data(Integer parentLivigUnitId);

	List<LivingUnits> housingDetailsExecuteQuery(LivingUnits searchBean);
	
	List<LivingUnits> getLivingUnit(BigDecimal livingUnit);

	Integer getZoneAssignedCount(OffObsZoneDetails searchBean);

}
