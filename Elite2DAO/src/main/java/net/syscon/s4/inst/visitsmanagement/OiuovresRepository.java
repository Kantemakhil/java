package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestricts;

public interface OiuovresRepository {
	List<ReferenceCodes> rgOffRestrictionTypeRecordGroup();

	List<ReferenceCodes> rgStaffIdRecordGroup();

	List<Persons> perExecuteQuery(Persons objPersons);

	Integer perInsertPersons(List<Persons> lstPersons);

	Integer perUpdatePersons(List<Persons> lstPersons);

	List<OffenderPersonRestricts> offConRestExecuteQuery(OffenderPersonRestricts objOffenderPersonRestricts);

	Integer offConRestUpdateOffenderPersonRestricts(List<OffenderPersonRestricts> lstOffenderPersonRestricts);

	Integer offConRestInsertOffenderPersonRestricts(List<OffenderPersonRestricts> lstOffenderPersonRestricts);

	String getStaffName(Integer enteredStaffId);

	Integer getStaffId(final String staffName);
}
