package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OmuvrestRepository
 */
public interface OmuvrestRepository {
	Integer visrRestInsertVisitorRestrictions(List<VisitorRestrictions> lstVisitorRestrictions);

	List<Persons> perExecuteQuery(Persons objPersons);

	Integer visrRestUpdateVisitorRestrictions(List<VisitorRestrictions> lstVisitorRestrictions);

	List<VisitorRestrictions> visrRestExecuteQuery(VisitorRestrictions objVisitorRestrictions);

	List<ReferenceCodes> rgVisrRestVisitRestrictiRecordGroup();

	Integer visitorRestrictionsPreInsert();

	Integer enteredStaffIdPreInsert(String userId);

	String desriptionPostInsert(StaffMembers objVisitorRestrictions);

}
