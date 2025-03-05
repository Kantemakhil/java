package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

/**
 * Interface OcdclogsService
 */
public interface OcdclogsService {
	List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType ,String userName);

	List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType,String userName,String caseloadType);

	List<OffenderCaseNotes> rgCasenotestaffNameRecordGroup(final String tip);

	List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	Integer validateNoteTypeSubType(OffenderCaseNotes objOffenderCaseNotes);

	public List<VOffenderAllSchedules> schExecuteQuery(final BigDecimal offenderBookId);

	List<StaffLocationRoles> rgStaffnameRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules searchObject);

	List<ReferenceCodes> rgScheduleSubTypeRecordGroup(final String eventTypeDesc);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	Integer offNotesCommit(OffenderCaseNotesCommitBean CommitBean);

	Integer offSchCommit(VOffenderAllSchedulesCommitBean CommitBean);

	List<ReferenceCodes> rgnoteSourceRecordGroup();

	List<ReferenceCodes> rgEventOutcomeRecordGroup(final String threeip);

	List<AgencyLocations> rgLocationRecordGroup();

	StaffMembers lvLoginUserStaffName(String userName) ;
	
	String getModuleName(OffenderCaseNotes offNotes);
	List<Areas> rglovOutComeRecordGroup();


	Integer getStaffIdOne(Integer caseNoteId);

	String getcaseNoteTextData(OffenderCaseNotes caseNotesObj);
	
	String checkNonAssociations(final VOffenderAllSchedulesCommitBean commitBean);
	
	List<EventMeasures> getEmailSmsFlag(VOffenderAllSchedules beanOne);
	
	String getCancelFlag(VOffenderAllSchedules beanOne);

	String checkCasenoteSubType(String caseNoteType, String caseNoteCode, String userName);
}
