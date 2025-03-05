package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;

/**
 * Interface OcmsuwpjRepository
 */
public interface OcmsuwpjRepository {
	List<Corporates> rgPlacementNameRecordGroup();

	CourseActivities courseActUpdateCourseActivities(List<CourseActivities> lstCourseActivities);

	List<Teams> rgTeamRecordGroup(String user);

	CourseActivities courseActInsertCourseActivities(List<CourseActivities> lstCourseActivities);

	CourseActivities courseActDeleteCourseActivities(List<CourseActivities> lstCourseActivities);

	List<ReferenceCodes> rgBeneficiaryTypeRecordGroup();

	List<VCorporateAddresses> rgPlacementAddressesRecordGroup(final BigDecimal placementCorporateId);

	List<CourseActivities> courseActExecuteQuery(CourseActivities objCourseActivities);

	Integer getCodeUniqueCntCur(final Long pTeamId, final String pCode);

	Long getCrsActyIdCur();

	List<ProgramServices> getProgramId();

	CourseActivities getPlacementDetails(CourseActivities courseActivities);

	Integer getUpdateScheduleDate(CourseActivities offenderId);

	BigDecimal getAllocExists(CourseActivities bean);

	List<CourseActivities> placementExecuteQuery(CourseActivities searchBean);

	List<VProgramProviders> rgProviderRecordGroup(String caseLoadId, String caseLoadType, String providerType, String user);

	List<IntLocUsageLocations> rgIntLocRecordGroup(String agyLocId);

	List<ProgramServices> rgProgramTypeRecordGroup();

	List<VProgramProviders> rgProviderRecordGroupTeam(String caseLoadId, String caseLoadType, String providerType,
			String user);
	
	List<Corporates> getPlacementLocationByCaseload(String caseload);
}
