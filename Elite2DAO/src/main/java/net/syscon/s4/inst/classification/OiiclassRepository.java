package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;

public interface OiiclassRepository {
	List<OiiclassClassInquiry> oiiclassExecuteQuery(OiiclassClassInquiry objOiiclassClassInquiry);

	Integer livUnitUpdateLivingUnits(List<LivingUnits> lstLivingUnits);

	List<SystemProfiles> sysProfExecuteQuery(SystemProfiles objSystemProfiles);

	Integer livUnitInsertLivingUnits(List<LivingUnits> lstLivingUnits);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<Assessments> cgfkAssessmentTypeRecordGroup(final String userId);

	List<LivingUnits> cgfkHousingLevel3RecordGroup();

	List<LivingUnits> livUnitExecuteQuery(LivingUnits objLivingUnits);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String userId);

	AgencyLocations defaultAgyLocDefaultAgyLoc(AgencyLocations paramBean);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(String agyLocId, String livingUnitId);

}
