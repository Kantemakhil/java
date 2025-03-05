package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiryCommitBean;

public interface OiiclassService {
	Integer oiiclassCommit(OiiclassClassInquiryCommitBean CommitBean);

	List<OiiclassClassInquiry> oiiclassExecuteQuery(OiiclassClassInquiry objOiiclassClassInquiry);

	Integer livUnitCommit(LivingUnits commitBean);

	AgencyLocations DefaultAgyLoc(AgencyLocations paramBean);

	List<SystemProfiles> sysProfExecuteQuery(SystemProfiles objSystemProfiles);

	List<Assessments> cgfkAssessmentTypeRecordGroup(final String userId);

	List<LivingUnits> cgfkHousingLevel3RecordGroup();

	List<LivingUnits> livUnitExecuteQuery(LivingUnits objLivingUnits);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String userId);

	Integer sysProfCommit(SystemProfiles commitBean);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(String agyLocId, String livingUnitId);

	List<ReferenceCodes> cgfkSearchTypeRecordGroup();

}
