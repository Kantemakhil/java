package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;

/**
 * Interface OmuvrestService
 */
public interface OmuvrestService {
	List<Persons> perExecuteQuery(Persons objPersons);

	Integer visrRestCommit(VisitorRestrictionsCommitBean CommitBean);

	List<VisitorRestrictions> visrRestExecuteQuery(VisitorRestrictions objVisitorRestrictions);

	Integer perCommit(PersonsCommitBean commitBean);

	List<ReferenceCodes> rgVisrRestVisitRestrictiRecordGroup();

}
