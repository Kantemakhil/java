package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;

/**
 * Interface OumhlhisRepository
 */
public interface OumhlhisRepository {

	List<LivingUnits> livingUnitFourRgRecordGroup(Long livingUnitId, String level3Code);

	List<LivingUnits> livingUnitOneRgRecordGroup( String agyLocId);

	List<LivingUnits> livingUnitTwoRgRecordGroup(Long livingUnitId, String level1Code);

	List<LivingUnits> livingUnitThreeRgRecordGroup(Long livingUnitId, String level2Code);

	List<LivingUnits> setLivingUnitLevels(LivingUnits paramBean);

	String getAgyLocIdDesc(String userId);

	String getAgyLocIdDescReturn(String userId);

	List<LivingUnits> getLivingunitId(String agyLocId);

}
