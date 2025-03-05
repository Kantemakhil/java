package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;

public interface OcicnsrcRepository {

	List<ReferenceCodes> getAllStaffNamesByCaseload(String caseload);

	List<ReferenceCodes> getAllFacilities(String caseload);

	List<OffenderCaseNotes> casenoteexecuteQuery(OffenderCaseNotes offenderCaseNotes);
	
	Boolean checkPermisionForLov(String username);

}
