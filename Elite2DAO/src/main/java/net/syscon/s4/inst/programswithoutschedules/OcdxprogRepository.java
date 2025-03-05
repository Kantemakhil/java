package net.syscon.s4.inst.programswithoutschedules;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;

/**
 * Interface OcdxprogRepository
 */
public interface OcdxprogRepository {
	List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	Integer offPrgObligationsDeleteOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer offProgramProfilesInsertOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<CourseActivities> callOciscata(CourseActivities paramBean);

	List<Dual> offPrgObligationsWhenNewRecordInstance(Dual paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<ProgramServices> rgProgramRecordGroup();

	List<ReferenceCodes> rgEndReasonRecordGroup();

	List<ReferenceCodes> rgAvailabilityCodeRecordGroup();

	Integer offPrgObligationsUpdateOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer offPrgObligationsInsertOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer offProgramProfilesUpdateOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations objOffenderPrgObligations);

	List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);

	String offProgramProfilesPostQuery(VCourseActivities paramBean);

	List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(OffenderPrgObligations paramBean);

	List<OffenderProgramProfiles> offProgramProfilesWhenValidateRecord(OffenderProgramProfiles paramBean);

	String currentCaseloadType(String caseloadId);

	String obligationStatus(String status);

	ProgramServices offPrgObligationsProgramId(OffenderPrgObligations offPrgObligations);

	Long offenderPrgObligationId();

	Long offenderProramCode(String description);

	String obligationStatusCode(String status);

	Long ocdxprogOffPrgrefId();

	CourseActivities offPrgPrflesProjectDescription(Long crsActyId);

	Integer offProgramPrflesUpdatePrgStatus(Long offenderPrgObligationId, Long offenderBookId);

	String offProgramPrflesSameValidation(OffenderProgramProfiles offProgramProfiles);

	Integer offProgramProfilePreInsert(OffenderProgramProfiles offenderPropertyItemObj);

	Integer checkDeleteOffenderPrgObligations(Long offenderPrgObligationId);

	int checkPrivilegeExists();

	void updateOffObligationStatus(Long offenderPrgObligationId, String modifyUserId);
	
	OffenderPrgObligations getOffenderPrgObligationsOldRecord(Long offenderPrgObligationId);
	
	List<VOffenderSentCondActs> rgOffenderSentencesRecordGroup(Integer offenderBookId);

	String getCourtData(String agyLocId);

	List<SentenceCalcTypes> getSentenceData();
	
	Integer checkNonAssociationGroupConflict(BigDecimal OffenderBookId1, BigDecimal OffenderBookId2);
}
