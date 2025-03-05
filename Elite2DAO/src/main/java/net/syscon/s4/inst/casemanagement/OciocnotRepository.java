package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.casemanagement.beans.VOffenderCaseNotes;

/**
 * Interface OciocnotRepository
 */
public interface OciocnotRepository {
	List<ReferenceCodes> rgTypeRecordGroup();

	List<VOffenderCaseNotes> caseNoteExecuteQuery(VOffenderCaseNotes obj);

	List<ReferenceCodes> rgLocationRecordGroup(String caseLoadId,String userName);

	List<ReferenceCodes> rgStaffNameRecordGroup();

	List<ReferenceCodes> rgSubTypeRecordGroup();

	Integer toGetStaffId(String userName);

	StaffMembers toGetFirstAndLastName(Integer staffId);

	Integer checkPrevExists(String userName);

}
