package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestricts;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestrictsCommitBean;

public interface OiuovresService {
	List<ReferenceCodes> rgOffRestrictionTypeRecordGroup();

	List<ReferenceCodes> rgStaffIdRecordGroup();

	List<Persons> perExecuteQuery(Persons objPersons);

	Integer offConRestCommit(OffenderPersonRestrictsCommitBean CommitBean);

	List<OffenderPersonRestricts> offConRestExecuteQuery(OffenderPersonRestricts objOffenderPersonRestricts);

	Integer perCommit(PersonsCommitBean commitBean);

}
