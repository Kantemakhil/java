package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;

public interface OcicnsrcService {

	List<ReferenceCodes> getAllStaffNamesByCaseload(String caseload);

	List<ReferenceCodes> getAllFacilities(String caseload);

	List<OffenderCaseNotes> casenoteexecuteQuery(OffenderCaseNotes offenderCaseNotes);

	Integer getStaffId(String username);
	
	Boolean checkPermisionForLov(String username);

}
