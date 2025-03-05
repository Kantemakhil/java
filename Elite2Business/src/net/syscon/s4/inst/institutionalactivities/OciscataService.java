package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VCourseActivities;

/**
 * Interface OciscataService
 */
public interface OciscataService {
	List<ReferenceCodes> rgPsProvTypeRecordGroup();

	List<ReferenceCodes> rgServicesRecordGroup(String category);

	List<ReferenceCodes> rgPsNeedsRecordGroup();

	List<VCourseActivities> vCrsActExecuteQuery(VCourseActivities obj);

	List<ReferenceCodes> rgAcpProviderInstRecordGroup(String caseloadId);

	List<ReferenceCodes> rgRegionRecordGroup();

	List<Teams> rgTeamAgyLocsRecordGroup();

	List<ReferenceCodes> rgPsAgeRangeRecordGroup();

	List<ReferenceCodes> rgTeamUnpaidWkRecordGroup(String userName);

	List<Areas> vCrsActPreQuery(Areas paramBean);

	List<ReferenceCodes> rgEthnicityRecordGroup();

	List<ReferenceCodes> rgTeamAcpRecordGroup(String userName);

	List<OffenderBookings> ociscataWhenNewFormInstance(OffenderBookings paramBean);

	List<ReferenceCodes> rgPsOffGrpsRecordGroup();

	List<ReferenceCodes> rgAreasRecordGroup(String environment, String region);

	List<ReferenceCodes> rgCorpLocsRecordGroup(String category);

	List<ReferenceCodes> rgPsSexRecordGroup();

	List<ReferenceCodes> rgAgyLocClRecordGroup();

	List<ReferenceCodes> rgCsldCodeRecordGroup();

	List<ReferenceCodes> rgPsCategoryRecordGroup();

	List<ReferenceCodes> rgPsAvailRecordGroup();

	List<ReferenceCodes> rgAgyLocsRecordGroup();

	List<ReferenceCodes> rgProviderDttoRecordGroup();

	String setupDefaults(BigDecimal listSeq);
	
	String getDefaultDomain();
	
	String getDefaultAgency(String caseloadId);
	
	String getDescCode(String strCode,  String strDesc);
	
	String getAccProgram(BigDecimal programId); 
	
	ProgramsNonAssocTmp getProgramsNonAssTmp(ProgramsNonAssocTmp objPrograms);
	
	Integer getProgramsNonAssTmpCount();
	
	ReferenceCodes getCommDefaults(String caseloadId);

	Integer vCrsActWhenNewRecordInstance(Long crystalId);
	
	List<CourseActivities> checkNonAssociationConflict(List<CourseActivities> courseActivitiesList);
	
	List<VOffenderPrgObligations> checkNonAssociationConflictWithAllocatedOffenders(CourseActivities courseActivities);

	List<CourseActivities> checkNonAssociationConflictByIndAndGang(List<CourseActivities> courseActivitiesList);
	
	CourseActivities checkNonAssociationConflictWithAllocatedOffendersByIndAndGang(CourseActivities courseActivities);

	
}
