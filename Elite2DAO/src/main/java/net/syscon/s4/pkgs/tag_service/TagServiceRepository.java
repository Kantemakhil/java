package net.syscon.s4.pkgs.tag_service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;

public interface TagServiceRepository {
	public Integer chkCode(String programCode);

	public Integer cChk(Long programId);

	Long updateCourseActivities(final Long pCrsActyId, final Long pTotal, final String userName);

	Integer updateVProgramPhases(BigDecimal pProgramPhaseId, BigDecimal pTotal);

	Integer deleteCourseActivityAreasDeleteOperation(final Long crsActyId,String modifyUserId);

	Assessments getAssessmentDetails(final Long assessmentId);

	CourseActivities getAllocationInfo(final Long crsActyId);

	Areas cArea(final String areaCode);

	TeamMembers getCommDefault(final String userId);

	CourseActivities getCrsDetails(Long pCrsActyId);

	List<CourseActivities> getCrsDetailsTwo(Long pPhaseInstanceId);

	ProgramServices getPrgSrvDetails(Long lvProgramId);

	Integer getNextPrgSrvListSeq(Long pParentProgId);

	Integer checkNextPrgSrvSeqUnique(Integer pParentProgId, BigDecimal pListSeq);

	Integer getProgramIdSeq();

	Long getCrsSessionCount(Long pCrsActyId);

	Integer getCrsActyChecksum(Long pCrsActyId);

	Long getNextCsRuleSeq();

	Long preInsertProgramService(ProgramServices bean);

	Long getNextCsSeq();

	Date getLastSchedDate(Long pCrsActyId);

	Caseloads getWorkingCaseload(final String user);

	 List<CourseActivities> getCaDates(Long pCrsActyId);

	Integer deleteCourseActivityParties(Long crsActyId,String modifyUserId);

	Integer deleteCourseActivityProf(Long crsActyId,String modifyUserId);

}