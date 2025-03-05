package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.offenderissuestracking.beans.VStaffLocationRoles;

/**
 * Interface OcuwarniService
 */
public interface OcuwarniService {

	List<VStaffLocationRoles> rgStaffNameRecordGroup(Long offenderBookId, String caseLoadId, String agyLocId);

	List<OffenderCaseNotes> offCaseNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	List<ReferenceCodes> rgConSubTypeRecordGroup();

}
