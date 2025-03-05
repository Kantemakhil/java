package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.booking.beans.ContactPersonTypes;

/**
 * Interface OimctactRepository
 */
public interface OimctactRepository {
	Integer contactPersonTypesInsertContactPersonTypes(List<ContactPersonTypes> isContPers);

	List<ContactPersonTypes> contactPersonTypesExecuteQuery(ContactPersonTypes isContPers);

	List<StaffMembers> contactPersonTypesPostQuery(StaffMembers paramBean);

	List<ReferenceCodes> rgRelationshipTypeRecordGroup();

	List<ReferenceCodes> rgContactTypeRecordGroup();

	Integer contactPersonTypesUpdateContactPersonTypes(List<ContactPersonTypes> isContPers);

}
