package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffCaseNoteRecipients;
import net.syscon.s4.im.beans.OffCaseNoteRecipientsCommitBean;

/**
 * Interface OcunotcmService
 */
public interface OcunotcmService {
	List<StaffMembers> rgStaffDtlsRecordGroup(String teamIdDesc);

	List<OffCaseNoteRecipients> offCaseNrExecuteQuery(OffCaseNoteRecipients objOffCaseNoteRecipients);

	Integer offCaseNrCommit(OffCaseNoteRecipientsCommitBean commitBean);

	Object offCaseNrPreInsert();

}
