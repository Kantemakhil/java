package net.syscon.s4.pkgs.tag_service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;

public interface TagServiceService {
	Integer checkProgramCode(final String programCode);

	Integer checkProgramServiceUpdate(final Long programId);

	Long doUpdateOnCrsPhase(final Long pCrsActyId, final Long pTotal, final String userName);

	Integer doUpdateOnPhase(final BigDecimal pProgramPhaseId, final BigDecimal pTotal);

	Integer deleteCourseActivityAreas(final Long crsActyId,String modifyUserId);

	Assessments getAssessmentDetails(final Long assessmentId);

	OffenderProgramProfiles getAllocationInfo(final Long crsActyId);

	String getCourseActivityAreaDesc(final String areaCode);

	ReferenceCodes getCommDefault(final String userId);

	List<CourseActivities> getCaDates(final Long pCrsActyId);

	Date getLastSchedDate(final Long pCrsActyId);

	CourseActivities getCrsDetails(final Long crsActyId);

	List<CourseActivities> getCrsDetails(final Long pPhaseInstanceId, final Date startDate, final Date endDate,
			final Long ProgramId);

	ProgramServices getPrgSrvDetails(final Long programId);

	Integer getNextPrgSrvListSeq(final Long pParentProgId);

	Integer checkNextPrgSrvSeqUnique(final Integer pParentProgId, final BigDecimal pListSeq);

	Integer getProgramIdSeq();

	Long getCrsSessionCount(final Long pCrsActyId);

	Integer getCrsActyChecksum(final Long pCrsActyId);

	Long getNextCsRuleSeq();

	Long PreInsertProgramService(final ProgramServices bean);

	Long getNextCsSeq();

	Caseloads getWorkingCaseload(final String user);
	
	Integer deleteCourseActivityParties(Long crsActyId,String modifyUserId);
	
	Integer deleteCourseActivityProf(Long crsActyId,String modifyUserId);

	Areas cArea(String areaCode);
}