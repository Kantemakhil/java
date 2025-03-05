package net.syscon.s4.inst.casemanagement;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffCaseNoteRecipients;

/**
 * Interface OcunotcmRepository
 */
public interface OcunotcmRepository {
	List<StaffMembers> rgStaffDtlsRecordGroup(String teamIdDesc);

	List<OffCaseNoteRecipients> offCaseNrExecuteQuery(OffCaseNoteRecipients objOffCaseNoteRecipients);

	Integer offCaseNrUpdateOffCaseNoteRecipients(List<OffCaseNoteRecipients> lstOffCaseNoteRecipients);

	Integer offCaseNrInsertOffCaseNoteRecipients(List<OffCaseNoteRecipients> lstOffCaseNoteRecipients);

	Object offCaseNrPreInsert();

	Integer getcaseRecipientId();

	String offCasegetteamIdDesc(Integer teamId);

	String offStaffNameDesc(Integer staffId);

	Integer getteamId(String teamIdDesc);

}
