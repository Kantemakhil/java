package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;

public interface OcmlesetRepository {
	
	Integer updateLegalsData(List<LegalSettings> settings);
	
	List<LegalSettings> getLegalsData();
	
	List<MovementReasons> fetchMovementTypes();
	
	List<ReferenceCodes> fetchRoles();

	String getLegalSettingValue(String code);

}
