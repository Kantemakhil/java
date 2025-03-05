package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgyIntLocAmendQuery;
import net.syscon.s4.im.beans.LivingUnits;

/**
 * Interface OumhlhisService
 */
public interface OumhlhisService {

	List<AgyIntLocAmendQuery> vAgyIntLocAmendExecuteQuery(AgyIntLocAmendQuery objSearchDao);

	List<LivingUnits> livingUnitFourRgRecordGroup(Long livingUnitId, String level3Code);

	List<LivingUnits> livingUnitOneRgRecordGroup(String agyLocId);

	List<LivingUnits> livingUnitTwoRgRecordGroup(Long livingUnitId, String level1Code);

	List<LivingUnits> livingUnitThreeRgRecordGroup(Long livingUnitId, String level2Code);

	String getAgyLocIdDescReturn(String userId);

	List<LivingUnits> getLivingunitId(String agyLocId);

}
